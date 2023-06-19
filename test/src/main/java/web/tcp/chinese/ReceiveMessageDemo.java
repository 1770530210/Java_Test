package web.tcp.chinese;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ReceiveMessageDemo {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(10000);
        Socket socket = ss.accept();
        InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());//字符流  中文
        int len;
        while ((len = inputStreamReader.read()) != -1) {
            System.out.println((char) len);
        }
        //4.关闭资源
        socket.close();
        ss.close();
    }
}
