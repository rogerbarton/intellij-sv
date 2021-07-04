timeunit 1us;
timeprecision 1ps;

/* Block Comment */
module filter #(
//    parameter int unsigned Order      = 127, // Filter order
//    parameter int unsigned AddrWidth  = 7   // Address width
  ) (
    input logic clk_i,                         // Clock signal

    input logic                 data_in_req_i, // Req at input
    input logic [DataWidth-1:0] data_in_i,     // Incoming data
  );

endmodule : filter