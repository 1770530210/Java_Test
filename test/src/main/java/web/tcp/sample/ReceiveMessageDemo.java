package web.tcp.sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ReceiveMessageDemo {
    public static void main(String[] args) throws IOException {
        //TCP协议，接收数据

        //1.创建对象ServerSocket
        ServerSocket serverSocket = new ServerSocket(10000);
        //2.监听客户端的连接
        Socket socket = serverSocket.accept();
        //3.从连接通道中获取输入流读取数据
        InputStream inputStream = socket.getInputStream();//字节流 原始字节
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);//字符流  中文
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);//缓冲流   提速
        int len;
        while ((len = bufferedReader.read()) != -1) {
            System.out.print((char) len);
        }
        //4.关闭资源
        socket.close();
        serverSocket.close();
    }
}
