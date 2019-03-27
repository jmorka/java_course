package jm.javacourse.addressbook.appmanager;

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
    type(By.name("home"), userData.getHomePhone());
    type(By.name("mobile"), userData.getMobilePhone());
    type(By.name("work"), userData.getWorkPhone());
    type(By.name("address"), userData.getAddress());
    type(By.name("email"), userData.getEmail());
    type(By.name("email2"), userData.getEmail2());
    type(By.name("email3"), userData.getEmail3());

    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(userData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
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
    initContactModificationById(user.getId());
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


  public int count() {
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
      String allPhones = element.findElement(By.cssSelector("td:nth-child(6)")).getText();
      String address = element.findElement(By.cssSelector("td:nth-child(4)")).getText();
      String allEmails = element.findElement(By.cssSelector("td:nth-child(5)")).getText();
      int id = Integer.parseInt(element.findElement(By.cssSelector("td:nth-child(1) input")).getAttribute("value"));
      userCache.add(new UserData().withId(id).withFirstname(firstName).withLastname(lastName)
              .withAllPhones(allPhones).withAddress(address).withAllEmails(allEmails));
    }

    return new Users (userCache);
  }


  public UserData infoFromEditForm(UserData contact) {
    initContactModificationById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    wd.navigate().back();
    return new UserData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname).withHomePhone(home)
            .withMobilePhone(mobile).withWorkPhone(work)
            .withAddress(address)
            .withEmail(email).withEmail2(email2).withEmail3(email3);
  }

  private void initContactModificationById(int id) {
    WebElement checkbox = wd.findElement(By.cssSelector("input[value='" + id + "'"));
    WebElement row = checkbox.findElement(By.xpath("./../.."));
    List<WebElement> cells = row.findElements(By.tagName("td"));
    cells.get(7).findElement(By.tagName("a")).click();
  }

  public UserData infoFromDetailPage(UserData contact) {
    initContactDetailPage(contact.getId());
    String details = wd.findElement(By.id("content")).getText();
    wd.navigate().back();
    return new UserData().withDetails(details);
  }

  private void initContactDetailPage(int id) {
    WebElement checkbox = wd.findElement(By.cssSelector("input[value='" + id + "'"));
    WebElement row = checkbox.findElement(By.xpath("./../.."));
    List<WebElement> cells = row.findElements(By.tagName("td"));
    cells.get(6).findElement(By.tagName("a")).click();
  }
}
