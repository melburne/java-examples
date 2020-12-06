import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Demonstrates the basics of the Java Streams API.
 */
public class StreamBasics {

  public static void main(String[] args) {
    List<String> stringList = new ArrayList<>();
    stringList.add("String One");
    stringList.add("String Two");
    stringList.add("String Three");
  
    Stream<String> stream = stringList.stream();
    
    stream.forEach(System.out::println);
    
    convertToUpperCase(stringList);
  }
  
  private static void convertToUpperCase(List<String> stringList) {
    System.out.println("Converting to upper case...");
    Stream<String> stream = stringList.stream();
    
    Stream<String> upperCaseStream = stream.map(String::toUpperCase);
    upperCaseStream.forEach(System.out::println);
  }
}
