package tests.restassured;

import dto.MessageResponseDTO;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeleteContactsTests extends BaseRa{
    @Test
    public void deleteContactTest(){
        contactsService.setResponseAddNewContactNull();
        String id = contactsService.getIdResponseAddNewContact(createNewContact(), token);
        contactsService.setResponseAddNewContactNull();
        softAssert.assertEquals(contactsService.getStatusCodeResponseDeleteOneContact(token, id), 200);
        System.out.println(contactsService.getMessageDeleteOneContact(token, id));
        softAssert.assertEquals(contactsService.getMessageDeleteOneContact(token, id), "Contact was deleted");
        contactsService.setNullResponseDeleteOneContact();
        softAssert.assertAll();
    }

    @Test
    public void deleteAllContacts(){
        softAssert.assertEquals(contactsService.getStatusCodeResponseDeleteAllContacts(token), 200);
        System.out.println(contactsService.getMessageResponseDeleteAllContactsPositive(token));
        softAssert.assertEquals(contactsService.getMessageResponseDeleteAllContactsPositive(token), "Contact was deleted");
        softAssert.assertAll();
    }

    @Test
    public void deleteAllContactsWhenExists(){
        contactsService.getStatusCodeResponseAddNewContact(createNewContact(), token);

        MessageResponseDTO response = contactsService.deleteAllContacts(token);
        Assert.assertNotNull(response, "Response should not be null");
        Assert.assertEquals(response.getMessage(), "All contacts was deleted!", "Unexpected response message");
    }

    @Test
    public void deleteAllContactsWhenContactDoesNotExists(){
        contactsService.deleteAllContacts(token);
        MessageResponseDTO response = contactsService.deleteAllContacts(token);
        Assert.assertNotNull(response, "Response should not be null");
        Assert.assertEquals(response.getMessage(), "All contacts was deleted!", "Unexpected response message");
    }
}
