package web.udp.single;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ReceiveMessageDemo2 {
    public static void main(String[] args) throws IOException {
        //1.创建DatagramSocket对象
        //必须绑定端口与发送的端口移植
        DatagramSocket datagramSocket = new DatagramSocket(10086);

        //2.接收数据包
        byte[] bytes = new byte[1024];
        DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length);

        while (true) {
            datagramSocket.receive(datagramPacket);//此方法是阻塞的
            //3.解析数据包
            byte[] data = datagramPacket.getData();
            int len = datagramPacket.getLength();
            String address = datagramPacket.getAddress().getHostAddress();
            int port = datagramPacket.getPort();

            System.out.println("接收到数据 " + new String(data, 0, len));
            System.out.println("IP: " + address + "  端口： " + port);
        }

        //4.释放资源
        //  datagramSocket.close();

    }
}
