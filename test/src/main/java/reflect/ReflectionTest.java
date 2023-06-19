package reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Scanner;

public class ReflectionTest {
    @SuppressWarnings("rawtypes") // 忽略编译未检查泛型（这行注解新手就不要去深究,不加不会影响使用）
    public static void main(String[] args) throws ClassNotFoundException {
        String name;
        var in = new Scanner(System.in);
        System.out.println("Enter class name (e.g.java.util.Date): ");
        name = in.nextLine();
        Class class1 = Class.forName(name);// 获取Class对象
        Class superClass = class1.getSuperclass();// 获取其父类对象
        String modefiers = Modifier.toString(class1.getModifiers());// 返回一个整数，描述（类、方法、字段）的修饰符,包括4种访问限制符，static，final,transient等
        if (modefiers.length() > 0) {
            System.out.print(modefiers + " ");// 如果修饰符不为空（即defualt）
        }
        System.out.print("class " + name);
        if (superClass != null && superClass != Object.class) {
            System.out.print(" extends " + superClass.getName());// 输出继承的父类
        }
        System.out.print("\n{\n");

        printConstructors(class1);// 输出所有构造器方法
        System.out.println();
        printMethods(class1);// 输出所有常规方法
        System.out.println();
        printField(class1);// 输出所有字段
        System.out.println("}");
        in.close();
    }

    @SuppressWarnings("rawtypes")
    public static void printConstructors(Class c) {
        Constructor[] constructors = c.getDeclaredConstructors();// 获取此类中定义的所有构造器
        for (Constructor constructor : constructors) {
            String name = constructor.getName();
            System.out.print("	");
            String modifiers = Modifier.toString(constructor.getModifiers());
            if (modifiers.length() > 0) {
                System.out.print(modifiers + " ");// 输出修饰符
            }
            System.out.print(name + "(");
            Class[] paramTypes = constructor.getParameterTypes();
            for (int i = 0; i < paramTypes.length; i++) {
                if (i > 0) {
                    System.out.print(", ");
                }
                System.out.print(paramTypes[i].getName());// 输出变量名称
            }
            System.out.print(") ");
            Class[] exceptionTypes = constructor.getExceptionTypes();
            if (exceptionTypes.length > 0) {
                System.out.print("throws ");
            }
            for (Class exceptionType : exceptionTypes) {
                System.out.print(exceptionType.getName() + " ");// 输出变量名称
            }
            System.out.println(";");

        }

    }

    @SuppressWarnings("rawtypes")
    public static void printMethods(Class c) {
        Method[] methods = c.getDeclaredMethods();// 获取此类中定义的所有方法
        for (Method method : methods) {
            Class retType = method.getReturnType();// 获取返回类型
            String name = method.getName();
            System.out.print("	");
            String modifiers = Modifier.toString(method.getModifiers());
            if (modifiers.length() > 0) {
                System.out.print(modifiers + " ");// 输出修饰符
            }
            System.out.print(retType.getName() + " " + name + "(");

            Class[] paramTypes = method.getParameterTypes();
            for (int i = 0; i < paramTypes.length; i++) {
                if (i > 0) {
                    System.out.print(", ");
                }
                System.out.print(paramTypes[i].getName());// 输出变量名称
            }
            System.out.println(");");

        }
    }

    @SuppressWarnings("rawtypes")
    public static void printField(Class c) {
        Field[] fields = c.getDeclaredFields();
        for (Field field : fields) {
            Class type = field.getType();
            String name = field.getName();
            System.out.print("	");
            String modifiers = Modifier.toString(field.getModifiers());
            if (modifiers.length() > 0) {
                System.out.print(modifiers + " ");// 输出修饰符
            }
            System.out.println(type.getName() + " " + name + ";");// 输出变量类型和变量名称

        }
    }
}
