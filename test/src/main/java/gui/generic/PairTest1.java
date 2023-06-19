package gui.generic;

public class PairTest1 {
    public static void main(String[] args) {
        String[] words = {"Mary", "had", "a", "little", "lamb"};
        Pair<String> mm = ArrayAlg1.minMax(words);
        System.out.println("min = " + mm.getFirst());
        System.out.println("max = " + mm.getSecond());
    }
}

class ArrayAlg1 {
    public static Pair<String> minMax(String[] args) {
        if (args == null || args.length == 0) {
            return null;
        }
        String min = args[0];
        String max = args[0];
        for (String arg : args) {
            if (min.compareTo(arg) > 0) {
                min = arg;
            }
            if (max.compareTo(arg) < 0) {
                max = arg;
            }
        }
        return new Pair<>(min, max);
    }
}