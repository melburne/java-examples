package multithreading.basics;

/**
 * Creates a new {@link Thread} using a lambda {@link Runnable}.
 */
public class LambdaRunnable {
  public static void main(String[] args) {
    Runnable runnable = () -> {
      System.out.println("New thread created using Lambda Runnable.");
    };
    
    Thread thread = new Thread(runnable);
    thread.start();
  }
}
