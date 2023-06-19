package web.tcp.chinese;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class SendMessageDemo {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 10000);
        Scanner scanner = new Scanner(System.in);
        OutputStream outputStream = socket.getOutputStream();
        String message;

        while (true) {
            System.out.println("输入信息：");
            message = scanner.nextLine();
            if (message.equals("886")) break;
            outputStream.write(message.getBytes());
        }

        socket.close();
    }
}
