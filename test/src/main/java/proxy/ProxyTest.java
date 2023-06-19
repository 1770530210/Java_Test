package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Random;

public class ProxyTest {
    public static void main(String[] args) {
        var elements = new Object[1000];
        for (int i = 0; i < elements.length; i++) {
            Integer value = i + 1;
            var handler = new TraceHandler(value);
//          Class proxyClass=Proxy.getProxyClass(null,interfaces);//返回实现指定接口的代理类
            Object proxy = Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{Comparable.class}, handler);
//          构造实现指定接口的代理类的一个新实例.所有方法都调用给定处理器对象的invoke方法
//          boolean isProxy=Proxy.isProxyClass(proxyClass);//如果它是一个代理类就返回true

            elements[i] = proxy;
        }
        Integer key = new Random().nextInt(elements.length) + 1;
        int result = Arrays.binarySearch(elements, key);
        if (result >= 0) System.out.println(elements[result]);
    }
}

class TraceHandler implements InvocationHandler {
    private final Object target;

    public TraceHandler(Object target) {
        this.target = target;
    }

    /*
    定义这个方法包含一个动作,你希望只要在代理对象中调用一个方法就完成这个动作
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(target);
        System.out.print(target.getClass().getName() + "." + method.getName() + "(");//输出代理的方法
        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                System.out.print(args[i]);
                if (i < args.length - 1) System.out.print(", ");
            }
        }
        System.out.println(")");

        return method.invoke(target, args);
    }
}
