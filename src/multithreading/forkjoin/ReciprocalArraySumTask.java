package multithreading.forkjoin;

import java.util.concurrent.RecursiveAction;

/** Represents the task created to perform each reciprocal sum computation in parallel. */
public class ReciprocalArraySumTask extends RecursiveAction {
  private final double[] array;
  private final int startIndex;
  private final int endIndex;
  private double value;

  public ReciprocalArraySumTask(double[] array, int startIndex, int endIndex) {
    this.array = array;
    this.startIndex = startIndex;
    this.endIndex = endIndex;
  }

  /**
   * Returns the reciprocal sum of the array
   *
   * @return Reciprocal sum
   */
  public double getValue() {
    return value;
  }

  /** Computes the reciprocal sum of an array. */
  @Override
  protected void compute() {
    value = 0;
    for (int i = startIndex; i < endIndex; i++) {
      value += 1 / array[i];
    }
  }
}
