package streamsapi;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Demonstrates how to use the {@link java.util.stream.Stream#collect(Collector)} to convert a
 * stream to a Collection.
 */
public class StreamCollecting {

  public static void main(String[] args) {
    List<String> list = new ArrayList<>();
    list.add("String One");
    list.add("String Two");
    list.add("String Three");

    // print the contents of the list
    list.forEach(System.out::println);

    // convert each element in the list to upper case and convert the stream back to a list
    List<String> upperCaseList =
        list.stream().map(String::toUpperCase).collect(Collectors.toList());

    upperCaseList.forEach(System.out::println);
  }
}
