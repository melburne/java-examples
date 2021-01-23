package networking.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Enables two-way communication with the {@link ChatClient} on port 9090. Chats are initiated by
 * the {@link ChatClient}.
 *
 * <p>{@link ChatServer} need to be up before starting the {@link ChatClient}.
 */
public class ChatServer {
  private ServerSocket serverSocket;
  private Socket clientSocket;

  private static BufferedReader reader;
  private static PrintWriter writer;

  public void start(int port) throws IOException {
    serverSocket = new ServerSocket(port);
    clientSocket = serverSocket.accept(); // blocks the program until a client makes a connection

    reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    writer = new PrintWriter(clientSocket.getOutputStream(), true);
  }

  public static void main(String[] args) throws IOException {
    ChatServer server = new ChatServer();
    BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
    String clientMessage;
    String inputMessage;

    server.start(9090);

    while (true) {
      clientMessage = reader.readLine();
      System.out.println("Client says: " + clientMessage);

      if (clientMessage == null || clientMessage.equals("bye")) {
        writer.println("bye");
        server.closeConnection();
        break;
      } else {
        // read the message typed by the user and send it to the client
        inputMessage = userInput.readLine();
        writer.println(inputMessage);
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
