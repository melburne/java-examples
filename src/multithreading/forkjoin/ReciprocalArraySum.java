package multithreading.forkjoin;

import java.util.Random;

/**
 * Compares the time it takes to calculate the reciprocal sum of an array sequentially vs the
 * Fork-Join Framework.
 */
public class ReciprocalArraySum {
  static final double[] array = createRandomArray(200_000_000);

  public static void main(String[] args) {

    double seqTime = timeSequentialRuns();
    System.out.println();
    double forkJoinTime = timeForkJoinRuns();

    System.out.format("\nSpeedup: %.2fx", seqTime / forkJoinTime);
  }

  /**
   * Create a random array of double values.
   *
   * @param N Number of elements in the array
   * @return An array of double values
   */
  private static double[] createRandomArray(final int N) {
    final double[] array = new double[N];
    final Random random = new Random(10);

    for (int i = 0; i < N; i++) {
      array[i] = random.nextInt(100);
      // remove zero values to avoid divide-by-zero error
      if (array[i] == 0) {
        i--;
      }
    }

    return array;
  }

  /**
   * Calculates the time taken for three runs using the Fork-Join Framework.
   *
   * @return Average time of three runs
   */
  private static double timeForkJoinRuns() {
    long startTime;
    long endTime;
    long totalTime;
    long forkJoinTime = 0;
    long average;
    double sum;

    // time the fork-join framework for 3 runs
    for (int i = 1; i <= 3; i++) {
      startTime = System.currentTimeMillis();
      sum = calculateReciprocalSumUsingForkJoin();
      endTime = System.currentTimeMillis();
      totalTime = endTime - startTime;

      System.out.printf(
          "Iteration: %d | Fork Join Reciprocal Sum: %.2f | Time: %dms\n", i, sum, totalTime);
      forkJoinTime += totalTime;
    }
    average = forkJoinTime / 3;
    System.out.printf("Average Time: %dms\n", average);
    
    return average;
  }

  /**
   * Calculates the time taken for three sequential runs.
   *
   * @return Average time of three runs
   */
  private static double timeSequentialRuns() {
    long startTime;
    long endTime;
    long totalTime;
    long seqTime = 0;
    long average;
    double sum;

    // time sequential computation for 3 runs
    for (int i = 1; i <= 3; i++) {
      startTime = System.currentTimeMillis();
      sum = calculateReciprocalSumSequentially();
      endTime = System.currentTimeMillis();
      totalTime = endTime - startTime;

      System.out.printf(
          "Iteration: %d | Sequential Reciprocal Sum: %.2f | Time: %dms\n", i, sum, totalTime);
      seqTime += totalTime;
    }
    average = seqTime / 3;
    System.out.printf("Average Time: %dms\n", average);
  
    return average;
  }

  /**
   * Calculates the reciprocal sum of an array using the Fork-Join Framework.
   *
   * @return Reciprocal sum of the the elements in the array
   */
  private static double calculateReciprocalSumUsingForkJoin() {
    ReciprocalArraySumTask firstHalf = new ReciprocalArraySumTask(array, 0, array.length / 2);
    ReciprocalArraySumTask secondHalf =
        new ReciprocalArraySumTask(array, array.length / 2, array.length);

    firstHalf.fork();
    secondHalf.compute();
    firstHalf.join();

    return firstHalf.getValue() + secondHalf.getValue();
  }

  /**
   * Calculates the reciprocal sum of an array sequentially.
   *
   * @return Reciprocal sum of the the elements in the array
   */
  private static double calculateReciprocalSumSequentially() {
    double sum = 0;
    for (int i = 0; i < array.length; i++) {
      sum += 1 / array[i];
    }

    return sum;
  }
}
