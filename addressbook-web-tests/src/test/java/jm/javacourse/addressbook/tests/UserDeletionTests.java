package jm.javacourse.addressbook.tests;

import jm.javacourse.addressbook.model.GroupData;
import jm.javacourse.addressbook.model.UserData;
import org.testng.annotations.Test;

public class UserDeletionTests extends TestBase {

  @Test
  public void testUserDeletion() {
    if (!app.getContactHelper().isThereAUser()) {
      app.getNavigationHelper().goToGroupPage();
      if (!app.getGroupHelper().isThereAGroup()) {
        app.getGroupHelper().createGroup(new GroupData("test1", null, null));
      }
      app.getNavigationHelper().gotoNewUserPage();
      app.getContactHelper().createUser(new UserData("Test1", "Test2", "111222333", "test1@test.pl", "test1"), true);
      app.getNavigationHelper().gotoHomePage();
    }
    app.getContactHelper().selectUser();
    app.getContactHelper().deleteSelectedUsers();
    app.getContactHelper().confirmUserDeletion();
  }
}
