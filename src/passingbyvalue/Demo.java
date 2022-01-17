package passingbyvalue;

// https://www.linkedin.com/learning/java-memory-management/how-objects-are-passed
public class Demo {
  public static void main(String[] args) {
    Customer c = new Customer("Sally");
    renameCustomer(c);

    // prints Diane because renameCustomer() changed the object's pointer to the String on the heap
    System.out.println(c.getName());
  }

  private static void renameCustomer(Customer cust) {
    // the Customer object is NOT copied on the heap but a new copy of the variable that points to the
    // Customer object is created on the stack. Hence, the following line modifies the original object on the heap.
    cust.setName("Diane");
  }
}
