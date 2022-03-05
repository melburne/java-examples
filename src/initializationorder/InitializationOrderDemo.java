package initializationorder;

public class InitializationOrderDemo {
  // non static initializer
  int nonStaticField = 1;
  {
    System.out.println("Non static initializer");
    nonStaticField = 2;
    // static fields can be accessed in non static initializer blocks
    staticField = 2;
  }

  // static initializer
  static int staticField = 1;
  static {
    System.out.println("Static initializer");
    staticField = 3;
    // compilation error. non static fields cannot be accessed in static initializer blocks
    // nonStaticField = 3;
  }

  public InitializationOrderDemo() {
    System.out.println("Constructor");
  }

  public static void main(String[] args) {
    /*
    static initializers are always executed first, then non static initializers and finally the constructor.
    If a new instance of the class is not created, code in the non static initializer and the constructor won't
    be executed.
     */
    InitializationOrderDemo demo = new InitializationOrderDemo();

    // static block changes staticField to 3 first and then the non static block changes it to 2
    System.out.println(InitializationOrderDemo.staticField);

    System.out.println(demo.nonStaticField);
  }
}
