package com.example.multithreading.basics;

/**
 * Demonstrates named {@link Thread}s.
 */
public class NamedThread {
  public static void main(String[] args) {
    Runnable runnable = () -> {
      System.out.println("Running thread: " + Thread.currentThread().getName());
    };
    
    // If you don't specify the name in the constructor, the default name is 'Thread-0'.
    Thread thread = new Thread(runnable, "randomThread1");
    thread.start();
  
    // Threads are not guaranteed to execute in the order in which they are called
    // so 'randomThread2' may execute before 'randomThread1'.
    Thread thread2 = new Thread(runnable, "randomThread2");
    thread2.start();
  }
}
