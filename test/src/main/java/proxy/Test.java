package proxy;

public class Test {
    public static void main(String[] args) {
        Star proxy = StarProxy.createProxy(new BigStar("坤诩菜菜子"));
        proxy.dance();
//        System.out.println(proxy.sing("鸡你太美！"));
    }
}
