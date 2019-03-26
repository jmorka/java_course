package jm.javacourse.addressbook.appmanager;

import jm.javacourse.addressbook.model.Groups;
import jm.javacourse.addressbook.model.UserData;
import jm.javacourse.addressbook.model.Users;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.List;

public class ContactHelper extends HelperBase {
  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void returnToHomePage() {
    click(By.linkText("home"));
  }

  public void submitUserCreation() {
    click(By.xpath("(//input[@name='submit'])[2]"));
  }

  public void fillUserForm(UserData userData, boolean creation) {
    type(By.name("firstname"), userData.getFirstname());
    type(By.name("lastname"), userData.getLastname());
    type(By.name("home"), userData.getPhoneNumber());
    type(By.name("email"), userData.getEmail());

    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(userData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void initUserModification() {
    click(By.xpath("//img[@alt='Edit']"));
  }


  private void initUserModificationById(int id) {
    List<WebElement> rows = wd.findElements(By.cssSelector("tr"));
    rows.stream()
            .filter(r -> r.findElements(By.cssSelector("input[value='" + id + "']")).size() != 0)
            .findFirst()
            .ifPresent(r -> r.findElement(By.cssSelector("img[alt='Edit']")).click());
  }

  public void submitUserModification() {
    click(By.name("update"));
  }

  public void selectUserById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public void deleteSelectedUsers() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void confirmUserDeletion() {
    wd.switchTo().alert().accept();
  }

  public void create(UserData user, boolean creation) {
    fillUserForm(user, creation);
    submitUserCreation();
    userCache = null;
    returnToHomePage();
  }

  public void modify(UserData user) {
    initUserModificationById(user.getId());
    fillUserForm(user, false);
    submitUserModification();
    userCache = null;
    returnToHomePage();
  }


  public void delete(UserData user) {
    selectUserById(user.getId());
    deleteSelectedUsers();
    confirmUserDeletion();
    userCache = null;
    returnToHomePage();
  }

  public boolean isThereAUser() {
    return isElementPresent(By.name("selected[]"));
  }


  public int getUserCount() {
   return wd.findElements (By.name("selected[]")).size();
  }

  private Users userCache = null;

  public Users all() {
    if (userCache != null){
      return new Users (userCache);
    }
    userCache = new Users();
    List<WebElement> elements = wd.findElements(By.cssSelector("tr[name=\"entry\""));
    for (WebElement element : elements){
      String firstName = element.findElement(By.cssSelector("td:nth-child(3)")).getText();
      String lastName = element.findElement(By.cssSelector("td:nth-child(2)")).getText();
      int id = Integer.parseInt(element.findElement(By.cssSelector("td:nth-child(1) input")).getAttribute("value"));
      userCache.add(new UserData().withId(id).withFirstname(firstName).withLastname(lastName));
    }

    return new Users (userCache);
  }


}
