package customannotations;

/**
 * Sample class that can be serialized to a JSON String using {@link JsonSerializable}. {@link
 * JsonElement} can be used on fields to include them in the JSON String and {@link Init} can be
 * used to mark methods that must be run before the serialization.
 */
@JsonSerializable
public class Person {

  @JsonElement private String firstName;

  @JsonElement private String lastName;

  // use the 'personAge' key while serializing to JSON instead of 'age'
  @JsonElement(key = "personAge")
  private String age;

  private String address;

  public Person(String firstName, String lastName, String age, String address) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.age = age;
    this.address = address;
  }
  
  /**
   * Converts the first letter of the 'firstName' and 'lastName' fields to upper case.
   */
  @Init
  private void initNames() {
    this.firstName = this.firstName.substring(0, 1).toUpperCase() + this.firstName.substring(1);
    this.lastName = this.lastName.substring(0, 1).toUpperCase() + this.lastName.substring(1);
  }
}
