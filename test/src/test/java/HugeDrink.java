

public class HugeDrink implements Equipment {
    public static final String name = "Huge drink";
    private int capacity;
    private String effect;

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getEffect() {
        return effect;
    }
}
