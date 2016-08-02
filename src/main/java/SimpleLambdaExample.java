import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Adrian_Ferenc on 8/2/2016.
 */
public class SimpleLambdaExample {

  //Using lambda expression and functional operations
  public static void forEach_example_1(){
    String[] playersArray = {"Player 1", "Player 2", "Player 3", "Player 4"};
    List<String> players = Arrays.asList(playersArray);

    players.forEach(player -> System.out.println(player));
  }

  //Using double colon operator in Java 8
  public static void forEach_example_2(){
    String[] playersArray = {"Player 1", "Player 2", "Player 3", "Player 4"};
    List<String> players = Arrays.asList(playersArray);

    players.forEach(System.out::println);
  }

  // Using lambda expression
  public static void thread_example_1(){
    new Thread(()-> System.out.println("Hello Thread!")).start();
  }

  // Using lambda expression
  public static void runnable_example_1(){
    Runnable run1 = ()-> System.out.println("Hello Runnable!");
    run1.run();
  }

  public static void sort_example_1(){
    String[] playersArrays = {"Rafael Nadal", "Novak Djokovic", "Stanislas Wawrinka", "David Ferrer", "Roger Federer", "Andy Murray", "Tomas Berdych", "Juan Martin Del Potro", "Richard Gasquet", "John Isner"};

    System.out.println("--- BEFORE SORTING ---");
    Arrays.asList(playersArrays).forEach(System.out::println);

    // Sort players by name using anonymous innerclass
//    Arrays.sort(playersArrays, new Comparator<String>() {
//      @Override
//      public int compare(String s1, String s2) {
//        return (s1.compareTo(s2));
//      }
//    });

    //Using lambda expression
    Comparator<String> sortByName = (String s1, String s2) -> s1.compareTo(s2);
    Arrays.sort(playersArrays, sortByName);

    System.out.println("--- AFTER SORTING ---");
    Arrays.asList(playersArrays).forEach(System.out::println);
  }

  public static void sort_example_2(){
    String[] playersArrays = {"Rafael Nadal", "Novak Djokovic", "Stanislas Wawrinka", "David Ferrer", "Roger Federer", "Andy Murray", "Tomas Berdych", "Juan Martin Del Potro", "Richard Gasquet", "John Isner"};

    System.out.println("--- BEFORE SORTING ---");
    Arrays.asList(playersArrays).forEach(System.out::println);

    Comparator<String> sortBySurname = (String s1, String s2) -> s1.substring(s1.indexOf(" ")).compareTo(s2.substring
        (s2.indexOf(" ")));
    Arrays.sort(playersArrays, sortBySurname);

    System.out.println("--- AFTER SORTING ---");
    Arrays.asList(playersArrays).forEach(System.out::println);
  }

  public static void main(String[] args) {
    int i=1;
//    System.out.println("Example "+i++);
//    forEach_example_1();
//    System.out.println("Example "+i++);
//    forEach_example_2();

//    System.out.println("Example "+i++);
//    thread_example_1();
//    System.out.println("Example "+i++);
//    runnable_example_1();

//    System.out.println("Example "+i++);
//    sort_example_1();
    System.out.println("Example "+i++);
    sort_example_2();
  }

}
