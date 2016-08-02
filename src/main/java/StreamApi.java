import dto.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by Adrian_Ferenc on 8/2/2016.
 */
public class StreamApi {

  private static List<Person> javaProgrammers = new ArrayList<Person>() {
    {
      add(new Person("Elsdon", "Jaycob", "Java programmer", "male", 43, 2000));
      add(new Person("Tamsen", "Brittany", "Java programmer", "female", 23, 1500));
      add(new Person("Floyd", "Donny", "Java programmer", "male", 33, 1800));
      add(new Person("Sindy", "Jonie", "Java programmer", "female", 32, 1600));
      add(new Person("Vere", "Hervey", "Java programmer", "male", 22, 1200));
      add(new Person("Maude", "Jaimie", "Java programmer", "female", 27, 1900));
      add(new Person("Shawn", "Randall", "Java programmer", "male", 30, 2300));
      add(new Person("Jayden", "Corrina", "Java programmer", "female", 35, 1700));
      add(new Person("Palmer", "Dene", "Java programmer", "male", 33, 2000));
      add(new Person("Addison", "Pam", "Java programmer", "female", 34, 1300));
    }
  };

  private static List<Person> phpProgrammers = new ArrayList<Person>() {
    {
      add(new Person("Jarrod", "Pace", "PHP programmer", "male", 34, 1550));
      add(new Person("Clarette", "Cicely", "PHP programmer", "female", 23, 1200));
      add(new Person("Victor", "Channing", "PHP programmer", "male", 32, 1600));
      add(new Person("Tori", "Sheryl", "PHP programmer", "female", 21, 1000));
      add(new Person("Osborne", "Shad", "PHP programmer", "male", 32, 1100));
      add(new Person("Rosalind", "Layla", "PHP programmer", "female", 25, 1300));
      add(new Person("Fraser", "Hewie", "PHP programmer", "male", 36, 1100));
      add(new Person("Quinn", "Tamara", "PHP programmer", "female", 21, 1000));
      add(new Person("Alvin", "Lance", "PHP programmer", "male", 38, 1600));
      add(new Person("Evonne", "Shari", "PHP programmer", "female", 40, 1800));
    }
  };

  public static void print_persons_example_1() {
    System.out.println("PROGRAMMERS: ");
    javaProgrammers.forEach(person -> System.out.printf("%s %s\n", person.getFirstName(), person.getLastName()));
    phpProgrammers.forEach(person -> System.out.printf("%s %s\n", person.getFirstName(), person.getLastName()));
  }

  public static void increase_salary_example_1() {
    javaProgrammers.forEach(person -> {
      int previousSalary = person.getSalary();
      person.setSalary(person.getSalary() / 100 * 5 + person.getSalary());
      System.out.printf("%s %s ", person.getFirstName(), person.getLastName());
      System.out.printf("%d -> %d\n", previousSalary, person.getSalary());
    });
  }

  public static void increase_salary_example_2() {
    Consumer<Person> giveRaise = person -> {
      int previousSalary = person.getSalary();
      person.setSalary(person.getSalary() / 100 * 5 + person.getSalary());
      System.out.printf("%s %s ", person.getFirstName(), person.getLastName());
      System.out.printf("%d -> %d\n", previousSalary, person.getSalary());
    };

    System.out.println("--- Java developers");
    javaProgrammers.forEach(giveRaise);
    System.out.println("--- PHP developers");
    phpProgrammers.forEach(giveRaise);
  }

  public static void filterBySalary_example_1() {
    javaProgrammers.stream().filter(person -> person.getSalary() > 1400).forEach(person -> System.out.printf("%s " +
        "%s, salary: %d\n", person
        .getFirstName(), person.getLastName(), person.getSalary()));
  }

  public static void manyFilters_example_1() {
    Predicate<Person> ageFilter = person -> person.getAge() > 30;
    Predicate<Person> salaryFilter = person -> person.getSalary() > 1400;
    Predicate<Person> genderFilter = person -> person.getGender().equals("female");

    System.out.println("--- Java developers");
    javaProgrammers.stream().filter(ageFilter).filter(salaryFilter).filter(genderFilter).forEach(person -> System.out
        .printf("%s %s, age: %d, salary: %d, gender: %s\n", person.getFirstName(), person.getLastName(), person
            .getAge(), person.getSalary(), person.getGender()));

    System.out.println("--- PHP developers");
    phpProgrammers.stream().filter(ageFilter).filter(salaryFilter).filter(genderFilter).forEach(person -> System.out
        .printf("%s %s, age: %d, salary: %d, gender: %s\n", person.getFirstName(), person.getLastName(), person
            .getAge(), person.getSalary(), person.getGender()));
  }

  public static void limits_example_1() {
    javaProgrammers.stream().filter(person -> person.getGender().equals("female")).limit(3).forEach(person -> System
        .out.printf("%s %s, gender: %s\n", person.getFirstName(), person.getLastName(), person.getGender()));
  }

  public static void sort_example_1() {
    List<Person> sortedJavaProgrammers = javaProgrammers.stream().sorted((p1, p2) -> p1.getFirstName().compareTo
        (p2.getFirstName())).collect(Collectors.toList());
    sortedJavaProgrammers.forEach(person -> System.out.printf("%s %s\n", person.getFirstName(), person.getLastName()));
  }

  public static void min_example_1() {
    Person person = javaProgrammers.stream().min((p1, p2) -> p1.getSalary() - p2.getSalary()).get();
    System.out.printf("%s %s, salary: %d", person.getFirstName(), person.getLastName(), person.getSalary());
  }

  public static void max_example_1() {
    Person person = javaProgrammers.stream().max((p1, p2) -> p1.getSalary() - p2.getSalary()).get();
    System.out.printf("%s %s, salary: %d", person.getFirstName(), person.getLastName(), person.getSalary());
  }

  public static void collect_example_1() {
    String persons = javaProgrammers
        .stream()
        .sorted((p1, p2) -> p1.getFirstName().compareTo(p2.getFirstName()))
        .map(Person::getFirstName)
        .collect(Collectors.joining("; "));
    System.out.println(persons);
  }

  public static void summaryData_example_1() {
//Get count, min, max, sum, and average for numbers
    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    IntSummaryStatistics stats = numbers
        .stream()
        .mapToInt(x -> x)
        .summaryStatistics();
    System.out.println("Count : " + stats.getCount());
    System.out.println("Highest number in List : " + stats.getMax());
    System.out.println("Lowest number in List : " + stats.getMin());
    System.out.println("Sum of all numbers : " + stats.getSum());
    System.out.println("Average of all numbers : " + stats.getAverage());
  }

  public static void main(String[] args) {
    int i = 1;
//    System.out.println("Example "+i++);
//    print_persons_example_1();

//    System.out.println("Example "+i++);
//    increase_salary_example_1();

//    System.out.println("Example "+i++);
//    increase_salary_example_2();

//    System.out.println("Example "+i++);
//    filterBySalary_example_1();

//    System.out.println("Example "+i++);
//    manyFilters_example_1();

//    System.out.println("Example "+i++);
//    limits_example_1();

//    System.out.println("Example "+i++);
//    sort_example_1();

//    System.out.println("Example "+i++);
//    min_example_1();

//    System.out.println("Example "+i++);
//    max_example_1();

//    System.out.println("Example " + i++);
//    collect_example_1();

    System.out.println("Example " + i++);
    summaryData_example_1();
  }

}
