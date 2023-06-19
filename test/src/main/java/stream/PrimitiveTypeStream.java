package stream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PrimitiveTypeStream {
    public static void show(String title, IntStream stream) {
        final int SIZE = 10;
        int[] firstElements = stream.limit(SIZE + 1).toArray();
        System.out.println(title + ": ");
        for (int i = 0; i < firstElements.length; i++) {
            if (i > 0) {
                System.out.print(", ");
            }
            if (i < SIZE) {
                System.out.print(firstElements[i]);
            } else {
                System.out.print("...");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) throws IOException {
        IntStream intStream1 = IntStream.generate(() -> (int) (Math.random() * 100));
        IntStream intStream2 = IntStream.range(5, 10);
        IntStream intStream3 = IntStream.rangeClosed(5, 10);
        show("is1", intStream1);
        show("is2", intStream2);
        show("is3", intStream3);

        Path path = Paths.get("a.txt");
        var contents = Files.readString(path);

        Stream<String> words = Stream.of(contents.split("\\PL+"));
        IntStream intStream4 = words.mapToInt(String::length);
        show("is4", intStream4);
        var sentence = " is the set of oct-onions.";
        System.out.println(sentence);
        IntStream codes = sentence.codePoints();
        System.out.println(codes.mapToObj(c -> String.format("%X ", c)).collect(Collectors.joining()));

        Stream<Integer> integers = IntStream.range(0, 100).boxed();
        IntStream intStream5 = integers.mapToInt(Integer::intValue);
        show("is5", intStream5);
    }
}
