package tests.restassured;

import org.testng.annotations.Test;

public class AllContactsTests extends BaseRa{
    @Test
    public void testAllContacts() throws InterruptedException {
        softAssert.assertEquals(contactsService.getStatusCodeResponseAddNewContact(createNewContact(), token), 200);
        String id = contactsService.getIdResponseAddNewContact(createNewContact(), token);
        System.out.println("id created contact " + id);
        Thread.sleep(3000);
        softAssert.assertEquals(contactsService.getStatusCodeResponseGetAllContacts(token), 200);
        softAssert.assertTrue(contactsService.isIdInTheAllContactResponse(token, id));
        softAssert.assertAll();
    }
}
