import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Demonstrates three types of Thread Pools: Cached, Scheduled and Single.
 */
public class ExecutorServicePoolTypes {
  public static void main(String[] args) {

    // Creates a new thread for a submitted task if all existing threads are busy.
    // Also has the ability to kill a thread if it is idle for more than 60s.
    ExecutorService cachedExecutorService = Executors.newCachedThreadPool();
    for (int i = 0; i < 10; i++) {
      cachedExecutorService.execute(new Task());
    }
    cachedExecutorService.shutdown();

    ScheduledExecutorService scheduledExecutorService =
        Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());

    // runs after an initial 5 second delay.
    scheduledExecutorService.schedule(new Task(), 5, TimeUnit.SECONDS);

    // runs every 10 seconds after an initial 5 second delay.
    scheduledExecutorService.scheduleAtFixedRate(new Task(), 5, 10, TimeUnit.SECONDS);

    // waits for 5 seconds initially, and then launches a new task only if the previous task is
    // complete with a 10 second delay in between.
    scheduledExecutorService.scheduleWithFixedDelay(new Task(), 5, 10, TimeUnit.SECONDS);

    scheduledExecutorService.shutdown();

    // creates one single thread for each task, so tasks are run sequentially.
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    executorService.execute(new Task());
    executorService.shutdown();
  }

  private static class Task implements Runnable {
    @Override
    public void run() {
      System.out.println("Thread name: " + Thread.currentThread().getName());
      try {
        Thread.sleep(2000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
