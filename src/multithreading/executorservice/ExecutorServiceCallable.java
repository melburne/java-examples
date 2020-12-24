package multithreading.executorservice;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/** Demonstrates the use of a {@link Callable} in Executor Service. */
public class ExecutorServiceCallable {

  public static void main(String[] args) {
    ExecutorService service = Executors.newFixedThreadPool(10);

    // Future is a placeholder for the value returned by the Callable which will be populated
    // whenever the Callable finishes and returns a value
    Future<Integer> future = service.submit(new Task());

    try {
      // future.get() is a blocking operation so it will halt the main thread till a value is
      // returned by the Callable
      System.out.println("Trying to get value returned by Callable");
      System.out.println(future.get());
      System.out.println("Got value from Callable");

      // use the overloaded get method to specify the max time you're willing to wait
      // System.out.println(future.get(5, TimeUnit.SECONDS));
    } catch (InterruptedException | ExecutionException e) {
      e.printStackTrace();
    }

    service.shutdown();
  }

  // use a Callable when you need to return a value
  public static class Task implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
      Thread.sleep(3000);
      return new Random().nextInt(100);
    }
  }
}
