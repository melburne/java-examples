package networking.udp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

public class EchoClient {
  private static DatagramSocket datagramSocket;
  private static InetAddress address;
  private static byte[] buffer = new byte[256];

  public static void main(String[] args) throws IOException {
    datagramSocket = new DatagramSocket();
    address = InetAddress.getByName("localhost");

    startEcho();
  }

  private static void startEcho() throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    String inputMessage;

    do {
      inputMessage = reader.readLine();
      buffer = inputMessage.getBytes(StandardCharsets.UTF_8);
      String receivedMessage;

      DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, 9090);
      datagramSocket.send(packet);

      packet = new DatagramPacket(buffer, buffer.length);
      datagramSocket.receive(packet);
      receivedMessage = new String(packet.getData());
      System.out.println("Server echoed: " + receivedMessage);

    } while (!inputMessage.equals("bye"));
  }
}
