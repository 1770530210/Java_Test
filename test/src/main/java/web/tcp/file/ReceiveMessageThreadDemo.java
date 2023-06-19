package web.tcp.file;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ReceiveMessageThreadDemo {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(10000);
        while (true) {
            Socket socket = serverSocket.accept();
            new MyThread(socket).start();
        }
//        serverSocket.close();
    }
}
