package networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {
  private ServerSocket serverSocket;
  private Socket clientSocket;
  
  private static BufferedReader reader;
  private static PrintWriter writer;
  
  public void start(int port) throws IOException {
    serverSocket = new ServerSocket(port);
    clientSocket = serverSocket.accept();
    
    reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    writer = new PrintWriter(clientSocket.getOutputStream(), true);
  }

  public static void main(String[] args) throws IOException {
    ChatServer server = new ChatServer();
    BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
    String inputMessage;
    String outputMessage;
    
    server.start(9090);
    
    while (true) {
      inputMessage = reader.readLine();
      System.out.println("Client says: " + inputMessage);
      
      if (inputMessage.equals("bye")) {
        writer.println("bye");
        server.closeConnection();
        break;
      } else {
        outputMessage = userInput.readLine();
        writer.println(outputMessage);
      }
    }
  }
  
  private void closeConnection() throws IOException {
    serverSocket.close();
    clientSocket.close();
    
    reader.close();
    writer.close();
  }
}
