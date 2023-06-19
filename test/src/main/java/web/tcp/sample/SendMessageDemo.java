package web.tcp.sample;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class SendMessageDemo {
    public static void main(String[] args) throws IOException {
        //TCP协议，发送数据

        //1.创建Socket对象
        //细节：   在创建对象的同时会连接服务器
        //        连接不上代码会报错
        Socket socket = new Socket("127.0.0.1", 10000);

        //2.可以从连接通道中获取输出流
        OutputStream outputStream = socket.getOutputStream();
        //写出数据
        outputStream.write("hello 你好".getBytes());

        //3.释放资源
        outputStream.close();
        socket.close();
    }
}
