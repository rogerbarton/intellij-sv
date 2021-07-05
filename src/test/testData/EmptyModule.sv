timeunit 1us;
timeprecision 1ps;

module empty ();

endmodule

module empty2 #() ();

endmodule

module empty3 #(
    parameter int Order = 1,
    localparam int Id = 1,
) ();

endmodule

module empty3 #() (
    input logic clk_i = 1'b0,
    input logic clk_i = 1'hFF,
);

endmodule


module empty3 #(
    parameter int unsigned Order
) (
    input logic clk_i
);

endmodule


module filter #(
    parameter int unsigned Order      = 127,   // Filter order
    parameter int unsigned AddrWidth  = 7      // Address width
  ) (
    input logic clk_i,                         // Clock signal

    input logic       data_in_req_i, // Req at input
    input logic [1:0] data_in_i,     // Incoming data
  );

endmodule : filter