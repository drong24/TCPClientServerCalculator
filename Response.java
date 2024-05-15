
public class Response {
   public int result;
   public int errorCode;
   public short requestId;

   public Response(int result, short requestId) {
   
      this.result = result;
      this.requestId = requestId;
      
   }
   
   public String toString() {
      final String EOLN = java.lang.System.getProperty("line.separator");
   
      String value = "Result: " + result + EOLN +
         "Request ID: " + requestId + EOLN +
         "Error: ";
      value += (errorCode == 0) ? "Ok" : "Error";
      return value;
   }
}