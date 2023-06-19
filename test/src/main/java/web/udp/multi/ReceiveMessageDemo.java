package web.udp.multi;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class ReceiveMessageDemo {
    public static void main(String[] args) throws IOException {
        MulticastSocket multicastSocket = new MulticastSocket(10000);

        InetAddress address = InetAddress.getByName("224.0.0.224");//将本机加入到224.0.0.1组
        multicastSocket.joinGroup(address);

        byte[] bytes = new byte[1024];
        DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length);
        multicastSocket.receive(datagramPacket);

        int length = datagramPacket.getLength();
        byte[] data = datagramPacket.getData();
        InetAddress address1 = datagramPacket.getAddress();
        int port = datagramPacket.getPort();

        System.out.println("IP: " + address1 + " 端口： " + port + "   " + new String(data, 0, length));

        multicastSocket.close();
    }
}
