# TCPServer

Implementation of a simple Calculating Server. This calculating server performs bitwise Boolean and
arithmetic computations requested by a client on signed integers. 

## Program Testing and Usage Instructions

### Compilation:
Compile all programs on a tux machine before running them.

### Running the Server:
Execute the server program using the command: java Server port#, where port# is the port number you want the server to listen on.

### Running the Client:
On a separate tux machine, run the client program by entering: java Client servername port#, replacing servername with the name of the tux machine where the server is running, and port# with the same port number used for the server.
Once the client is running, it will prompt you to input two numbers and an operation code. Follow the prompts, and the client will generate a request, displaying it in hexadecimal format. The response received from the server will also be displayed in both hexadecimal and readable English on the client.
The server will print the received request in both hexadecimal and readable English.
After processing each request, the client will ask if you want to create a new request or quit.

### Roundtrip Time Analysis:
After collecting the roundtrip time of 30 requests, the following statistics were observed:
Minimum time taken: 1518544 ns
Maximum time taken: 6355254 ns
Average time for all requests: 3349481.9 ns
Feel free to utilize these programs for your needs, and don't hesitate to reach out if you encounter any issues or have questions regarding their usage.