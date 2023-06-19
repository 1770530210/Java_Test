//package xml;
//
//import org.dom4j.Document;
//import org.dom4j.Element;
//import org.dom4j.io.SAXReader;
//
//import java.io.InputStream;
//import java.util.List;
//
//public class TestRead {
//    public static void main(String[] args) throws Exception {
//        InputStream is = TestRead.class.getClassLoader().getResourceAsStream("UIData.xml");
//        SAXReader reader = new SAXReader();
//        Document document = reader.read(is);
//        Element root = document.getRootElement();
//
//        List<Element> list = root.elements();
//        list.forEach(e -> {
//            root.remove(e);
//        });
////        System.out.println(list.size());
////        list.forEach(s -> System.out.println(s));
//        for (Element element : list) {
//            System.out.println(element.getName());//元素标签名称
//            System.out.println(element.getName() + "属性id:" + element.attribute("id").getValue());
//            System.out.println(element.attributeValue("id"));
//            System.out.println(element.getName() + "属性width:" + element.attribute("width").getValue());
//            System.out.println(element.getName() + "属性height:" + element.attribute("height").getValue());
//            List<Element> list2 = element.elements();//子节点
//            for (Element e : list2) {
//                e.attributes();
//                System.out.println(element.getName() + "属性id:" + e.attribute("id").getValue());
//
////                System.out.println(element.getName()+"属性width:"+e.attribute("width").getValue());
////                System.out.println(element.getName()+"属性height:"+e.attribute("height").getValue());
//
//                System.out.println("\t" + e.getName() + ":");//元素标签名称
////                System.out.println(e.getData());//元素内容
//            }
//        }
////		List<Node> backModel=root.selectNodes("backModel");
////		Element element = (Element) root.selectObject("backModel/subModel[@id=1]/icon");
////		System.out.println(element.attributeValue("name"));
//
//    }
//}
