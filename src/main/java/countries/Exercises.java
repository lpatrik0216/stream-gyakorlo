package countries;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class Exercises extends CountryRepository{
    public void printNameOfCountries(){
        getAll().stream()
                .map(Country::name)
                .forEach(System.out::println);
    }

    public void printCapitalsSorted(){
        getAll().stream()
                .map(Country::capital)
                .sorted(Comparator.nullsFirst(Comparator.naturalOrder()))
                .forEach(System.out::println);
    }

    public void printCapitalsSortedReversed(){
        getAll().stream()
                .map(Country::capital)
                .sorted(Comparator.nullsLast(Comparator.reverseOrder()))
                .forEach(System.out::println);
    }

    public long getMostPopulation(){
        return getAll().stream()
                .mapToLong(Country::population)
                .max()
                .getAsLong();
    }

    public double getPopulationAverage(){
        return getAll().stream()
                .mapToLong(Country::population)
                .average()
                .getAsDouble();
    }

    public LongSummaryStatistics getSummaryStatisticsOfPopulation(){
        return getAll().stream()
                .mapToLong(Country::population)
                .summaryStatistics();
    }

    public void printNamesOfEuropeanCountries(){
        getAll().stream()
                .filter(country -> country.region().equals(Region.EUROPE))
                .forEach(System.out::println);
    }

    public long getNumberOfEuropeanCountries(){
        return getAll().stream()
                .filter(country -> country.region().equals(Region.EUROPE))
                .count();
    }

    public long getNumberOfIndependentCountries(){
        return getAll().stream()
                .filter(country -> country.independent())
                .count();
    }

    public void printCountriesWithPopulationLessThanHundred(){
        getAll().stream().filter(country -> country.population()<100).forEach(System.out::println);
    }

    public void printNamesOfCountriesWithPopulationLessThanHundred(){
        getAll().stream().
                filter(country -> country.population()<100)
                .map(Country::name)
                .forEach(System.out::println);
    }

    public long sumOfEuropesPopulation(){
        return getAll().stream()
                .filter(country -> country.region().equals(Region.EUROPE))
                .mapToLong(Country::population)
                .sum();
    }

    public void printEuropeanCountriesPopulationsInOrder(){
        getAll().stream()
                .filter(country -> country.region().equals(Region.EUROPE))
                .mapToLong(Country::population)
                .sorted()
                .forEach(System.out::println);
    }

    public void printEuropeanCountriesPopulationsInReverseOrder(){
        getAll().stream()
                .filter(country -> country.region().equals(Region.EUROPE))
                .mapToLong(Country::population)
                .boxed()
                .sorted(Comparator.reverseOrder())
                .forEach(System.out::println);
    }

    public Country getMostPopulatedEuropeanCountry(){
        return getAll().stream()
                .max(Comparator.comparing(Country::population))
                .get();
    }

    public String getNameOfMostPopulatedEuropeanCountry(){
        return getAll().stream()
                .max(Comparator.comparing(Country::population))
                .map(Country::name)
                .get();
    }

    public void printNameOfFirstFiveCountries(){
        getAll().stream()
                .map(Country::name)
                .limit(5)
                .forEach(System.out::println);
    }

    public boolean isThereACountryWithZeroPopulation(){
        return getAll().stream()
                .anyMatch(country -> country.population()==0);
    }

    public boolean doesAllCountriesHaveOneTimezone(){
        return getAll().stream()
                .allMatch(country -> country.timezones().size()>0);
    }

    public Optional<Country> getFirstCountryWhoseNameStartsWithH(){
        return getAll().stream()
                .filter(country -> {String t=country.name(); return t.charAt(0)=='H';}) //Totally unneccessary, just wanted to practice more complex variants of lambda function
                .findFirst();
    }

    public long getNumberOfDistinctTimezones(){
        return getAll().stream().
                filter(country -> country.region() == Region.EUROPE)
                .flatMap(country -> country.timezones().stream())
                .distinct()
                .count();
    }
    public void printDistinctTimezones(){
        getAll().stream().
                filter(country -> country.region() == Region.EUROPE)
                .flatMap(country -> country.timezones().stream())
                .distinct()
                .forEach(System.out::println);
    }

    public void printCountriesAndPopulation(){
        getAll().stream()
                .sorted(Comparator.comparingLong(Country::population).reversed())
                .forEach(country -> System.out.printf("%s: %d%n", country.name(),country.population()));
    }

    public int getHowLongTheLongestCountryNameIs(){
        return getAll().stream()
                .map(Country::name)
                .map(String::length)
                .sorted(Comparator.reverseOrder())
                .findFirst()
                .get();

    }

    public void printCountryCapitalsSortedByLength(){
        getAll().stream()
                .map(Country::capital)
                .sorted(Comparator.nullsLast(Comparator.comparing(String::length)))
                .forEach(System.out::println);
    }

    public void printCountryCapitalsSortedTwice(){
        getAll().stream()
                .map(Country::capital)
                .sorted(Comparator.nullsLast(Comparator.comparing(String::length)).thenComparing(Comparator.naturalOrder()))
                .forEach(System.out::println);
    }


    public boolean isThereACountryWithSubstringIsland(){
        return getAll().stream()
                    .anyMatch(country -> {var t=country.name().toLowerCase(); return t.contains("island");});
    }

    public Country getFirstCountryWithSubstrIsland(){
        return getAll().stream()
                .filter(country -> {var t=country.name().toLowerCase(); return t.contains("island");})
                .findFirst()
                .get();
    }

    public void printCountriesWithSameFirstAndLastLetters(){
        getAll().stream()
                .filter(country -> {var t=country.name().toLowerCase(); return t.charAt(0)==t.charAt(t.length()-1);})
                .map(Country::name)
                .forEach(System.out::println);
    }

    public void printTenSmallestPopulation(){
                getAll().stream()
                        .sorted(Comparator.comparing(Country::population))
                        .mapToLong(Country::population)
                        .limit(10)
                        .forEach(System.out::println);
    }

    public void printTenLeastPopulatedCountries(){
        getAll().stream()
                .sorted(Comparator.comparing(Country::population))
                .map(Country::name)
                .limit(10)
                .forEach(System.out::println);
    }

    public IntSummaryStatistics getSummaryAboutNumberOfTranslations(){
        return getAll().stream()
                .mapToInt(country -> country.translations().size())
                .summaryStatistics();
    }

    public void printCountryNamesSortedByNumberOfTimezones(){
        getAll().stream()
                .sorted(Comparator.comparing(country -> country.timezones().size()))
                .map(Country::name)
                .forEach(System.out::println);
    }

    public void printCountriesWithNumberOfTimezones(){
        getAll().stream()
                .sorted(Comparator.comparing(country -> country.timezones().size()))
                .forEach(country -> System.out.printf("%s : %d%n", country.name(),country.timezones().size()));
    }

    public long getCountriesWithNoFarsi(){
        return getAll().stream()
                .filter(country -> !country.translations().containsKey("fa"))
                .count();
    }

    public void printCountriesWithNullArea(){
        getAll().stream()
                .filter(country -> country.area() == null)
                .map(Country::name)
                .forEach(System.out::println);
    }

    public void printAllTranslationCode(){
        getAll().stream()
                .flatMap(country -> country.translations().keySet().stream())
                .distinct()
                .sorted(Comparator.naturalOrder())
                .forEach(System.out::println);
    }

    public OptionalDouble getAverageLengthOfCountryNames(){
        return getAll().stream()
                .mapToLong(country -> country.name().length())
                .average();
    }

    public void printRegionOfNullAreaCountries(){
        getAll().stream()
                .filter(country -> country.area()==null)
                .map(Country::region)
                .distinct()
                .forEach(System.out::println);
    }

    public Optional<String> getCountryWithBiggestArea(){
        return getAll().stream()
                .filter(country -> country.area()!=null)
                .sorted(Comparator.comparing(Country::area).reversed())
                .map(Country::name)
                .findFirst();
    }

    public void printCountriesWithAreasLessThanOne(){
        getAll().stream()
                .filter(country -> country.area()!=null && country.area().compareTo(BigDecimal.ONE)<0)
                .map(Country::name)
                .forEach(System.out::println);
    }

    public void printAllTimezonesOfEuropeAndAsia(){
        getAll().stream()
                .filter(country -> country.region()==Region.EUROPE || country.region()==Region.ASIA)
                .flatMap(country -> country.timezones().stream())
                .distinct()
                .forEach(System.out::println);
    }

    public Country getCountryWithBiggestPopulation(){
        return getAll().stream()
                .filter(Objects::nonNull)
                .max(Comparator.comparing(Country::population))
                .get();

    }

    public void printNamesOfNullCountries(){
        getAll().stream()
                .filter(country -> country.area()==null)
                .map(Country::name)
                .forEach(System.out::println);
    }

    public DoubleSummaryStatistics getSummaryAboutAreasOfCountries(){
        return getAll().stream()
                .map(Country::area)
                .filter(Objects::nonNull)
                .mapToDouble(BigDecimal::doubleValue)
                .summaryStatistics();

    }

    public double getSumOfCountriesArea(){
        return getAll().stream()
                .filter(country -> country.area()!=null)
                .mapToDouble(country -> country.area().doubleValue())
                .sum();

    }

    public String getCountriesWithCommas(){
        return getAll().stream()
                .map(Country::name)
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.joining(","));
    }

    public Map<String,String> getCountryNamesAndCodes(){
        return getAll().stream()
                .collect(Collectors.toMap(Country::code,Country::name));
    }

    public Map<String,Country> getCountriesByCodes(){
        return getAll().stream()
                .collect(Collectors.toMap(Country::code, Function.identity()));
    }

    public void printCountriesWithPopulationLessThanHungary(){
        Map<String,Country> countryMap = getAll().stream()
                .collect(Collectors.toMap(Country::code, Function.identity()));
        Country hungary= countryMap.get("HU");
        getAll().stream()
                .filter(country -> country.population()<= hungary.population())
                .sorted(Comparator.comparingLong(Country::population).reversed())
                .forEach(country -> System.out.printf("%s:%d%n", country.name(), country.population()));
    }

    public Map<Boolean, Long> getNumberOfEuropeanAndNonEuropeanCountries(){
        return getAll().stream()
                .collect(Collectors.partitioningBy(country -> country.region().equals(Region.EUROPE), counting()));
    }

    public Map<Region,List<Country>> getCountriesByRegion(){
        return getAll().stream()
                .collect(Collectors.groupingBy(Country::region));
    }

    public Map<Region,Long> getNumberOfCountriesByRegion(){
        return getAll().stream()
                .collect(Collectors.groupingBy(Country::region, counting()));
    }

    public void printNumberOfCountriesByRegion(){
        getAll().stream()
                .collect(Collectors.groupingBy(Country::region, counting()))
                .forEach((region, count) -> System.out.printf("%s:%d%n", region,count));
    }

    public Map<Region,Double> getAveragePopulationsOfRegions(){
        return getAll().stream()
                .collect(Collectors.groupingBy(Country::region, averagingDouble(Country::population)));
    }

    public Map<Region,Optional<Country>> getMaxPopulatedCountriesOfRegions(){
        return getAll().stream()
                .collect(Collectors.groupingBy(Country::region, maxBy(Comparator.comparing(Country::population))));
    }

    public Map<Region,Optional<Long>> getMaxPopulationsOfRegions(){
        return getAll().stream()
                .collect(Collectors.groupingBy(Country::region, mapping(Country::population,maxBy(Long::compare))));
    }

    public Map<Region, Optional<String>> getLongestCountryNameByRegion(){
        return getAll().stream()
                .collect(Collectors.groupingBy(Country::region, mapping(Country::name, maxBy(Comparator.comparing(String::length)))));
    }

    public Map<Character, Long> getHowManyCountriesStartWithSameLetter(){
        return getAll().stream()
                .collect(Collectors.groupingBy(country -> country.name().charAt(0) , counting()));
    }

    public boolean isThereTwoOrMoreCountriesWithSameSize(){
        return getAll().stream()
                .map(Country::area)
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(Function.identity(), counting()))
                .entrySet()
                .stream()
                .anyMatch(e -> e.getValue() > 1);
    }

    public static void main(String[] args) {
        Exercises manager = new Exercises();

        //manager.printNameOfCountries(); //1.1
        //manager.printCapitalsSorted(); //1.2
        //manager.printCapitalsSortedReversed(); //1.3
        //System.out.println(manager.getMostPopulation()); //1.4
        //System.out.println(manager.getPopulationAverage()); //1.5
        //System.out.println(manager.getSummaryStatisticsOfPopulation()); //1.6
        //manager.printNamesOfEuropeanCountries(); //1.7
        //System.out.println(manager.getNumberOfEuropeanCountries()); //1.8
        //System.out.println(manager.getNumberOfIndependentCountries()); //1.9
        //manager.printCountriesWithPopulationLessThanHundred(); //1.10
        //manager.printNamesOfCountriesWithPopulationLessThanHundred(); //1.11
        //System.out.println(manager.sumOfEuropesPopulation()); //1.12
        //manager.printEuropeanCountriesPopulationsInOrder(); //1.13
        //manager.printEuropeanCountriesPopulationsInReverseOrder(); //1.14
        //System.out.println(manager.getMostPopulatedEuropeanCountry()); //1.15
        //System.out.println(manager.getNameOfMostPopulatedEuropeanCountry()); //1.16
        //manager.printNameOfFirstFiveCountries(); //1.17
        //System.out.println(manager.isThereACountryWithZeroPopulation()); //1.18
        //System.out.println(manager.doesAllCountriesHaveOneTimezone()); //1.19
        //manager.getFirstCountryWhoseNameStartsWithH().ifPresent(System.out::println); //1.20
        //System.out.println(manager.getNumberOfDistinctTimezones()); //1.21
        //manager.printDistinctTimezones(); //1.22
        //manager.printCountriesAndPopulation(); //1.23
        //System.out.println(manager.getHowLongTheLongestCountryNameIs()); //1.24
        //manager.printCountryCapitalsSortedByLength(); //1.25
        //manager.printCountryCapitalsSortedTwice(); //1.26


        //System.out.println(manager.isThereACountryWithSubstringIsland());//2.1
        //System.out.println(manager.getFirstCountryWithSubstrIsland()); //2.2
        //manager.printCountriesWithSameFirstAndLastLetters(); //2.3
        //manager.printTenSmallestPopulation(); //2.4
        //manager.printTenLeastPopulatedCountries(); //2.5
        //System.out.println(manager.getSummaryAboutNumberOfTranslations()); //2.6
        //manager.printCountryNamesSortedByNumberOfTimezones(); //2.7
        //manager.printCountriesWithNumberOfTimezones(); //2.8
        //System.out.println(manager.getCountriesWithNoFarsi()); //2.9
        //manager.printCountriesWithNullArea(); //2.10
        //manager.printAllTranslationCode(); //2.11
        //manager.getAverageLengthOfCountryNames().ifPresent(System.out::println); //2.12
        //manager.printRegionOfNullAreaCountries(); //2.13
        //manager.getCountryWithBiggestArea().ifPresent(System.out::println); //2.14
        //manager.printCountriesWithAreasLessThanOne(); //2.15
        //manager.printAllTimezonesOfEuropeAndAsia(); //2.16

        //System.out.println(manager.getCountryWithBiggestPopulation()); //3.1
        //manager.printNamesOfNullCountries(); //3.2
        //System.out.println(manager.getSummaryAboutAreasOfCountries()); //3.3
        //System.out.println(manager.getSumOfCountriesArea()); //3.4
        //System.out.println(manager.getCountriesWithCommas()); //3.5
        //System.out.println(manager.getCountryNamesAndCodes()); //3.6
        //Map<String,Country> countryMap = manager.getCountriesByCodes(); //3.7
        //System.out.println(countryMap.get("HU")); //3.7 extension
        //manager.printCountriesWithPopulationLessThanHungary(); //3.8 - Unlikely in exam since not only a stream pipeline in the methods body.
        //System.out.println(manager.getNumberOfEuropeanAndNonEuropeanCountries()); //3.9
        //System.out.println(manager.getCountriesByRegion()); //3.10
        //System.out.println(manager.getNumberOfCountriesByRegion()); //3.11
        //manager.printNumberOfCountriesByRegion(); //3.12
        //System.out.println(manager.getAveragePopulationsOfRegions()); //3.13
        //System.out.println(manager.getMaxPopulatedCountriesOfRegions()); //3.14
        //System.out.println(manager.getMaxPopulationsOfRegions()); //3.15
        //System.out.println(manager.getLongestCountryNameByRegion()); //3.16
        //System.out.println(manager.getHowManyCountriesStartWithSameLetter()); //3.17
        //System.out.println(manager.isThereTwoOrMoreCountriesWithSameSize()); //3.18
        }
}
