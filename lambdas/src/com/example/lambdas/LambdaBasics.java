package com.example.lambdas;

import java.util.Comparator;

/**
 * Contrasts the old of using anonymous classes against Lambdas.
 */
public class LambdaBasics {
  public static void main(String[] args) {
    // Old way
    Comparator<String> comparator =
        new Comparator<String>() {
          @Override
          public int compare(String s, String s1) {
            return s.compareTo(s1);
          }
        };
    System.out.println("Old way: " + comparator.compare("Hello", "World"));

    // Using Lambdas
    Comparator<String> lambdaComparator =
        (String s1, String s2) -> {
          return s1.compareTo(s2);
        };
    System.out.println("Using Lambdas: " + lambdaComparator.compare("Hello", "World"));

    // Simplifying further
    Comparator<String> simpleLambdaComparator = (s, s2) -> s.compareTo(s2);
    System.out.println("Simplified Lambda: " + simpleLambdaComparator.compare("Hello", "World"));

    /*
    // Simplifying even more (using method reference)
    Comparator<String> simplestLambdaComparator = String::compareTo;
    System.out.println("Simplest Lambda: " + simpleLambdaComparator.compare("Hello", "World"));
    */
  }
}
