//package font;
//
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URL;
//
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.select.Elements;
//
///**
// * @author 作者 E-mail: ZH519080@163.com
// * @version 创建时间：2016年11月10日 下午11:01:39 类说明 ：
// */
//public class DownParseTTF {
//
//    //解析ttf字体文件
//    public String parseTTF(String htmlsource) {
//
//        TTFCodeParse ttfCodeParse = new TTFCodeParse();
//
//        //解析电影的源码html文件
//        Document document = Jsoup.parse(htmlsource);
//
//        Elements headStyle = document.select("head style");
//
//        Document headStyleDocu = Jsoup.parse(headStyle.html());
//        String ttfFigureCode = "";
//
//        if (!headStyleDocu.body().text().contains(".ttf")) {
//            return "没有ttf文件解析";
//        }
//        else {
//            //获取ttf字体文件下载链接
//            String ttfURL = "http://" + headStyleDocu.body().text().substring(244, 310);
//
//            //获取ttf字体文件的名称
//            String ttfName = ttfURL.substring(33, ttfURL.length());
//
//            //本地存放地址
//            String savePath = "F:\\ttf\\";
//
//
//
//            try {
//                URL url = new URL(ttfURL);
//
//                // 获取url链接反应
//                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//
//                // 设置超时间为10秒
//                conn.setConnectTimeout(10 * 1000);
//
//                // 防止屏蔽程序抓取而返回403错误
//                conn.setRequestProperty("User-Agent",
//                        "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:49.0) Gecko/20100101 Firefox/49.0");
//
//                // 得到输入流
//                InputStream inputStream = conn.getInputStream();
//
//                // 获取自己数组
//                byte[] getData = readInputStream(inputStream);
//
//                // 文件保存位置
//                File saveDir = new File(savePath);
//
//                if (!saveDir.exists()) {
//                    saveDir.mkdir();
//                }
//
//                File file = new File(saveDir + File.separator + ttfName);
//                FileOutputStream fos = new FileOutputStream(file);
//                fos.write(getData);
//
//                //得到ttf字的编码,猫眼电影中的ttf文件是对0到9的解析编码，找到其一一对应的顺序
//                ttfFigureCode = ttfCodeParse.parseInner(file.toString());
//
//                if (fos != null) {
//                    fos.close();
//                }
//
//                if (inputStream != null) {
//                    inputStream.close();
//                }
//
//                // return code;
//            } catch (MalformedURLException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            } catch (IOException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//            return ttfFigureCode;
//        }
//
////        return ttfFigureCode;
//    }
//
//
//    public static byte[] readInputStream(InputStream inputStream) throws IOException {
//
//        byte[] buffer = new byte[1024];
//
//        int len = 0;
//
//        ByteArrayOutputStream bos = new ByteArrayOutputStream();
//
//        while ((len = inputStream.read(buffer)) != -1) {
//
//            bos.write(buffer, 0, len);
//
//        }
//
//        bos.close();
//
//        return bos.toByteArray();
//    }
//
//}
//
//