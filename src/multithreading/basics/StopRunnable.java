package multithreading.basics;

/**
 * Creates a {@link Runnable} that keeps running till the main method requests it to stop.
 */
public class StopRunnable {
  public static class StoppableRunnable implements Runnable {
    
    private boolean stopRequested = false;
    
    public synchronized void requestStop() {
      this.stopRequested = true;
    }
    
    public synchronized boolean isStopRequested() {
      return this.stopRequested;
    }
    
    private void sleep(long millis) {
      try{
        Thread.sleep(millis);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    
    @Override
    public void run() {
      System.out.println(Thread.currentThread().getName() + " started.");
      // this thread will keep running till the main thread flips the stopRequested flag.
      while (!isStopRequested()) {
        sleep(1000);
        System.out.println(Thread.currentThread().getName() + " running...");
      }
      System.out.println(Thread.currentThread().getName() + " stopped.");
    }
  }

  public static void main(String[] args) {
    
    StoppableRunnable stoppableRunnable = new StoppableRunnable();
    Thread thread = new Thread(stoppableRunnable, "StoppableRunnable");
    thread.start();
    
    try {
      System.out.println("Putting " + Thread.currentThread().getName() + " to sleep for 5s.");
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    // Stop request is sent after the main method sleeps for 5 seconds.
    // So till then the StoppableRunnable keeps running.
    System.out.println("Sending stop request to StoppableRunnable.");
    stoppableRunnable.requestStop();
    System.out.println("Request sent!");
  }
}
