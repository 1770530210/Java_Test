package web.tcp.threadpool;

import web.tcp.file.MyThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Server {
    public static void main(String[] args) throws IOException {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
                3,//核心线程数量
                16,//线程池总大小
                60,//空闲时间
                TimeUnit.SECONDS,//空闲时间（单位）
                new ArrayBlockingQueue<>(2),//队列
                Executors.defaultThreadFactory(),//线程工厂，让线程池如何创建线程对象
                new ThreadPoolExecutor.AbortPolicy()//阻塞队列
        );

        ServerSocket serverSocket = new ServerSocket(10000);
        while (true) {
            Socket socket = serverSocket.accept();
            //开启一条线程，一个用户对应服务端的一条线程
//            new MyThread(socket).start();
            poolExecutor.submit(new MyThread(socket));
        }

    }
}
