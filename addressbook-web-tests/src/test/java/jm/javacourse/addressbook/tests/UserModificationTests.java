package jm.javacourse.addressbook.tests;

import jm.javacourse.addressbook.model.GroupData;
import jm.javacourse.addressbook.model.UserData;
import org.testng.annotations.Test;

public class UserModificationTests extends TestBase {

  @Test
  public void testUserModification() {
    if (!app.getContactHelper().isThereAUser()) {
      app.getNavigationHelper().goToGroupPage();
      if (!app.getGroupHelper().isThereAGroup()) {
        app.getGroupHelper().createGroup(new GroupData("test1", null, null));
      }

      app.getNavigationHelper().gotoNewUserPage();
      app.getContactHelper().createUser(new UserData("Test1", "Test2", "111222333", "test1@test.pl", "test1"), true);
      app.getNavigationHelper().gotoHomePage();
    }
    app.getContactHelper().initUserModification();
    app.getContactHelper().fillUserForm(new UserData("Test3", "Test4", "511222333", "test6@test.pl", null), false);
    app.getContactHelper().submitUserModification();

  }
}
