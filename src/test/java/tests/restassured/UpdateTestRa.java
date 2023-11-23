package tests.restassured;
import org.testng.Assert;

import org.testng.annotations.Test;

public class UpdateTestRa extends BaseRa {
    @Test
    public void updateContact() {


    String id = contactsService.getIdResponseAddNewContact(createNewContact(), token);
    softAssert.assertEquals(contactsService.getStatusCodeResponseAddNewContact(updateContact(id), token), 200);
    softAssert.assertEquals(contactsService.getMessagePositiveResponseUpdateOneContact(updateContact(id), token), "Contact was updated");
    System.out.println(contactsService.getMessagePositiveResponseUpdateOneContact(updateContact(id), token));

     softAssert.assertAll();
  }
}

