package com.example.lambdas;

/**
 * Local variables used in a lambda must be effectively final or explicitly marked as final. Static
 * or member variables need not be final.
 */
public class LambdaVariables {

  // this can be changed even after declaring the lambda
  static String staticVariable = "staticVariable";
  
  // this can also be changed since it belongs to the class.
  private String memberVariable = "memberVariable";
  
  public void lambdaVariableDemo() {
    // to use this in a lambda mark it as final or make sure this doesn't change after declaring the
    // lambda (should be effectively final)
    String localVariable = "localVariable";
  
    MyFunctionalInterface myFunctionalInterface =
        text -> System.out.println(text + ": " + localVariable + " | " + staticVariable + " | " + memberVariable);
    myFunctionalInterface.printText("firstCall");
  
    // changing a non static/non member variable after using it in the lambda is not allowed.
    // localVariable = "notAllowed";
  
    // changing static variables is allowed for lambdas
    staticVariable = "changingStaticVariableIsAllowed";
    myFunctionalInterface.printText("secondCall");
    
    // changing member variables is also allowed
    memberVariable = "changingMemberVariableIsAlsoAllowed";
    myFunctionalInterface.printText("thirdCall");
  }

  public static void main(String[] args) {
    LambdaVariables lambdaVariables = new LambdaVariables();
    lambdaVariables.lambdaVariableDemo();
  }
}
