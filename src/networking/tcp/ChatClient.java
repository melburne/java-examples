package networking.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Connects with the {@link ChatServer} to send and receive messages on port 9090. {@link ChatClient} needs to
 * send a message before it can receive messages from the {@link ChatServer}.
 */
public class ChatClient {
  private Socket clientSocket;
  private static BufferedReader reader;
  private static PrintWriter writer;

  public void start(String ip, int port) throws IOException {
    clientSocket = new Socket(ip, port);

    reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    writer = new PrintWriter(clientSocket.getOutputStream(), true);
  }

  public static void main(String[] args) throws IOException {
    ChatClient chatClient = new ChatClient();
    BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
    String inputMessage;
    String serverMessage;

    chatClient.start("localhost", 9090);

    while (true) {
      inputMessage = userInput.readLine();
      writer.println(inputMessage);

      serverMessage = reader.readLine();
      System.out.println("Server says: " + serverMessage);

      if (inputMessage.equals("bye") || serverMessage.equals("bye")) {
        chatClient.closeConnection();
        break;
      }
    }
  }

  private void closeConnection() throws IOException {
    clientSocket.close();

    reader.close();
    writer.close();
  }
}
