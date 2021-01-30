package streamsapi;

import java.util.ArrayList;
import java.util.List;

/** Demonstrates how to match elements of a stream according to some predicate. */
public class StreamMatching {

  public static void main(String[] args) {
    List<String> list = new ArrayList<>();
    list.add("String One");
    list.add("String Two");
    list.add("String Three");

    // print the contents of the list
    list.forEach(System.out::println);

    // return true if any element in the list contains the letter 'g'
    // (returns false if the stream is empty)
    boolean anyMatch = list.stream().anyMatch(element -> element.contains("g"));
    System.out.println("AnyMatch - 'g' - " + anyMatch);

    // returns true if all elements in the list contain the letter 'g'
    // (returns true if the stream is empty)
    boolean allMatch = list.stream().allMatch(element -> element.contains("g"));
    System.out.println("AllMatch - 'g' - " + allMatch);

    // returns true if no elements in the list contain the letter 'g'
    // (returns true if the stream is empty)
    boolean noneMatch = list.stream().noneMatch(element -> element.contains("g"));
    System.out.println("NoneMatch - 'g' - " + noneMatch);
  }
}
