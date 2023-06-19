package stream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DownstreamCollectors {
    public static void main(String[] args) throws IOException {
        Stream<Locale> locales = Stream.of(Locale.getAvailableLocales());
        Map<String, Set<Locale>> countryToLocaleSet = locales.collect(Collectors.groupingBy(Locale::getCountry, Collectors.toSet()));
        System.out.println("countryToLocaleSet: " + countryToLocaleSet);

        Map<String, Long> countryToLocaleCounts = locales.collect(Collectors.groupingBy(Locale::getCountry, Collectors.counting()));
        System.out.println("countryToLocaleCounts: " + countryToLocaleCounts);

        Stream<City> cities = readCities("cities.txt");
        Map<String, Integer> stateToCityPopulation = cities.collect(Collectors.groupingBy(City::getState, Collectors.summingInt(City::getPopulation)));//计算国家中每个城市人口总数
        System.out.println("stateToCityPopulation: " + stateToCityPopulation);

        Map<String, Optional<String>> stateToLongestCityName = cities.collect(Collectors.groupingBy(City::getState, Collectors.mapping(City::getName, Collectors.maxBy(Comparator.comparing(String::length)))));//可以产生每个州中人口最大的城市
        System.out.println("stateToLongestCityName: " + stateToLongestCityName);

        Map<String, Set<String>> countyToLanguages = locales.collect(Collectors.groupingBy(Locale::getDisplayCountry, Collectors.mapping(Locale::getDisplayLanguage, Collectors.toSet())));
        System.out.println("countyToLanguages: " + countyToLanguages);

        Map<String, IntSummaryStatistics> stateToCityPopulationSummary = cities
                .collect(Collectors.groupingBy(City::getState, Collectors.summarizingInt(City::getPopulation)));
        System.out.println(stateToCityPopulationSummary.get("NY"));

        Map<String, String> stateToCityNames = cities.collect(Collectors.groupingBy(
                City::getState,
                Collectors.reducing("", City::getName, (s, t) -> s.length() == 0 ? t : s + ", " + t)
        ));

        stateToCityNames = cities.collect(Collectors.groupingBy(
                City::getState,
                Collectors.mapping(City::getName, Collectors.joining(", "))
        ));
        System.out.println("stateToCityNames: " + stateToCityNames);

    }

    public static Stream<City> readCities(String fileName) throws IOException {
        return Files.lines(Paths.get(fileName))
                .map(l -> l.split(", "))
                .map(a -> new City(a[0], a[1], Integer.parseInt(a[2])));
    }

    public static class City {
        private final String name;
        private final String state;
        private final int population;

        public City(String name, String state, int population) {
            this.name = name;
            this.state = state;
            this.population = population;
        }

        public String getName() {
            return name;
        }

        public String getState() {
            return state;
        }

        public int getPopulation() {
            return population;
        }
    }
}
