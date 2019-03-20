package jm.javacourse.addressbook.appmanager;

import jm.javacourse.addressbook.model.GroupData;
import jm.javacourse.addressbook.model.UserData;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.List;

public class ContactHelper extends HelperBase {
  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitUserCreation() {
    click(By.xpath("(//input[@name='submit'])[2]"));
  }

  public void fillUserForm(UserData userData, boolean creation) {
    type(By.name("firstname"), userData.getFirstname());
    type(By.name("lastname"), userData.getLastname());
    type(By.name("home"), userData.getPhoneNumber());
    type(By.name("email"), userData.getEmail());

    if (creation){
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(userData.getGroup());
    }else{
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
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

  public void createUser(UserData user, boolean creation ) {
    fillUserForm(user, creation);
    submitUserCreation();

  }

  public boolean isThereAUser() {
    return isElementPresent (By.name("selected[]"));
  }


}
