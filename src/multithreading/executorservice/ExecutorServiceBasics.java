package multithreading.executorservice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Demonstrates the basics of using an Executor Service.
 */
public class ExecutorServiceBasics {
  public static void main(String[] args) {

    // For CPU intensive operations - thread pool should ideally be the same as the no of cores in
    // the machine.
    // For IO intensive operations - set a static higher number for the thread pool.
    ExecutorService executorService =
        Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    for (int i = 0; i < 50; i++) {
      executorService.execute(
          () -> {
            System.out.println("Thread Name: " + Thread.currentThread().getName());
            try {
              Thread.sleep(2000);
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          });
    }
    
    executorService.shutdown();
  }
}
