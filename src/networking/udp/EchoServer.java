package networking.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

/**
 * Receives a {@link DatagramPacket} over a {@link DatagramSocket} and echos it back to {@link
 * EchoClient}. The {@link DatagramSocket} is closed when a 'bye' message is received.
 */
public class EchoServer {

  public static void main(String[] args) throws IOException {
    DatagramSocket datagramSocket = new DatagramSocket(9090);
    DatagramPacket packet;
    String clientMessage;
    byte[] buffer;

    do {
      // buffer holds the incoming datagram
      buffer = new byte[50];

      // length indicates the number of bytes to read
      packet = new DatagramPacket(buffer, buffer.length);

      // blocks until a message is received and then stores it in the packet's buffer
      datagramSocket.receive(packet);
      byte[] receivedBytes = packet.getData();

      clientMessage = new String(receivedBytes, StandardCharsets.UTF_8);
      System.out.println(clientMessage);

      // retrieve packet info and echo it back
      InetAddress address = packet.getAddress();
      int port = packet.getPort();
      packet = new DatagramPacket(receivedBytes, receivedBytes.length, address, port);
      datagramSocket.send(packet);

      // buffer length and the message length may not match, hence .startsWith() is used instead of
      // .equals()
    } while (!clientMessage.startsWith("bye"));

    datagramSocket.close();
  }
}
