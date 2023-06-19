import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Main {
    static {
//        System.setProperty("DB", "mysql");//可以作为全局变量，在任何地方使用
    }

    public static void main(String[] args) throws IOException, FontFormatException {
//        输出所有的字体
//        System.out.println(Arrays.toString(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames()));
//        System.out.println(Integer.toHexString(1352165));
//        System.out.println(Integer.parseInt("fff", 16));
//        System.out.println(Integer.toHexString(20259173));
//
//        Font font=Font.createFont(Font.TRUETYPE_FONT,new FileInputStream(new File("C:\\Windows\\Fonts\\simkai.ttf")));
//        Font font=new Font("宋体",Font.BOLD,16);
//        System.out.println(font);
//        System.out.println(font.getAttributes());
//        System.out.println(font.getFontName());
//        System.out.println(font.getFamily());
//        System.out.println(font.getItalicAngle());
//        System.out.println(font.getMissingGlyphCode());
//        System.out.println(font.getName());
//        System.out.println(font.getPSName());
//        System.out.println(font.getSize());
//        System.out.println(font.getSize2D());
//        System.out.println(font.getStyle());
//        System.out.println(font.getTransform());
//        A a = new A(10);
//        System.out.println(a);

//        ArrayList<String> list2 = new ArrayList<>();
//        Collections.addAll(list2,"a-男", "b-女", "c-男", "d-男", "e-男", "f-男", "g-女", "h-男", "i-男", "j-女", "k-女");

//        List<String> list = List.of("a-男", "b-女", "c-男", "d-男", "e-男", "f-男", "g-女", "h-男", "i-男", "j-女", "k-女", "c-男");
//        //统计
//        //获取所有男性
//        System.out.println(list.stream().filter(s -> "男".equals(s.split("-")[1])).count());
//        //获取所有男性
//        list.stream().filter(s -> "男".equals(s.split("-")[1])).forEach(System.out::print);
//        System.out.println();
//        //收集List
//        System.out.println(list.stream().filter(s -> "男".equals(s.split("-")[1])).collect(Collectors.toList()));
//        //收集Set
//        System.out.println(list.stream().filter(s -> "男".equals(s.split("-")[1])).collect(Collectors.toSet()));
//        //收集Map,键不能有重复
//        System.out.println(list.stream().filter(s -> "男".equals(s.split("-")[1])).collect(Collectors.toMap(s -> s.split("-")[0], s -> s.split("-")[1])));

//        System.setProperty("update_every_flag","0");
//        System.out.println(System.getProperty("DB"));
        // 指定路径和文件名
        String filePath = "D:\\runtime-win.product\\a\\.setting\\image.xml";
        File file = new File(filePath);


// 检查目录是否存在
        File directory = file.getParentFile();
        if (!directory.exists()) {
            // 目录不存在，创建目录及其父级目录、若非必要则不用 mkdirs()，避免误操作
            boolean success = directory.mkdirs();
            if (!success) {
                // 创建目录失败，抛出异常或显示错误信息
                throw new RuntimeException("Failed to create directory");
            }
        }

// 创建文件
        boolean success = false;
        try {
            success = file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!success) {
            // 创建文件失败，抛出异常或显示错误信息
            throw new RuntimeException("Failed to create file");
        }

    }
}

//    static class A {
//        int a = 0;
//
//        public A(int b) {
//            a = b;
//        }
//
//        @Override
//        public String toString() {
//            return "A{" +
//                    "a=" + a +
//                    '}';
//        }
//    }
//}
