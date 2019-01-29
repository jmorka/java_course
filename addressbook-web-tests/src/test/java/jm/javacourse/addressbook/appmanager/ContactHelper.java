package jm.javacourse.addressbook.appmanager;

import jm.javacourse.addressbook.model.UserData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactHelper extends HelperBase {
  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitUserCreation() {
    click(By.xpath("(//input[@name='submit'])[2]"));
  }

  public void fillUserForm(UserData userData) {
    type(By.name("firstname"), userData.getFirstname());
    type(By.name("lastname"), userData.getLastname());
    type(By.name("home"), userData.getPhoneNumber());
    type(By.name("email"), userData.getEmail());
  }

  public void initUserModification() {
    click(By.xpath("//img[@alt='Edit']"));
  }

  public void submitUserModification() {
    click(By.name("update"));
  }

  public void selectUser() {
    click(By.name("selected[]"));
  }

  public void deleteSelectedUsers() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void confirmUserDeletion() {
    wd.switchTo().alert().accept();
  }
}
