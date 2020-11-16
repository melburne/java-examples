package com.example.lambdas;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 * A Functional Interface is an interface that has exactly one abstract method.
 * The interface can have other default and static methods.
 * (The annotation {@link FunctionalInterface} is not required.)
 */
@FunctionalInterface
public interface MyFunctionalInterface {
  void printText(String text);
  
  default public void printUtf8To(String text, OutputStream outputStream) {
    try {
      outputStream.write(text.getBytes(StandardCharsets.UTF_8));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  static void printToSystemOut(String text) {
    System.out.println(text);
  }
}
