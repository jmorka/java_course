package jm.javacourse.addressbook.tests;

import jm.javacourse.addressbook.model.UserData;
import org.testng.annotations.Test;

public class UserCreationTests extends TestBase {

  @Test
  public void testUserCreation() throws Exception {
    app.getNavigationHelper().gotoNewUserPage();
    app.getContactHelper().fillUserForm(new UserData("Test1", "Test2", "111222333", "test1@test.pl", "test1"), true);
    app.getContactHelper().submitUserCreation();
  }

}
