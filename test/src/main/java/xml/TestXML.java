//package xml;
//
//import org.dom4j.Document;
//import org.dom4j.DocumentException;
//import org.dom4j.Element;
//import org.dom4j.Node;
//import org.dom4j.io.SAXReader;
//
//import java.io.InputStream;
//import java.util.List;
//
//public class TestXML {
//    public static void main(String[] args) throws DocumentException {
////		加载xml文件到jvm中，形成数据流
//        InputStream is = TestXML.class.getClassLoader().getResourceAsStream("UIData.xml");
////		创建解析对象
//        SAXReader saxReader = new SAXReader();
////		获取文档对象（整个xml文件）【将数据流转换成一个文档对象】
//        Document document = saxReader.read(is);
////		获取根节点
//        Element root = document.getRootElement();
////		获取根元素下的所有子节点
////		List<Element> list = root.elements();
////		for (Element element : list) {
////			System.out.println(element.getName());
////			List<Element> list2 = element.elements();
////			for (Element element2 : list2) {
////				输出元素名和里面的数据
////				System.out.println(element2.getName() + ":" + element2.getData());
////			}
////		}
//
////		for (Element element : list) {
////			获取属性
////			Attribute attribute = element.attribute("name");
////			输出属性值
////			System.out.println(attribute.getValue());
////		}
////		获取所有学生
//        List<Node> list2 = root.selectNodes("student");// 类似模糊匹配
//        list2.forEach(s -> System.out.println(s));
////		获取所有学生名字
//        List<Node> list3 = root.selectNodes("student/name");// 模糊匹配
//        list3.forEach(System.out::print);
////		获取所有名字，忽略层级和位置
//        List<Node> list4 = root.selectNodes("//name");// 模糊匹配
//        list4.forEach(s -> System.out.println(s.getStringValue()));
////		获取第一个学生
//        List<Node> list5 = root.selectNodes("student[1]/name");// 模糊匹配
//        list5.forEach(s -> System.out.println(s.getStringValue()));
////		获取最后一个学生
//        List<Node> list6 = root.selectNodes("student[last()]/name");// 模糊匹配
//        list6.forEach(s -> System.out.println(s.getStringValue()));
////		获取前2个学生
//        List<Node> list7 = root.selectNodes("student[position()<3]/name");// 模糊匹配
//        list7.forEach(s -> System.out.println(s.getStringValue()));
////		获取带有type属性的学生
//        List<Node> list8 = root.selectNodes("student[@type]/name");// 模糊匹配
//        list8.forEach(s -> System.out.println(s.getStringValue()));
////		获取带有type属性等于c的学生
//        List<Node> list9 = root.selectNodes("student[@type=c]/name");// 模糊匹配
//        list9.forEach(s -> System.out.println(s.getStringValue()));
////		获取年龄超过20
//        List<Node> list10 = root.selectNodes("student[age>20]/name");// 模糊匹配
//        list10.forEach(s -> System.out.println(s.getStringValue()));
//    }
//}
