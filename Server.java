import java.net.*;  // for DatagramSocket and DatagramPacket
import java.io.*;   // for IOException

public class Server {

   private static final int BUFSIZE = 64;          // Size of receive buffer
   public static void main(String[] args) throws Exception {
      
      if (args.length != 1)  // Test for correct # of args        
         throw new IllegalArgumentException("Parameter(s): <Port> [<encoding>]");
      
      int servPort = Integer.parseInt(args[0]);   // Receiving Port
      ServerSocket servSock = new ServerSocket(servPort);  // UDP socket for sending and receiving 
      byte[] byteBuffer;                           // Receive buffer
      String hex = "";
      
      
      RequestDecoder decoder = new RequestDecoderBin();
      Encoder encoder = new EncoderBin();               // Fields for encoding and sending responses
      Request receivedRequest;
      Response response;
   
      
      while(true) {
         Socket clntSock = servSock.accept();
         InputStream in = clntSock.getInputStream();
         OutputStream out = clntSock.getOutputStream();
         byteBuffer = new byte[BUFSIZE];                             // resets byteBuffer for new client message
         
         in.read(byteBuffer);
         ByteArrayInputStream buf = new ByteArrayInputStream(byteBuffer);
         receivedRequest = decoder.decode(buf);
            
         for (byte i : byteBuffer) {                 // Prints out received request in hex
            hex += String.format("%02X", i);
         } 
      
         System.out.println("\nReceived Request:\nHex: " + hex.substring(0, byteBuffer[0] * 2));
         System.out.println("\nRequest Info:\n" + receivedRequest.toString());
         hex = "";                                             // Resets hex String for next client
         
         response = new Response(calc(receivedRequest), receivedRequest.requestID);
         out.write(encoder.encode(response));
         
         clntSock.close();
      }
      /*      
      DatagramPacket packet;                                            // Fields for receiving and decoding requests
      RequestDecoder decoder = new RequestDecoderBin();
      Request receivedRequest;
      Response response;
      byte[] requestData;                    
      String hex = "";                       // To display received request in hexadecimal
      
      Encoder encoder = new EncoderBin();               // Fields for encoding and sending responses
      byte[] codedResponse;
      DatagramPacket message;
      InetAddress senderAddr;
      
      while (true) {
      
         // Receive and decode request
         packet = new DatagramPacket(new byte[64],64);
         sock.receive(packet);
         senderAddr = packet.getAddress();        // Gets the sender address
         requestData = packet.getData();           
         
         for (byte i : requestData) {                 // Prints out received request in hex
            hex += String.format("%02X", i);
         } 
         System.out.println("Received Request:\n" + hex);
         hex = "";                             // Resets string for next request
         
         receivedRequest = decoder.decode(packet);
         System.out.println(receivedRequest);         // Prints out received request in english
         
         // Create, encode, and send response
         response = new Response(calc(receivedRequest), receivedRequest.requestID);
         codedResponse = encoder.encode(response);          
         message = new DatagramPacket(codedResponse, codedResponse.length, 
            		  senderAddr, port);
         sock.send(message);
      }
      */
   }
   
   // Used to calculate operations
   public static int calc(Request r) {
      int num1 = r.op1;
      int num2 = r.op2;
      switch (r.opCode) {
         case 0: 
            return num1 * num2;
         case 1:
            return num1 / num2;
         case 2: 
            return num1 | num2;
         case 3: 
            return num1 & num2;
         case 4: 
            return num1 - num2;
         case 5: 
            return num1 + num2;
         default:
            return 0;
      }
   }
}

