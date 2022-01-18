package passingbyvalue;

// https://www.linkedin.com/learning/java-memory-management/how-objects-are-passed
public class PassingObjects {
  public static void main(String[] args) {
    // example holds true even if c is declared final
    Customer c = new Customer("Sally", 10);

    renameCustomer(c);
    // prints Diane because renameCustomer() changed the object's pointer to the String on the heap
    System.out.println(c.getName());

    changeId(c);
    // same logic for primitives
    System.out.println(c.getId());
  }

  private static void renameCustomer(Customer cust) {
    // the Customer object is NOT copied on the heap but a new copy of the variable that points to the
    // Customer object is created on the stack. Hence, the following line modifies the original object on the heap.
    cust.setName("Diane");
  }

  private static void changeId(Customer cust) {
    cust.setId(23);
  }
}
