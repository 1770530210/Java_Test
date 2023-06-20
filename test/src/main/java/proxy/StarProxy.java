package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class StarProxy {


    /**
     * 给明星对象，创建一个代理
     *
     * @param bigStar
     * @return 给明星创建的代理
     */
    public static Star createProxy(BigStar bigStar) {
        Star star = (Star) Proxy.newProxyInstance(
                StarProxy.class.getClassLoader(), //类加载器,指定使用哪个类加载器，去加载生成的代理类
                new Class[]{Star.class},//代理接口
                new InvocationHandler() {//实际行为
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        /*
                        参数1：代理的对象-->star
                        参数2：要运行的方法
                        参数3：调用method时，传递的实参
                         */
                        if ("sing".equals(method.getName())) {
                            System.out.println("收钱，唱歌！");
                        } else if ("dance".equals(method.getName())) {
                            System.out.println("收钱，跳舞！");
                        }
                        return method.invoke(bigStar, args);
                    }
                });
        return star;
    }
}
