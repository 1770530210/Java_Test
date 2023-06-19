package thread.work.robRedPacket;

import thread.work.MyThread;

public class RobRedPacket {
    public static void main(String[] args) {

        MyThread myThread1 = new MyThread();
        MyThread myThread2 = new MyThread();
        MyThread myThread3 = new MyThread();
        MyThread myThread4 = new MyThread();
        MyThread myThread5 = new MyThread();

        myThread1.setName("小A");
        myThread2.setName("小B");
        myThread3.setName("小C");
        myThread4.setName("小D");
        myThread5.setName("小E");

        myThread1.start();
        myThread2.start();
        myThread3.start();
        myThread4.start();
        myThread5.start();
    }
}
