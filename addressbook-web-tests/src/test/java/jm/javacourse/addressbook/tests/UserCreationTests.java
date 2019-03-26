package jm.javacourse.addressbook.tests;

import jm.javacourse.addressbook.model.GroupData;
import jm.javacourse.addressbook.model.UserData;
import jm.javacourse.addressbook.model.Users;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserCreationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.group().all().size() == 0) {
      app.group().create(new GroupData().withName("test1"));
    }
    app.contact().returnToHomePage();
  }

  @Test
  public void testUserCreation() throws Exception {
    Users before = app.contact().all();
    UserData user = new UserData()
            .withFirstname("Test1").withLastname("Test2").withPhoneNumber("111222333").withEmail("test1@test.pl").withGroup("test1");
    app.goTo().newUserPage();
    app.contact().create(user, true);
    Users after = app.contact().all();
    assertThat(after.size(), equalTo(before.size() + 1));
    assertThat(after, equalTo(
            before.withAdded(user.withId(after.stream().mapToInt((u) -> u.getId()).max().getAsInt()))));
  }

}
