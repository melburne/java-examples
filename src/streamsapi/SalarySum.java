package streamsapi;

import java.util.ArrayList;
import java.util.List;

/** Uses the Java Streams API to calculate the salaries of a list of employees. */
public class SalarySum {

  private static class Employee {
    String name;
    Integer salary;

    Employee(String name, Integer salary) {
      this.name = name;
      this.salary = salary;
    }
  }

  public static void main(String[] args) {
    List<Employee> employeeList = new ArrayList<>();
    employeeList.add(new Employee("John", 10000));
    employeeList.add(new Employee("Mary", 20000));
    employeeList.add(new Employee("Sam", 25000));

    Integer totalSum =
        employeeList.stream()
            .map(employee -> employee.salary)
            .reduce(Integer::sum)
            .get();

    System.out.println("Sum of salaries: " + totalSum);
  }
}
