

public class Request {

   public int opCode;
   public int op1;
   public int op2;
   public short requestID;
   public String opName;
   
   public Request (int opCode, int op1, int op2, short requestID){
      this.opCode = opCode;
      this.op1 = op1; 
      this.op2 = op2; 
      this.requestID = requestID;
      switch (opCode) {
         case 0: 
            this.opName = "multiplication";
            break;
         case 1:
            this.opName = "division";
            break;
         case 2: 
            this.opName = "or";
            break;
         case 3: 
            this.opName = "and";
            break;
         case 4: 
            this.opName = "subtraction";
            break;
         case 5: 
            this.opName = "addition";
            break;
         default:
            this.opName = null;
            break;
      }
   
   }

   public String toString() {
      final String EOLN = java.lang.System.getProperty("line.separator");
   
      String value = "Op Code = " + opCode + EOLN +
                   "op1  = " + op1 + EOLN +
                   "op2 = " + op2 + EOLN + 
                   "Request ID = " + requestID + EOLN +
                   "Op Name = " + opName + EOLN;
      return value;
   
   }

}