package xml;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.FileOutputStream;
import java.io.InputStream;

public class TestWrite {
    public static void main(String[] args) throws Exception {
        InputStream is = TestWrite.class.getClassLoader().getResourceAsStream("Test.xml");
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(is);
        Element root = document.getRootElement();

//      在内存中创建
        Element backModel = root.addElement("backModel");

        Element subModel1 = backModel.addElement("subModel");
        subModel1.addAttribute("id", "1");

//      写入xml文件
        FileOutputStream out = new FileOutputStream("./src/main/resources/com.test.Test.xml");
        OutputFormat format = new OutputFormat("\t", true, "UTF-8");
        XMLWriter xw = new XMLWriter(out, format);
        xw.write(document);
        System.out.println("写入完成");
        xw.close();
    }
}
