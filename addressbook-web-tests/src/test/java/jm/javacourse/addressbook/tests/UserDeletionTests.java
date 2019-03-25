package jm.javacourse.addressbook.tests;

import jm.javacourse.addressbook.model.GroupData;
import jm.javacourse.addressbook.model.UserData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class UserDeletionTests extends TestBase {

  @Test
  public void testUserDeletion() {
    if (!app.getContactHelper().isThereAUser()) {
      app.goTo().groupPage();
      if (!app.group().isThereAGroup()) {
        app.group().create(new GroupData().withName("test1"));
      }
      app.goTo().gotoNewUserPage();
      app.getContactHelper().createUser(new UserData("Test1", "Test2", "111222333", "test1@test.pl", "test1"), true);
      app.goTo().gotoHomePage();
    }
    List<UserData> before = app.getContactHelper().getUserList();
    app.getContactHelper().selectUser(before.size() - 1);
    app.getContactHelper().deleteSelectedUsers();
    app.getContactHelper().confirmUserDeletion();
    app.goTo().gotoHomePage();
    List<UserData> after = app.getContactHelper().getUserList();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);
    Assert.assertEquals(before, after);
  }
}
