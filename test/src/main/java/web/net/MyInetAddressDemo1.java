package web.net;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class MyInetAddressDemo1 {
    public static void main(String[] args) throws UnknownHostException {
        //InetAddress   表示互联网协议IP地址

        //端口号:应用程序在设备中唯一标识（0-65535）2个字节 16位
        //0-1023    用于知名网络服务或应用
        //常规使用1024以上端口
        //端口    面向外界交流的通道
        //软件通过绑定端口向外界发送信息

        //协议：   数据传输规则
        //连接和通信的规则（网络通信协议）
        //OSI世界互联协议标准，全球通信规范（未广泛推广）
        //TCP/IP协议：     事实国际标准
        /*
       {OSI                   TCP/IP
        应用
        表示          ---->    应用     (HTTP、FTP、Telnet、DNS...)
        会话
        }
        传输                   TCP、UDP...
        网络                   IP、ICMP、ARP...
        {
        数据链路       ---->    物理链路层   硬件设备0101010101...
        物理
        }
         */
        //UDP
        //用户数据报协议（User Datagram Protocol）
        //UDP是面向无连接通信协议（不检测是否连接）
        //速度快，大小限制：一次最多发送64k，数据不安全，易丢失数据
        //适用：网络会议、网络通话、在线视频
        //通信方式：
        //1.单播
        //2.组播      组播地址：   224.0.0.0————239.255.255.255（预留的组播地址）
        //3.广播      广播地址：   255.255.255.255 （互联网上的所有电脑）

        //TCP
        //传输控制协议（Transmission Control Protocol）
        //TCP是面向连接通信协议（确保连接才会发送数据）
        //速度慢，没有大小限制，数据安全
        //适用：下载软件、文字聊天、发送邮件

        //IP对象 一台电脑的对象
//        InetAddress inetAddress = InetAddress.getByName("192.168.112.20");//ctrl alt +v
//        System.out.println(inetAddress);
        InetAddress address = InetAddress.getByName("computer01");
        System.out.println(address);

        String name = address.getHostName();
        System.out.println(name);//获取不到返回ip

        String ip = address.getHostAddress();
        System.out.println(ip);
    }
}
