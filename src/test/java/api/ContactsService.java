package api;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import dto.AllContactsDTO;
import dto.MessageResponseDTO;
import dto.NewContactDTO;
import dto.UpdateContactDTO;

import static com.jayway.restassured.RestAssured.given;

public class ContactsService extends BaseAPI{
    Response responseAddNewContact = null;
    Response responseDeleteOneContact = null;
    Response responseDeleteAllContacts = null;
    Response responseUpdateOneContact = null;
    Response responseGetAllContacts = null;
    private Response getResponseAddNewContact(NewContactDTO newContactDTO, String token){
        responseAddNewContact = given()
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
       responseDeleteOneContact = given()
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
        responseDeleteAllContacts = given()
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
        Response response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .when()
                .delete(baseUrl + "/v1/contacts/clear")
                .thenReturn();

        return response.getStatusCode() == 200 ? response.getBody().as(MessageResponseDTO.class) : null;
    }



    //========================================================================update contacts

    private Response getResponseUpdateOneContact(NewContactDTO updateContact, String token){
        responseUpdateOneContact = given()
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
    return responseUpdateOneContact.then().extract().path("message");
}
//===============================GetAllContacts========================

    private Response getResponseGetAllContacts(String token){
        responseGetAllContacts = given()
                .header("Authorization", token)
                .when()
                .get(baseUrl + "/v1/contacts");
        return responseGetAllContacts;
    }

    public int getStatusCodeResponseGetAllContacts(String token){
        if(responseGetAllContacts==null){
            responseGetAllContacts = getResponseGetAllContacts(token);
        }
        return responseGetAllContacts.getStatusCode();
    }

    public boolean isIdInTheAllContactResponse(String token, String id) {
        if(responseGetAllContacts==null){
            responseGetAllContacts = getResponseGetAllContacts(token);
        }
        NewContactDTO[] contacts = responseGetAllContacts.getBody().as(AllContactsDTO.class).getContacts();
        boolean flag = false;
        for(int i = 0; i<contacts.length; i++){
            if(contacts[i].getId().equals(id)){
                System.out.println("id from isId... " + contacts[i].getId());
                flag = true;
                return true;
            }

        }
        return flag;
    }
}
