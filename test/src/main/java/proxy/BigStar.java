package proxy;

public class BigStar implements Star {
    private String name;

    public BigStar() {
    }

    public BigStar(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String sing(String song) {
        System.out.println(this.name + " 唱 " + song);
        return "谢谢";
    }

    @Override
    public void dance() {
        System.out.println(this.name + " 跳舞");
    }
}
