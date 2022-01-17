package passingbyvalue;

public class PassingVariables {
  public static void main(String[] args) {
    int localVal = 5;
    calculate(localVal);

    // prints 5 because the original variable was not modified on the stack
    System.out.println(localVal);
  }

  private static void calculate(int val) {
    // a new copy of the variable is created on the stack and is lost the moment the method exits
    val = val * 100;
  }
}
