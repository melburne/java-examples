package multithreading.basics;

/**
 * Creates a new {@link Thread} using an anonymous {@link Runnable}.
 */
public class AnonymousRunnable {
  public static void main(String[] args) {
    Runnable runnable = new Runnable() {
      @Override
      public void run() {
        System.out.println("New thread created using Anonymous Runnable.");
      }
    };
    
    Thread thread = new Thread(runnable);
    thread.start();
  }
}
