package web.tcp.file;

import java.io.*;
import java.net.Socket;
import java.util.UUID;

public class MyThread extends Thread {
    private final Socket socket;

    public MyThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        //开启线程

        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(socket.getInputStream());//缓冲流   提速
            String name = UUID.randomUUID().toString().replace("-", "");
            File file = new File("properties/" + name + ".property");
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedOutputStream bufferedOUtputStream = new BufferedOutputStream(new FileOutputStream(file));//缓冲流   提速
            byte[] bytes = new byte[1024];
            int len;
            while ((len = bufferedInputStream.read(bytes)) != -1) {
                bufferedOUtputStream.write(bytes, 0, len);
            }
            bufferedOUtputStream.flush();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bufferedWriter.write("上传成功");
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null)
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
}
