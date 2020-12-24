package multithreading.basics;

/**
 * Creates a new custom {@link Runnable} by implementing the interface.
 */
public class RunnableBasics {
  
  public static class MyRunnable implements Runnable {
    @Override
    public void run() {
      System.out.println("New thread created using Runnable interface.");
    }
  }
  
  public static void main(String[] args) {
    Thread thread = new Thread(new MyRunnable());
    thread.start();
  }
}
