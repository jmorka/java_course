package jm.javacourse.addressbook.model;

import java.util.Objects;

public class UserData {
  private int id = Integer.MAX_VALUE;
  private String firstname;
  private String lastname;
  private String phoneNumber;
  private String email;
  private String group;


  public int getId() {
    return id;
  }

  public UserData withId(int id) {
    this.id = id;
    return this;
  }


  public UserData withFirstname(String firstname) {
    this.firstname = firstname;
    return this;
  }

  public UserData withLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public UserData withPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
    return this;
  }

  public UserData withEmail(String email) {
    this.email = email;
    return this;
  }

  public UserData withGroup(String group) {
    this.group = group;
    return this;
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

  @Override
  public String toString() {
    return "UserData{" +
            "id='" + id + '\'' +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserData userData = (UserData) o;
    return id == userData.id &&
            Objects.equals(firstname, userData.firstname) &&
            Objects.equals(lastname, userData.lastname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstname, lastname);
  }
}
