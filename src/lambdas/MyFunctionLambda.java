package lambdas;

/**
 * Simple demo of a lambda using an interface.
 */
public class MyFunctionLambda {
  public static void main(String[] args) {
    MyFunction myFunction = text -> System.out.println(text);
    
    myFunction.apply("Hello World");
  }
}
