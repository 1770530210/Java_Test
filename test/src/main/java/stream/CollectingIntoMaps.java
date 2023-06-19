package stream;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectingIntoMaps {
    public static class Person {
        private final int id;
        private final String name;

        public Person(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public static Stream<Person> people() {
            return Stream.of(new Person(1001, "Peter"), new Person(1002, "Paul"), new Person(1003, "Mary"));
        }

        public static void main(String[] args) {
            Map<Integer, String> idToName = people().collect(Collectors.toMap(Person::getId, Person::getName));
            System.out.println("idToName: " + idToName);

            Map<Integer, Person> idToPerson = people().collect(Collectors.toMap(Person::getId, Function.identity()));
            System.out.println("idToPerson: " + idToPerson);

            idToPerson = people().collect(Collectors.toMap(Person::getId, Function.identity()));
            System.out.println("idToPerson: " + idToPerson.getClass().getName() + idToPerson);

            Stream<Locale> locales = Stream.of(Locale.getAvailableLocales());
            Map<String, String> languageNames = locales.collect(
                    Collectors.toMap(
                            Locale::getDisplayLanguage,
                            loc -> loc.getDisplayLanguage(loc),
                            (existingValue, newValue) -> existingValue
                    )
            );
            System.out.println("languageNames: " + languageNames);

            locales = Stream.of(Locale.getAvailableLocales());
            Map<String, Set<String>> countryLanguageSets = locales.collect(
                    Collectors.toMap(
                            Locale::getDisplayCountry,
                            l -> Collections.singleton(l.getDisplayLanguage()),
                            (a, b) -> {
                                var union = new HashSet<>(a);
                                union.addAll(b);
                                return union;
                            }
                    )
            );
            System.out.println("countryLanguageSets: " + countryLanguageSets);
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "Person{" + "id=" + id + ", name='" + name + '\'' + '}';
        }
    }
}
