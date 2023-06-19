package web;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebDemo {
    public static void main(String[] args) throws IOException {
        String net = "https://hanyu.baidu.com/shici/detail?pid=0b2f26d4c0ddb3ee693fdb1137ee1b0d&from=kg0";//网址
        String content = webCrawler(net);
        ArrayList<String> list = getData(content, "(.{4})(，|。)", 1);
        System.out.println(list);
    }

    /**
     * 从网络中爬取数据，把数据拼接成字符串
     *
     * @param net 网络地址
     * @return 返回网页内容
     * @throws IOException 地址不存在异常
     */
    public static String webCrawler(String net) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        URL url = new URL(net);
        URLConnection connection = url.openConnection();
        InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());
        int len;
        char[] data = new char[1024];
        while ((len = inputStreamReader.read(data)) != -1) {
            stringBuilder.append(data, 0, len);
        }
        inputStreamReader.close();
        return stringBuilder.toString();
    }

    /**
     * 通过正则表达式获取字符串中内容
     *
     * @param content 字符串内容
     * @param regex   正则表达式
     * @return 字符数组
     */
    public static ArrayList<String> getData(String content, String regex, int index) {
        ArrayList<String> list = new ArrayList<>();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            String group = matcher.group(index);
            System.out.println(group);
            list.add(group);
        }
        return list;
    }
}
