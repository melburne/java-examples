package com.example.lambdas;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Demonstrates Lambdas using a {@link FunctionalInterface}.
 */
public class MyFunctionalInterfaceLambda {

  public static void main(String[] args) {
    MyFunctionalInterface myFunctionalInterface = text -> System.out.println(text);
    myFunctionalInterface.printText("Hello World using lambda");
    
    // calling default method
    try {
      myFunctionalInterface.printUtf8To("Hello File", new FileOutputStream("functional-interface-file.txt"));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  
    // calling static method
    MyFunctionalInterface.printToSystemOut("Hello World using static method");
  }
}
