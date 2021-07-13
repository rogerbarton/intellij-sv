(* X_CORE_INFO = "xlslice_v1_0_2_xlslice,Vivado 2020.1" *)
module filter #(
    parameter int unsigned Order      = 127, // Filter order
    parameter int unsigned AddrWidth  = 7,   // Address width
    parameter int unsigned CoeffScale = 12,  // Coefficient scale factor, i.e., 2**CoeffScale
    parameter int unsigned DataWidth  = 18   // Width of the samples
  ) (
    input logic clk_i,  // Clock signal
    input logic rst_ni, // Asynchronous reset

    input  logic [DataWidth:0] data_in_i,     // Incoming data
    input  logic                 data_in_req_i, // Req at input
    output logic                 data_in_ack_o, // Ack at input

    output logic [DataWidth:0] data_out_o,     // Outgoing data
    output logic                 data_out_req_o, // Req at output
    input  logic                 data_out_ack_i  // Ack at output
  );

  typedef struct packed {
      logic [2:0] switch;
      logic       hsync;
      logic       vsync;
      logic       vde;
      logic       valid;
      logic       ready;
      logic [7:0] r;
      logic [7:0] g;
      logic [7:0] b;
  } data_t;

  localparam int unsigned N = Order;

  /*************************
   *  Signal declarations  *
   *************************/

  // ALU output signal
  logic signed [DataWidth:0] acc_d, acc_q, multiplier;

  // Output register
  logic [DataWidth:0] out_reg_d, out_reg_q;
  logic                 out_reg_en;

  logic                 wen;    // Are we writing to the ram
  logic [AddrWidth:0] ramAddr_d, ramAddr_q, n_d, n_q;
  logic [DataWidth:0] data_ram_o;
  logic [CoeffScale:0] b_n;

  /***************************
   *  Module instantiations  *
   ***************************/

   typedef enum logic[1:0] {
      Idle = 0,
      Load = 1,
      Run  = 2,
      Provide = 3
   } state_t;

   state_t state_d, state_q;

  dataRAM #(
    .DataWidth (DataWidth),  // Data Width
    .AddrWidth (AddrWidth)   // Address Width
  ) inst_data_RAM (
    .clk_i  (clk_i),
    .rst_ni (rst_ni),
    .wen_i  (wen),
    .addr_i (ramAddr_q),
    .data_i (data_in_i),
    .data_o (data_ram_o)
  );

  coeffLUT #(
    .DataWidth (CoeffScale),
    .AddrWidth (AddrWidth)  
  )inst_coeffLUT(
    .addr_i (n_q),
    .data_o (b_n)
  );


  /**************
   *  Datapath  *
   **************/

  // TODO: Insert the actual datapath elements here
  assign multiplier = b_n * data_ram_o;
  assign acc_d = n_q == 0 ? multiplier : acc_q + multiplier;


  // Output scaling: compensate for scaled coefficients, to get gain=1
  assign out_reg_d  = state_q == Run ? acc_q[DataWidth+CoeffScale-1 -: DataWidth] : out_reg_q;
  assign data_out_o = out_reg_q;


  /*********
   *  FSM  *
   *********/

  // TODO: Currently, the output register is active all of the time

  always_comb begin : proc_control_fsm
    state_d = state_q;
    wen = 0;
    data_in_ack_o = 0;
    data_out_req_o = 0;
    n_d = 0;   
    ramAddr_d = ramAddr_q;

    if(state_q == Idle) begin
      if(data_in_req_i == 1) begin
        state_d = Load;
      end
    end

    if(state_q == Load) begin
      wen = 1;
      data_in_ack_o = 1;
      ramAddr_d  = (ramAddr_q + 1) % N;
      state_d = Run;
    end

    if(state_q == Run) begin
      n_d = (n_q + 1) % N;
      ramAddr_d = (ramAddr_q + 1) % N;

      state_d = n_d == 0 ? Provide : Run;
    end

    if(state_q == Provide) begin
      data_out_req_o = 1;
      state_d = data_out_ack_i ? Idle : Provide;
    end

  end

  assign out_reg_en = 1'b1;

  always_ff @(posedge clk_i or negedge rst_ni) begin : proc_ram_d
    if(~rst_ni) begin
      state_q <= Idle;
      acc_q <= '0;
      n_q <= '0;
      ramAddr_q <= '0;
    end else begin
      state_q <= state_d;
      acc_q <= acc_d;
      n_q <= n_d;
      ramAddr_q <= ramAddr_d;
    end
  end


  /************************
   *  Address generation  *
   ************************/

  // TODO: Generate the addresses to access the LUT and the sample RAM.


  /*************************
   *  Sequential elements  *
   *************************/

  always_ff @(posedge clk_i or negedge rst_ni) begin: p_outreg_q
    if (!rst_ni) begin // Asynchronous reset (active low)
      out_reg_q <= '0;
    end else begin
      if (out_reg_en)
        out_reg_q <= out_reg_d;
    end
  end

  /***********************
   *  Outputs            *
   ***********************/


endmodule : filter
