package jm.javacourse.addressbook.tests;

import jm.javacourse.addressbook.model.GroupData;
import jm.javacourse.addressbook.model.UserData;
import jm.javacourse.addressbook.model.Users;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class UserGroupRemovingTests extends TestBase {

  GroupData group;

  @BeforeMethod
  public void ensurePreconditions() {
    group = app.db().groups().stream().filter(g -> g.getUsers().size() > 0).findFirst().orElse(null);

    if (group == null) {
      app.goTo().groupPage();
      group = new GroupData().withName("test1");
      app.group().create(group);

      app.goTo().newUserPage();
      app.contact().create(new UserData()
              .withFirstname("Test1").withLastname("Test2")
              .withHomePhone("111").withMobilePhone("222").withWorkPhone("333")
              .withEmail("test1@test.pl")
              .inGroup(group), true);
    }
    app.contact().returnToHomePage();
  }

  @Test
  public void testRemovingUserFromGroup() {
    Users before = new Users(app.db().users().stream()
            .filter(u -> u.getGroups().stream()
                    .anyMatch(g -> g.equals(group)))
            .collect(Collectors.toList()));

    UserData modifiedUser = before.iterator().next();
    app.contact().removeFromGroup(modifiedUser, group);

    Users after = new Users(app.db().users().stream()
            .filter(u -> u.getGroups().stream()
                    .anyMatch(g -> g.equals(group)))
            .collect(Collectors.toList()));

    assertThat(after, equalTo(before.without(modifiedUser)));
  }


}
