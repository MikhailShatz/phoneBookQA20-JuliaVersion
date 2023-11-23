package api;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import dto.MessageResponseDTO;
import dto.NewContactDTO;
import dto.UpdateContactDTO;

public class ContactsService extends BaseAPI{
    Response responseAddNewContact = null;
    Response responseDeleteOneContact = null;
    Response responseDeleteAllContacts = null;
    Response responseUpdateOneContact = null;
    private Response getResponseAddNewContact(NewContactDTO newContactDTO, String token){
        responseAddNewContact = RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(newContactDTO)
                .when()
                .post(baseUrl + "/v1/contacts");
        return responseAddNewContact;
    }
    public void setResponseAddNewContactNull() {
        responseAddNewContact = null;
    }

    public int getStatusCodeResponseAddNewContact(NewContactDTO contactDTO, String token) {
        if(responseAddNewContact == null) {
            responseAddNewContact = getResponseAddNewContact(contactDTO, token);
        }
        return responseAddNewContact.getStatusCode();
    }

    public String getMessagePositiveResponseAddNewContact(NewContactDTO contactDTO, String token) {
        if(responseAddNewContact == null) {
            responseAddNewContact = getResponseAddNewContact(contactDTO, token);
        }
        return responseAddNewContact.getBody().as(MessageResponseDTO.class).getMessage();
    }

    public String getIdResponseAddNewContact(NewContactDTO contactDTO, String token) {
        return getMessagePositiveResponseAddNewContact(contactDTO,token).split(":")[1].trim();
    }

    //=====================================responseDeleteOneContact

    private Response getResponseDeleteOneContact(String token, String id){
       responseDeleteOneContact = RestAssured.given()
                .header("Authorization", token)
                .when()
                .delete(baseUrl + "/v1/contacts/" + id);
       return responseDeleteOneContact;
    }
    public void setNullResponseDeleteOneContact(){
        responseDeleteOneContact = null;
    }
    public int getStatusCodeResponseDeleteOneContact(String token, String id){
        if(responseDeleteOneContact == null){
            responseDeleteOneContact = getResponseDeleteOneContact(token, id);

        }
        return responseDeleteOneContact.getStatusCode();
    }
    public String getMessageDeleteOneContact(String token, String id){
        if(responseDeleteOneContact == null){
            responseDeleteOneContact = getResponseDeleteOneContact(token, id);

        }
        return responseDeleteOneContact.getBody().as(MessageResponseDTO.class).getMessage();
    }

    //====================================================================deleteAllContacts
    private Response getResponseDeleteAllContact(String token){
        responseDeleteAllContacts = RestAssured.given()
                .header("Authorization", token)
                .when()
                .delete(baseUrl + "/v1/contacts/clear");
        return responseDeleteAllContacts;
    }

    public void setResponseDeleteAllContactsNull(){
        responseDeleteAllContacts = null;
    }

    public int getStatusCodeResponseDeleteAllContacts(String token){
        if(responseDeleteAllContacts==null){
            responseDeleteAllContacts = getResponseDeleteAllContact(token);
        }
        return responseDeleteAllContacts.getStatusCode();
    }
    public String getMessageResponseDeleteAllContactsPositive(String token){
        if(responseDeleteAllContacts==null){
            responseDeleteAllContacts = getResponseDeleteAllContact(token);
        }
        return responseDeleteAllContacts.getBody().as(MessageResponseDTO.class).getMessage();
    }
    public MessageResponseDTO deleteAllContacts(String token) {
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .when()
                .delete(baseUrl + "/v1/contacts/clear")
                .thenReturn();

        return response.getStatusCode() == 200 ? response.getBody().as(MessageResponseDTO.class) : null;
    }



    //========================================================================update contacts

    private Response getResponseUpdateOneContact(NewContactDTO updateContact, String token){
        responseUpdateOneContact = RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(updateContact)
                .when()
                .put(baseUrl + "/v1/contacts");
        return responseUpdateOneContact;
    }

    public void setResponseUpdateOneContactNull(){
        responseUpdateOneContact = null;
    }
    public int getStatusCodeResponseUpdateOneContact(NewContactDTO updateContact, String token){
        if(responseUpdateOneContact==null){
            responseUpdateOneContact = getResponseUpdateOneContact(updateContact,token);
        }
        return responseUpdateOneContact.getStatusCode();
}

public String getMessagePositiveResponseUpdateOneContact(NewContactDTO updateContact, String token){
    if(responseUpdateOneContact==null){
        responseUpdateOneContact = getResponseUpdateOneContact(updateContact, token);
    }
    return responseUpdateOneContact.getBody().as(MessageResponseDTO.class).getMessage();
}

}
