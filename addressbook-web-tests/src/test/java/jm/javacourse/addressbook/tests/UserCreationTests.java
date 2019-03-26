package jm.javacourse.addressbook.tests;

import jm.javacourse.addressbook.model.GroupData;
import jm.javacourse.addressbook.model.UserData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

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
    List<UserData> before = app.contact().list();
    UserData user = new UserData("Test1", "Test2", "111222333", "test1@test.pl", "test1");
    app.goTo().newUserPage();
    app.contact().create(user, true);
    List<UserData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() + 1);

    user.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    before.add(user);
    Comparator<? super UserData> byId = (u1, u2) -> Integer.compare(u1.getId(), u2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }

}
