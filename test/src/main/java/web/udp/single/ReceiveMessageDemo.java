package web.udp.single;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ReceiveMessageDemo {
    public static void main(String[] args) throws IOException {
        //UDP接收数据
        //1.创建DatagramSocket对象
        //2.数据打包
        //3.发送数据
        //4.释放资源

        //1.创建DatagramSocket对象
        //必须绑定端口与发送的端口移植
        DatagramSocket datagramSocket = new DatagramSocket(10086);

        //2.接收数据包
        byte[] bytes = new byte[1024];
        DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length);

        datagramSocket.receive(datagramPacket);//此方法是阻塞的

        //3.解析数据包
        byte[] data = datagramPacket.getData();
        int len = datagramPacket.getLength();
        InetAddress address = datagramPacket.getAddress();
        int port = datagramPacket.getPort();

        System.out.println("接收到数据 " + new String(data, 0, len));
        System.out.println("IP: " + address + "端口： " + port);

        //4.释放资源
        datagramSocket.close();

    }
}
