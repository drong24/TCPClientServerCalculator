import java.io.*;  // for ByteArrayInputStream
import java.net.*; // for DatagramPacket

public class RequestDecoderBin implements RequestDecoder {

   private String encoding;
    
   public RequestDecoderBin() {
      encoding = "UTF-16BE";
   }

   public RequestDecoderBin(String encoding) {
      this.encoding = encoding;
   }
  
   public Request decode(InputStream wire) throws IOException {
      DataInputStream src = new DataInputStream(wire);
      int tml              = src.readByte();
      int opCode           = src.readByte();
      int op1              = src.readInt();
      int op2              = src.readInt();
      short requestID      = src.readShort();
      int opNameLength     = src.read();
      
      // Deal with the operation name
      byte[] stringBuf = new byte[opNameLength];
      src.readFully(stringBuf);
      String opName = new String(stringBuf, encoding);
    
      return new Request(opCode, op1, op2, requestID);
   }
}