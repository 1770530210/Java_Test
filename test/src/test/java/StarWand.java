import java.util.List;
import java.util.Map;

public class StarWand implements Equipment{
    public static final String name = "Star wand";
    private int length;
    private int price;
    private List<String> effect;

    public void setLength(int length) {
        this.length = length;
    }
 
    public void setPrice(int price) {
        this.price = price;
    }
 
    public void setEffect(List<String> effect) {
        this.effect = effect;
    }
 
    public String getName() {
        return name;
    }
 
    public int getLength() {
        return length;
    }
 
    public int getPrice() {
        return price;
    }
 
    public List<String> getEffect() {
        return effect;
    }
}
 

 
