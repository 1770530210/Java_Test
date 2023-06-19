package web.udp.single;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class SendMessageDemo2 {
    public static void main(String[] args) throws IOException {
        //UDP发送数据
        DatagramSocket datagramSocket = new DatagramSocket();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            //2.数据打包
            String str = scanner.nextLine();
            if (str.equals("886")) {
                break;
            }
            byte[] bytes = str.getBytes();
            InetAddress address = InetAddress.getByName("127.0.0.1");
            int port = 10086;
            DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length, address, port);
            //3.发送数据
            datagramSocket.send(datagramPacket);
        }

        //4.释放资源
        datagramSocket.close();
    }
}
