package collect;

import entity.Employee;

import java.util.HashMap;

public class MapTest {
    public static void main(String[] args) {
//        TreeMap
//        WeakHashMap
        var staff = new HashMap<String, Employee>();
        staff.put("144-25-5464", new Employee("Ame Lee"));
        staff.put("567-24-2546", new Employee("Harry Hacker"));
        staff.put("157-62-7935", new Employee("Gary Cooper"));
        staff.put("456-62-5527", new Employee("Francesca Cruz"));
        System.out.println(staff);
        staff.remove("567-24-2546");
        System.out.println(staff.put("456-62-5527", new Employee("Francesca Miller")));
        System.out.println(staff.get("157-62-7935"));
        System.out.println(staff.get("123456789"));
        System.out.println(staff.getOrDefault("hello world", new Employee("hello world")));
        staff.forEach((k, v) -> {
            System.out.println("key=" + k + ", value=" + v);
        });

    }
}
