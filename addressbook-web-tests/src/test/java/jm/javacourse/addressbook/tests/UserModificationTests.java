package jm.javacourse.addressbook.tests;

import jm.javacourse.addressbook.model.GroupData;
import jm.javacourse.addressbook.model.UserData;
import jm.javacourse.addressbook.model.Users;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class UserModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.contact().all().size() == 0) {
      app.goTo().groupPage();
      if (app.group().all().size() == 0) {
        app.group().create(new GroupData().withName("test1"));
      }

      app.goTo().newUserPage();
      app.contact().create(new UserData()
              .withFirstname("Test1").withLastname("Test2").withPhoneNumber("111222333").withEmail("test1@test.pl").withGroup("test1"), true);
    }
  }

  @Test
  public void testUserModification() {
    Users before = app.contact().all();
    UserData modifiedUser = before.iterator().next();
    UserData user = new UserData().withId(modifiedUser.getId())
            .withFirstname("Test3").withLastname("Test4").withPhoneNumber("511222333").withEmail("test6@test.pl");
    app.contact().modify(user);
    Users after = app.contact().all();
    assertEquals(after.size(), before.size());
    assertThat(after, equalTo(before.without(modifiedUser).withAdded(user)));

  }


}
