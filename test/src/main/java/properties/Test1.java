package properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Test1 {
    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();

        properties.put("aaa", "111");
        properties.put("bbb", "222");
        properties.put("ccc", "333");
        properties.put("ddd", "444");

        File file = new File("properties/test1.property");
//        file.getParentFile().mkdirs();
//        file.createNewFile();

//        存储推荐方式
//        优点：1.打印时间和备注
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        properties.store(fileOutputStream, "test1");//存储
        fileOutputStream.close();

//        general
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
//        Set<Map.Entry<Object,Object>> entries=properties.entrySet();
//        for (Map.Entry<Object, Object> entry : entries) {
//            Object key=entry.getKey();
//            Object value=entry.getValue();
//            bufferedWriter.write(key+"="+value);
//            bufferedWriter.newLine();
//        }
//        bufferedWriter.close();

//        读取
        Properties properties1 = new Properties();
        FileInputStream fileInputStream = new FileInputStream(file);
        properties1.load(fileInputStream);
        fileInputStream.close();
        properties1.list(System.out);
        for (Object o : properties1.keySet()) {
            System.out.println(properties1.get(o.toString()));
//            System.out.println(properties1.getProperty(o.toString()));
        }
    }
}
