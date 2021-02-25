package customannotations;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

public class TestCustomAnnotations {
  
  public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
    Person person = new Person("John", "Doe", "23");
    checkIfSerializable(person);
    initializeObject(person);
  }
  
  private static void checkIfSerializable(Object object) {
    if ( Objects.isNull(object)) {
      throw new RuntimeException("Cannot serialize null object");
    }
    
    Class<?> clazz = object.getClass();
    if (!clazz.isAnnotationPresent(JsonSerializable.class)) {
      throw new RuntimeException("Object is not annotated with JsonSerializable");
    }
  }
  
  private static void initializeObject(Object object) throws InvocationTargetException, IllegalAccessException {
    Class<?> clazz = object.getClass();
    for (Method method : clazz.getDeclaredMethods()) {
      if (method.isAnnotationPresent(Init.class)) {
        method.setAccessible(true);
        method.invoke(object);
      }
    }
  }
}
