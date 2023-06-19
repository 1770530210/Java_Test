package stream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ParallelSteams {
    public static void main(String[] args) throws IOException {
        var contents = Files.readString(Path.of("a.txt"));
        List<String> wordList = List.of(contents.split("\\PL+"));

        var shortWords = new int[10];
        wordList.parallelStream().forEach(s -> {
            if (s.length() < 10) {
                shortWords[s.length()]++;
            }
            System.out.println(Arrays.toString(shortWords));
        });

        Map<Integer, List<String>> result = wordList.parallelStream().collect(Collectors.groupingByConcurrent(String::length));

        System.out.println(result.get(14));

        result = wordList.parallelStream().collect(Collectors.groupingByConcurrent(String::length));
        System.out.println(result.get(14));

        Map<Integer, Long> wordCounts = wordList.parallelStream().collect(Collectors.groupingByConcurrent(String::length, Collectors.counting()));
        System.out.println(wordCounts);
    }
}
