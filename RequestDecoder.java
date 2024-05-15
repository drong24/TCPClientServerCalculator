import java.io.*;   // for InputStream and IOException
import java.net.*;  // for DatagramPacket

public interface RequestDecoder {
   Request decode(InputStream wire) throws IOException;
}
