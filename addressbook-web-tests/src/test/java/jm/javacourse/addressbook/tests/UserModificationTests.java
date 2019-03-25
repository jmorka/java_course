package jm.javacourse.addressbook.tests;

import jm.javacourse.addressbook.model.GroupData;
import jm.javacourse.addressbook.model.UserData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class UserModificationTests extends TestBase {

  @Test
  public void testUserModification() {
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
    app.getContactHelper().initUserModification();
    UserData user = new UserData(before.get(0).getId(), "Test3", "Test4", "511222333", "test6@test.pl", null);
    app.getContactHelper().fillUserForm(user, false);
    app.getContactHelper().submitUserModification();
    app.goTo().gotoHomePage();
    List<UserData> after = app.getContactHelper().getUserList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(0);
    before.add(user);
    Comparator<? super UserData> byId = (u1, u2) -> Integer.compare(u1.getId(), u2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);

  }
}
