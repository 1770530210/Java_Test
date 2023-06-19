package web.udp.single;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class SendMessageDemo {
    public static void main(String[] args) throws IOException {
        //UDP发送数据
        //1.创建DatagramSocket对象
        //2.数据打包
        //3.发送数据
        //4.释放资源

        //1.创建DatagramSocket对象
        //空参    随机分配一个端口
        //有参    指定端口
        DatagramSocket datagramSocket = new DatagramSocket();
        //2.数据打包
        String str = "hello";
        byte[] bytes = str.getBytes();
        InetAddress address = InetAddress.getByName("127.0.0.1");
        int port = 10086;
        DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length, address, port);
        //3.发送数据
        datagramSocket.send(datagramPacket);
        //4.释放资源
        datagramSocket.close();
    }
}
