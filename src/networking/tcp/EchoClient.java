package networking.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Sends a message to the {@link EchoServer} and reads back the echo message.
 */
public class EchoClient {
  private Socket clientSocket;
  private PrintWriter output;
  private BufferedReader input;
  
  public void start(String ip, int port) throws IOException {
    clientSocket = new Socket(ip, port);
    
    output = new PrintWriter(clientSocket.getOutputStream(), true);
    input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
  }
  
  public String sendMessage(String message) throws IOException {
    output.println(message);
    return input.readLine();
  }
  
  public void closeConnection() throws IOException {
    input.close();
    output.close();
    clientSocket.close();
  }

  public static void main(String[] args) throws IOException {
    EchoClient echoClient = new EchoClient();
    echoClient.start("localhost", 9090);
  
    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    String message = input.readLine();
    String echoMessage;
    
    while (true) {
      echoMessage = echoClient.sendMessage(message);
      System.out.println("Server echoed: " + echoMessage);
      if (message.equals("bye")) {
        echoClient.closeConnection();
        break;
      }
      
      message = input.readLine();
    }
  }
}
