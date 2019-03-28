package jm.javacourse.addressbook.tests;

import jm.javacourse.addressbook.model.GroupData;
import jm.javacourse.addressbook.model.UserData;
import jm.javacourse.addressbook.model.Users;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class UserDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().users().size() == 0) {
      app.goTo().groupPage();
      if (app.db().groups().size() == 0) {
        app.group().create(new GroupData().withName("test1"));
      }
      app.goTo().newUserPage();
      app.contact().create(new UserData()
              .withFirstname("Test1").withLastname("Test2").withHomePhone("111").withMobilePhone("222")
              .withWorkPhone("333").withEmail("test1@test.pl").withGroup("test1"), true);
    }
    app.contact().returnToHomePage();
  }

  @Test
  public void testUserDeletion() {
    Users before = app.db().users();
    UserData deletedUser = before.iterator().next();
    app.contact().delete(deletedUser);
    assertEquals(app.contact().count(), before.size() - 1);
    Users after = app.db().users();
    assertThat(after, equalTo(before.without(deletedUser)));

  }

}
