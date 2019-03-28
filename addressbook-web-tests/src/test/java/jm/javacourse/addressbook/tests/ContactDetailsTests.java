package jm.javacourse.addressbook.tests;

import jm.javacourse.addressbook.model.GroupData;
import jm.javacourse.addressbook.model.UserData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDetailsTests extends TestBase{

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
              .withEmail("test1@test.pl").withEmail2("test1@work.pl"), true);
    }
    app.contact().returnToHomePage();
  }

  @Test
  public void testContactDetails(){
    app.goTo().gotoHomePage();
    UserData contact = app.contact().all().iterator().next();
    UserData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    UserData contactInfoFromDetailPage = app.contact().infoFromDetailPage(contact);

    assertThat(contactInfoFromDetailPage.getDetails(), equalTo(mergeDetails(contactInfoFromEditForm)));
  }

  private String mergeDetails(UserData contact) {
    return Arrays.asList(
            contact.getFirstname() + " " + contact.getLastname(),
            contact.getAddress(),
            "",
            mergePhones(contact),
            "",
            mergeEmails(contact),
            "",
            "",
            "Member of: test1"
            ).stream()
            .collect(Collectors.joining("\n"));
  }

  private String mergePhones(UserData contact) {
    return Arrays.asList(
            "H: "+cleaned(contact.getHomePhone()),
            "M: "+cleaned(contact.getMobilePhone()),
            "W: "+cleaned(contact.getWorkPhone())).stream()
            .filter((s) -> !s.matches("^[HMW]: $"))
            .collect(Collectors.joining("\n"));
  }

  public static String cleaned (String phone){
    return phone.replaceAll("\\s","").replaceAll("[-()]","");
  }

  private String mergeEmails(UserData contact) {
    return Arrays.asList(contact.getEmail(), contact.getEmail2()).stream()
            .filter((s) -> !s.equals(""))
            .collect(Collectors.joining("\n"));
  }
}
