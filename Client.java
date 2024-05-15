
import java.net.*;                 // for DatagramSocket, DatagramPacket, and InetAddress
import java.io.*;                  // for IOException
import java.util.Scanner;          // for console input to create operation requests
import java.text.DecimalFormat;    // For formatting round trip time


public class Client {
   
   private final static int BUFSIZE = 8;
   public static void main(String args[]) throws Exception {
            
      if (args.length != 2)  // Test for correct # of args        
         throw new IllegalArgumentException("Parameter(s): <Destination>" +
            	     " <Port> [<encoding]");
                    
      String server = args[0];
      int servPort = Integer.parseInt(args[1]);
      
      byte[] byteBuffer = new byte[BUFSIZE];
      byte[] message;
      
      Socket socket;
      
      Encoder encoder = new EncoderBin();
      ResponseDecoder decoder = new ResponseDecoderBin();
      Response receivedResponse;
      String hex = "";
      
      boolean cont = true;
      Scanner scan = new Scanner(System.in);          // For receiveing user input and creating request
      int num1, num2, op;
      short requestID = 0;
      String c;
      
      DecimalFormat df = new DecimalFormat("###0.0###");
      double startTime, endTime;                      // To determine the round trip time    
      
      while (cont) {
         socket = new Socket(server, servPort);
         InputStream in = socket.getInputStream();
         OutputStream out = socket.getOutputStream();            
         // Create request, encode, and send to Server
         System.out.print("Enter the first number: ");
         num1 = scan.nextInt();
         System.out.print("Enter the second number: ");
         num2 = scan.nextInt();
         System.out.println("Operation Codes \n *\t/\t|\t&\t-\t+\n0\t1\t2\t3\t4\t5");
         System.out.print("Enter the Operand code: ");
         op = scan.nextInt();
         scan.nextLine();                // Reset scanner pointer
      
         Request req = new Request(op, num1, num2, requestID);
         requestID++;
         
         message = encoder.encode(req);
         for (byte i : message) {                 // Prints out received request in hex
            hex += String.format("%02X", i);
         } 
         
         System.out.println("\nSending Request:\nHex: " + hex);
         hex = "";
         
         out.write(message);
         startTime = System.nanoTime();            // Records start time
      
         in.read(byteBuffer);
         endTime = System.nanoTime();
         ByteArrayInputStream buf = new ByteArrayInputStream(byteBuffer);
         receivedResponse = decoder.decode(buf);
      
         for (byte i : byteBuffer) {                 // Prints out received request in hex
            hex += String.format("%02X", i);
         } 
         
         System.out.println("\nReceived Response:\nHex: " + hex);
         System.out.println(receivedResponse.toString());
         System.out.println("Round trip time: " + df.format(endTime - startTime));     // Prints out the round trip time 
         hex = "";
         
         // Checks if user wishes to continue using client
         System.out.print("\nContinue? yes/no: ");
         c = scan.nextLine();
         while (!c.equals("yes") && !c.equals("no")) {
            System.out.print("Invalid input. Please enter only yes or no: ");
            c = scan.nextLine();
         }
         cont = (c.equals("yes")) ? true : false;
      }
   }
}