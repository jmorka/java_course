package jm.javacourse.addressbook.tests;

import jm.javacourse.addressbook.model.UserData;
import org.testng.annotations.Test;

public class UserModificationTests extends TestBase {

  @Test
  public void testUserModification (){
    app.getContactHelper().initUserModification();
    app.getContactHelper().fillUserForm(new UserData("Test3", "Test4", "511222333", "test6@test.pl", null), false);
    app.getContactHelper().submitUserModification();

  }
}
