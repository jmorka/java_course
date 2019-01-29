package jm.javacourse.addressbook.tests;

import org.testng.annotations.Test;

public class UserDeletionTests extends TestBase {

  @Test
  public void testUserDeletion() {
    app.getContactHelper().selectUser();
    app.getContactHelper().deleteSelectedUsers();
    app.getContactHelper().confirmUserDeletion();
  }
}
