package networking.udp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

/**
 * Sends a {@link DatagramPacket} to {@link EchoServer} and waits for it to echo the same {@link
 * DatagramPacket} back. Communication is terminated when a 'bye' message is typed by the user.
 */
public class EchoClient {

  public static void main(String[] args) throws IOException {
    DatagramSocket datagramSocket = new DatagramSocket();
    DatagramPacket packet;
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    String inputMessage;
    byte[] buffer;

    InetAddress address = InetAddress.getLocalHost();
    int port = 9090;

    do {
      // read the input message
      inputMessage = reader.readLine();
      buffer = inputMessage.getBytes(StandardCharsets.UTF_8);
      String receivedMessage;

      // create a datagram packet and send it
      packet = new DatagramPacket(buffer, buffer.length, address, port);
      datagramSocket.send(packet);

      // wait for a datagram packet of the same size
      packet = new DatagramPacket(buffer, buffer.length);
      datagramSocket.receive(packet);
      receivedMessage = new String(packet.getData());
      System.out.println("Server echoed: " + receivedMessage);

    } while (!inputMessage.equals("bye"));

    datagramSocket.close();
  }
}
