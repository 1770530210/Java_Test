//package web;
//
//import org.apache.http.NameValuePair;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.client.utils.URIBuilder;
//import org.apache.http.entity.ContentType;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.message.BasicNameValuePair;
//import org.apache.http.util.EntityUtils;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.URI;
//import java.net.URL;
//import java.net.URLConnection;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//public class HttpClientUtil {
//
//    /**
//     * 发送get请求
//     *
//     * @param url   请求URL
//     * @param param 请求参数 key:value url携带参数 或者无参可不填
//     * @return
//     */
//    public static String doGet(String url, Map<String, String> param) {
//
//        // 创建Httpclient对象
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//
//        String resultString = "";
//        CloseableHttpResponse response = null;
//        try {
//            // 创建uri
//            URIBuilder builder = new URIBuilder(url);
//            if (param != null) {
//                for (String key : param.keySet()) {
//                    builder.addParameter(key, param.get(key));
//                }
//            }
//            URI uri = builder.build();
//
//            // 创建http GET请求
//            HttpGet httpGet = new HttpGet(uri);
//
//            // 执行请求
//            response = httpClient.execute(httpGet);
//            // 判断返回状态是否为200
//            if (response.getStatusLine().getStatusCode() == 200) {
////                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (response != null) {
//                    response.close();
//                }
//                httpClient.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return resultString;
//    }
//
//    /**
//     * 无参数get请求
//     *
//     * @param url
//     * @return
//     */
//    public static String doGet(String url) {
//        return doGet(url, null);
//    }
//
//    /**
//     * 发送post请求
//     *
//     * @param url   请求URL
//     * @param param 请求参数 key:value
//     * @return
//     */
//    public static String doPost(String url, Map<String, String> param) {
//        // 创建Httpclient对象
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        CloseableHttpResponse response = null;
//        String resultString = "";
//        try {
//            // 创建Http Post请求
//            HttpPost httpPost = new HttpPost(url);
//            // 创建参数列表
//            if (param != null) {
//                List<NameValuePair> paramList = new ArrayList<>();
//                for (String key : param.keySet()) {
//                    paramList.add(new BasicNameValuePair(key, param.get(key)));
//                }
//                // 模拟表单
//                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);
//                httpPost.setEntity(entity);
//            }
//            // 执行http请求
//            response = httpClient.execute(httpPost);
//            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (response != null) {
//                    response.close();
//                }
//                httpClient.close();
//            } catch (IOException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//        }
//
//        return resultString;
//    }
//
//    /**
//     * 发送post 请求
//     *
//     * @param url  请求地址
//     * @param json 请求参数
//     * @return
//     */
//    public static String doPostJson(String url, String json) {
//        // 创建Httpclient对象
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        CloseableHttpResponse response = null;
//        String resultString = "";
//        try {
//            // 创建Http Post请求
//            HttpPost httpPost = new HttpPost(url);
//            // 创建请求内容
//            StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
//            httpPost.setEntity(entity);
//            // 执行http请求
//            response = httpClient.execute(httpPost);
//            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (response != null) {
//                    response.close();
//                }
//                httpClient.close();
//            } catch (IOException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//        }
//        return resultString;
//    }
//
//    public static void main(String[] args) {
//        var t_gus = new File("t_gus.txt");
//        var url = "https://www.shtdo.com/Admin/Others/DownloadsPage.aspx";
//        var url1 = "https://dwin.com.cn/%E8%B5%84%E6%96%99%E4%B8%8B%E8%BD%BD/%E8%8A%AF%E7%89%87%E5%BC%80%E5%8F%91%E8%B5%84%E6%96%99.rar";
//        var url2 = "nid=28&id=733&ref=claproducts&t=f7d422438915c48a&pre=1";
////        String s = sendGet(url, "nid=28&id=733&ref=claproducts&t=f7d422438915c48a");
//        String s = sendGet(url1, url2);
//        System.out.print(s);
//    }
//
//    /**
//     * 向指定URL发送GET方法的请求
//     *
//     * @paramurl 发送请求的URL
//     * @paramparam 请求参数, 请求参数应该是name1=value1&name2=value2的形式。
//     */
//    public static String sendGet(String url, String param) {
//        String result = "";
//        BufferedReader in = null;
//        try {
//            String urlNameString = url + "?" + param;
////            String urlNameString = url + param;
//            System.out.println(urlNameString);
//            URL realUrl = new URL(urlNameString);
////打开和URL之间的连接
//            URLConnection connection = realUrl.openConnection();
////设置通用的请求属性
//            connection.setRequestProperty("accept", "*/*");
//            connection.setRequestProperty("connection", "Keep-Alive");
//            connection.setRequestProperty("user-agent",
//                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
////建立实际的连接
//            connection.connect();
////获取所有响应头字段
//            Map map = connection.getHeaderFields();
////遍历所有的响应头字段
//            for (String key : (Set<String>) map.keySet()) {
//                System.out.println(key + "--->" + map.get(key));
//            }
////定义BufferedReader输入流来读取URL的响应
//            in = new BufferedReader(new InputStreamReader(
//                    connection.getInputStream()));
//            String line;
//            while ((line = in.readLine()) != null) {
//                result += line;
//            }
//        } catch (Exception e) {
//            System.out.println("发送GET请求出现异常！" + e);
//            e.printStackTrace();
//        }
////使用finally块来关闭输入流
//        finally {
//            try {
//                if (in != null) {
//                    in.close();
//                }
//            } catch (Exception e2) {
//                e2.printStackTrace();
//            }
//        }
//        return result;
//    }
//}
//
