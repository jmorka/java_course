package jm.javacourse.addressbook.tests;

import jm.javacourse.addressbook.model.GroupData;
import jm.javacourse.addressbook.model.UserData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class UserModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.contact().list().size() == 0) {
      app.goTo().groupPage();
      if (app.group().all().size() == 0) {
        app.group().create(new GroupData().withName("test1"));
      }

      app.goTo().newUserPage();
      app.contact().create(new UserData("Test1", "Test2", "111222333", "test1@test.pl", "test1"), true);
    }
  }

  @Test
  public void testUserModification() {
    List<UserData> before = app.contact().list();
    int index = 0;
    UserData user = new UserData(before.get(index).getId(), "Test3", "Test4", "511222333", "test6@test.pl", null);
    app.contact().modify(user);
    List<UserData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(user);
    Comparator<? super UserData> byId = (u1, u2) -> Integer.compare(u1.getId(), u2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);

  }


}
