package customannotations;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/** Converts objects to JSON Strings if annotated with {@link JsonSerializable}. */
public class TestCustomAnnotations {

  public static void main(String[] args) {
    Person person = new Person("john", "doe", "23", "testAddress");

    try {
      checkIfSerializable(person);
      initializeObject(person);
      getJsonString(person);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * Checks if the object is annotated with {@link JsonSerializable}.
   *
   * @param object Object to convert to JSON
   */
  private static void checkIfSerializable(Object object) {
    if (Objects.isNull(object)) {
      throw new RuntimeException("Cannot serialize null object");
    }

    Class<?> clazz = object.getClass();
    if (!clazz.isAnnotationPresent(JsonSerializable.class)) {
      throw new RuntimeException("Object is not annotated with JsonSerializable");
    }
  }

  /**
   * Invokes methods annotated with {@link Init}.
   *
   * @param object Object to convert to JSON
   * @throws InvocationTargetException
   * @throws IllegalAccessException
   */
  private static void initializeObject(Object object)
      throws InvocationTargetException, IllegalAccessException {
    Class<?> clazz = object.getClass();
    for (Method method : clazz.getDeclaredMethods()) {
      if (method.isAnnotationPresent(Init.class)) {
        method.setAccessible(true);   // needed if method to be invoked is private
        method.invoke(object);
      }
    }
  }

  /**
   * Converts the object to a JSON String.
   *
   * @param object Object to convert to JSON
   * @throws IllegalAccessException
   */
  private static void getJsonString(Object object) throws IllegalAccessException {
    Map<String, String> jsonMap = new HashMap<>();

    Class<?> clazz = object.getClass();
    for (Field field : clazz.getDeclaredFields()) {
      field.setAccessible(true);    // needed if field is private
      if (field.isAnnotationPresent(JsonElement.class)) {
        jsonMap.put(getUserDefinedKeyMaybe(field), field.get(object).toString());
      }
    }

    String json =
        jsonMap.entrySet().stream()
            .map(e -> "\"" + e.getKey() + "\"" + ": \"" + e.getValue() + "\"")
            .collect(Collectors.joining(", "));

    System.out.println("{" + json + "}");
  }

  /**
   * Checks if a field has a custom key defined via {@link JsonElement}.
   *
   * @param field Field of the object to convert to JSON
   * @return Custom key if present or field name otherwise
   */
  private static String getUserDefinedKeyMaybe(Field field) {
    String key = field.getAnnotation(JsonElement.class).key();
    return key.isEmpty() ? field.getName() : key;
  }
}
