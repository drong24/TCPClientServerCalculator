import java.io.*;  // for ByteArrayInputStream
import java.net.*; // for DatagramPacket

public class ResponseDecoderBin implements ResponseDecoder {

   private String encoding;
    
   public ResponseDecoderBin() {
      encoding = "UTF-16BE";
   }

   public ResponseDecoderBin(String encoding) {
      this.encoding = encoding;
   }
  
   public Response decode(InputStream wire) throws IOException {
      DataInputStream src = new DataInputStream(wire);
      byte tml              = src.readByte();      // tml 
      int result           = src.readInt();
      byte errorCode        = src.readByte();
      short requestID      = src.readShort();
    
      return new Response(result, requestID);
   }

   public Response decode(DatagramPacket p) throws IOException {
      ByteArrayInputStream payload =
         new ByteArrayInputStream(p.getData(), p.getOffset(), p.getLength());
      return decode(payload);
   }
}