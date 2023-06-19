package lambda;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;

public class TimerPrint implements ActionListener {
    public static void main(String[] args) {
        var listener = new TimerPrint();
        var timer = new Timer(1000, listener);
        //构造一个定时器，每经过1000毫秒（1秒）通知listener一次
        timer.start();
        //启动定时器
        JOptionPane.showMessageDialog(null, "Quit program?");
        //显示一个包含一条提示信息和OK按钮的对话框，这个对话框位于parent组件的中央。如果parent为null，对话框将显示在中央
        System.exit(0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("At the tone, the time is " + Instant.ofEpochMilli(e.getWhen()));
        Toolkit.getDefaultToolkit().beep();
        //获得默认的工具箱。工具箱包含有关GUI环境的信息，beep（）发出一声铃响
    }
}
