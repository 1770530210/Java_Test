package entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.io.FileWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Data
public class Person implements Serializable {
    private String name;
    private int age;
    private String gender;
    //    @JsonIgnore
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    public Person() {
    }

    public static void main(String[] args) throws Exception {
        test1();
        ObjectMapper mapper = new ObjectMapper();
        String json = "{\"name\":\"张三\",\"age\":20,\"gender\":\"男\",\"birthday\":\"2022-12-05\"}";
        Person person = mapper.readValue(json, Person.class);
        System.out.println(person);
    }

    //java对象转为JSON字符串
    public static void test1() throws Exception {
        //1、创建Person对象
        Person p = new Person();
//        p.setName("张三");
//        p.setAge(20);
//        p.setGender("男");
//        p.setBirthday(new Date());
        //2、创建Jackson的核心对象， ObjectMapper
        ObjectMapper mapper = new ObjectMapper();
        //3、转换
        /*
            转换：
                writeValue(参数1,obj);
                    参数1：
                        File：将obj对象转换诶JSON字符串，并保存到指定的文件中
                        Writer：将obj对象转换为JSON字符串，并将json数据填充到字符输出流中
                        OutputStream：将obj对象转换为JSON字符串，并将json数据填充到字节输出流中
                writeValueAsString(obj)：将对象转为json字符串

         */
        String json = mapper.writeValueAsString(p);
        System.out.println(json);

        //writeValue：将数据写到文件中
//        mapper.writeValue(new File("G://r.json"),p);
        mapper.writeValue(new FileWriter("r.json"), p);
    }

    public static void test3() throws Exception {
        //1、创建Person对象
        Person p = new Person();
        p.setName("张三");
        p.setAge(20);
        p.setGender("男");
        p.setBirthday(new Date());
        Person p1 = new Person();
        p1.setName("张三");
        p1.setAge(20);
        p1.setGender("男");
        p1.setBirthday(new Date());
        Person p2 = new Person();
        p2.setName("张三");
        p2.setAge(20);
        p2.setGender("男");
        p2.setBirthday(new Date());

        //创建list集合
        List<Person> list = new ArrayList<>();
        list.add(p);
        list.add(p1);
        list.add(p2);

        //转换
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(list);
        System.out.println(json);

        mapper.writeValue(new FileWriter("r.json"), list);
    }

    public static void test4() throws Exception {
        //1、创建Map对象
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "张三");
        map.put("age", "23");
        map.put("gender", "男");
        //转换
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(map);
        System.out.println(json);
    }

    @Override
    public String toString() {
        return "Person{" + "name='" + name + '\'' + ", age=" + age + ", gender='" + gender + '\'' + '}';
    }
}