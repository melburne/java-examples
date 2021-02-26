package customannotations;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class TestCustomAnnotations {

  public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
    Person person = new Person("John", "Doe", "23");
    convertToJson(person);
  }

  private static void convertToJson(Object object) {
    try {
      checkIfSerializable(object);
      initializeObject(object);
      getJsonString(object);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  private static void checkIfSerializable(Object object) {
    if (Objects.isNull(object)) {
      throw new RuntimeException("Cannot serialize null object");
    }

    Class<?> clazz = object.getClass();
    if (!clazz.isAnnotationPresent(JsonSerializable.class)) {
      throw new RuntimeException("Object is not annotated with JsonSerializable");
    }
  }

  private static void initializeObject(Object object)
      throws InvocationTargetException, IllegalAccessException {
    Class<?> clazz = object.getClass();
    for (Method method : clazz.getDeclaredMethods()) {
      if (method.isAnnotationPresent(Init.class)) {
        method.setAccessible(true);
        method.invoke(object);
      }
    }
  }

  private static void getJsonString(Object object) throws IllegalAccessException {
    Map<String, String> jsonMap = new HashMap<>();

    Class<?> clazz = object.getClass();
    for (Field field : clazz.getDeclaredFields()) {
      field.setAccessible(true);
      if (field.isAnnotationPresent(JsonElement.class)) {
        jsonMap.put(getUserDefinedKeyMaybe(field), (String) field.get(object));
      }
    }

    String json =
        jsonMap.entrySet().stream()
            .map(e -> "\"" + e.getKey() + "\"" + ": \"" + e.getValue() + "\"")
            .collect(Collectors.joining(", "));

    System.out.println("{" + json + "}");
  }
  
  private static String getUserDefinedKeyMaybe(Field field) {
    String key = field.getAnnotation(JsonElement.class).key();
    return key.isEmpty() ? field.getName() : key;
  }
}
