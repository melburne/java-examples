package streamsapi;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Demonstrates how to iterate over a list using a stream.
 */
public class StreamIteration {

  public static void main(String[] args) {
    List<String> stringList = new ArrayList<>();
    stringList.add("String One");
    stringList.add("String Two");
    stringList.add("String Three");
  
    Stream<String> stream = stringList.stream();
    
    // iterates over the stream and prints out each element as it is
    stream.forEach(System.out::println);
    
    convertToUpperCase(stringList);
  }
  
  private static void convertToUpperCase(List<String> stringList) {
    System.out.println("Converting to upper case...");
    Stream<String> stream = stringList.stream();
    
    // convert each element in the stream to upper case and print it out
    Stream<String> upperCaseStream = stream.map(String::toUpperCase);
    upperCaseStream.forEach(System.out::println);
  }
}
