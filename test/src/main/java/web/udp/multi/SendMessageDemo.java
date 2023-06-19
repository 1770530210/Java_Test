package web.udp.multi;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class SendMessageDemo {
    public static void main(String[] args) throws IOException {
        MulticastSocket multicastSocket = new MulticastSocket();

        String str = "group cast";
        byte[] bytes = str.getBytes();
        InetAddress address = InetAddress.getByName("224.0.0.224");//255.255.255.255向互联网所有电脑发送数据
        int port = 10000;

        DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length, address, port);
        multicastSocket.send(datagramPacket);

        multicastSocket.close();
    }
}
