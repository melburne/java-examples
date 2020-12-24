package networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
  private ServerSocket serverSocket;
  private Socket clientSocket;
  private PrintWriter output;
  private BufferedReader input;
  
  public void start(int port) throws IOException {
    serverSocket = new ServerSocket(port);
    clientSocket = serverSocket.accept();
    
    output = new PrintWriter(clientSocket.getOutputStream(), true);
    input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
  
    String message;
    while ((message = input.readLine()) != null) {
      if ("bye".equals(message)) {
        System.out.println("bye");
        output.println(message);
        break;
      }
      System.out.println(message);
      output.println(message);
    }
  }
  
  public void closeConnection() throws IOException {
    input.close();
    output.close();
    serverSocket.close();
    clientSocket.close();
  }

  public static void main(String[] args) throws IOException {
    EchoServer echoServer = new EchoServer();
    
    echoServer.start(9090);
    
    echoServer.closeConnection();
  }
}
