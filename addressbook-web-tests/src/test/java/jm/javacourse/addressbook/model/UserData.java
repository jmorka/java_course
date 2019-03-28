package jm.javacourse.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.Objects;

@Entity
@Table(name="addressbook")
public class UserData {

  @XStreamOmitField
  @Id
  @Column(name = "id")
  private int id = Integer.MAX_VALUE;

  @Expose
  @Column(name="firstname")
  private String firstname;

  @Expose
  @Column(name="lastname")
  private String lastname;

  @Expose
  @Column(name="home")
  @Type(type = "text")
  private String homePhone;

  @Expose
  @Column(name="email")
  @Type(type = "text")
  private String email;

  @Expose
  @Transient
  private String group;

  @XStreamOmitField
  @Column(name="mobile")
  @Type(type = "text")
  private String mobilePhone;

  @XStreamOmitField
  @Column(name="work")
  @Type(type = "text")
  private String workPhone;

  @XStreamOmitField
  @Transient
  private String allPhones;

  @XStreamOmitField
  @Column(name="address")
  @Type(type = "text")
  private String address;

  @Expose
  @Column(name="email2")
  @Type(type = "text")
  private String email2;

  @Expose
  @Column(name="email3")
  @Type(type = "text")
  private String email3;

  @XStreamOmitField
  @Transient
  private String allEmails;

  @XStreamOmitField
  @Transient
  private String details;

  @XStreamOmitField
  @Column(name="photo")
  @Type(type = "text")
  private String photo;

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

  public UserData withHomePhone(String phoneNumber) {
    this.homePhone = phoneNumber;
    return this;
  }

  public UserData withMobilePhone(String phoneNumber) {
    this.mobilePhone = phoneNumber;
    return this;
  }

  public UserData withWorkPhone(String phoneNumber) {
    this.workPhone = phoneNumber;
    return this;
  }

  public UserData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
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

  public String getHomePhone() {
    return homePhone;
  }

  public String getEmail() {
    return email;
  }

  public String getGroup() {
    return group;
  }

  public String getMobilePhone() {
    return mobilePhone;
  }

  public String getWorkPhone() {
    return workPhone;
  }

  public String getAllPhones() {
    return allPhones;
  }

  public String getAddress() {
    return address;
  }

  public UserData withAddress(String address) {
    this.address = address;
    return this;
  }

  public String getEmail2() {
    return email2;
  }

  public UserData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }

  public String getEmail3() {
    return email3;
  }

  public UserData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }

  public String getAllEmails() {
    return allEmails;
  }

  public UserData withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;
  }

  public String getDetails() {
    return details;
  }

  public UserData withDetails(String details) {
    this.details = details;
    return this;
  }

  public File getPhoto() {
    if (photo != null) {
      return new File(photo);
    }
    return null;
  }

  public UserData withPhoto(File photo) {
    this.photo = photo.getPath();
    return this;
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
            Objects.equals(lastname, userData.lastname) &&
            Objects.equals(homePhone, userData.homePhone) &&
            Objects.equals(email, userData.email);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstname, lastname, homePhone, email);
  }
}
