package web.tcp.file;

import java.io.*;
import java.net.Socket;

public class SendMessageDemo {
    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("127.0.0.1", 10000);

        BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream("properties/test.property"));
        BufferedOutputStream outputStream = new BufferedOutputStream(socket.getOutputStream());
        byte[] bytes = new byte[1024];
        int len;
        while ((len = inputStream.read(bytes)) != -1) {
            outputStream.write(bytes, 0, len);
        }
        outputStream.flush();
        //标记写出关闭
        socket.shutdownOutput();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String line = bufferedReader.readLine();
        System.out.println(line);

        socket.close();
    }
}
