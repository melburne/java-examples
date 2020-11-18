package com.example.multithreading.basics;

/**
 * Demonstrates thread joining.
 */
public class WaitingRunnable {
  public static void main(String[] args) {
    Runnable runnable =
        () -> {
          for (int i = 1; i <= 5; i++) {
            System.out.println(Thread.currentThread().getName() + " sleeping.");
            sleep(1000);
          }
        };

    Thread thread = new Thread(runnable, "Test Thread");
    // setting this to true will kill the 'Test Thread' if the main thread has finished running.
    thread.setDaemon(true);
    thread.start();
    
    // Commenting this our will kill the 'Test Thread' as soon as the main thread finishes,
    // so if you want to keep the 'Test Thread' running even after the main thread finishes
    // make sure the daemon is set to false or join the two threads.
    try{
      thread.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
  
  private static void sleep(long millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
