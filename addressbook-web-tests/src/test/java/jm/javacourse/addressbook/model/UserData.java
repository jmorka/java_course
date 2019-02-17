package jm.javacourse.addressbook.model;

public class UserData {
  private final String firstname;
  private final String lastname;
  private final String phoneNumber;
  private final String email;
  private String group;

  public UserData(String firstname, String lastname, String phoneNumber, String email, String group) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.phoneNumber = phoneNumber;
    this.email = email;
    this.group = group;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public String getEmail() {
    return email;
  }

  public String getGroup() {
    return group;
  }
}
