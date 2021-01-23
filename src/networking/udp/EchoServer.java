package networking.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class EchoServer {
  private static DatagramSocket datagramSocket;
  private static final byte[] buffer = new byte[256]; // buffer holds the incoming datagram

  public static void main(String[] args) throws IOException {
    datagramSocket = new DatagramSocket();
    String message;

    do {
      // length indicates the number of bytes to read
      DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
      
      // blocks until a message is received and then stores it in the packet's buffer
      datagramSocket.receive(packet);

      InetAddress address = packet.getAddress();
      int port = packet.getPort();
      packet = new DatagramPacket(buffer, buffer.length, address, port);

      message = new String(packet.getData());

      datagramSocket.send(packet);

    } while (!message.equals("bye"));
  }
}
