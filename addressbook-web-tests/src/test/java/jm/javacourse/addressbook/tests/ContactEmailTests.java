package jm.javacourse.addressbook.tests;

import jm.javacourse.addressbook.model.GroupData;
import jm.javacourse.addressbook.model.UserData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEmailTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.contact().all().size() == 0) {
      app.goTo().groupPage();
      if (app.group().all().size() == 0) {
        app.group().create(new GroupData().withName("test1"));
      }
      app.goTo().newUserPage();
      app.contact().create(new UserData()
              .withFirstname("Test1").withLastname("Test2")
              .withHomePhone("111").withMobilePhone("222").withWorkPhone("333")
              .withAddress("Marszalkowska\nWarsaw")
              .withEmail("test1@test.pl").withEmail2("test1@work.pl")
              .withGroup("test1"), true);
    }
    app.contact().returnToHomePage();
  }

  @Test
  public void testContactPhones(){
    app.goTo().gotoHomePage();
    UserData contact = app.contact().all().iterator().next();
    UserData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
  }

  private String mergeEmails(UserData contact) {
    return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3()).stream()
            .filter((s) -> !s.equals(""))
            .collect(Collectors.joining("\n"));
  }
}
