import java.io.*;  // for ByteArrayOutputStream and DataOutputStream

public class EncoderBin implements Encoder {

   private String encoding;  // Character encoding

   public EncoderBin() {
      encoding = "UTF-16BE";           // encoding type
   }

   public EncoderBin(String encoding) {
      this.encoding = encoding;
   }

   public byte[] encode(Request request) throws Exception {
   
      ByteArrayOutputStream buf = new ByteArrayOutputStream();
      DataOutputStream out = new DataOutputStream(buf);
   
      byte[] encodedOpName = request.opName.getBytes(encoding);      // Encode operation name to byte array
      out.writeByte(encodedOpName.length + 13);              // tml
      out.writeByte(request.opCode);
      out.writeInt(request.op1);
      out.writeInt(request.op2);
      out.writeShort(request.requestID);
      out.writeByte(encodedOpName.length);
      out.write(encodedOpName);
   
      return buf.toByteArray();
   }
   
   public byte[] encode(Response response) throws Exception {
      ByteArrayOutputStream buf = new ByteArrayOutputStream();
      DataOutputStream out = new DataOutputStream(buf);
      out.writeByte(8);                                   // tml
      out.writeInt(response.result);
      out.writeByte((buf.size() == 5) ? 0 : 127);        // Error Code check
      out.writeShort(response.requestId);
      
      return buf.toByteArray();
   }
}
