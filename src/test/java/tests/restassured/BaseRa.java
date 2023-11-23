package tests.restassured;

import api.ContactsService;
import api.UserApi;
import dto.NewContactDTO;
import dto.UpdateContactDTO;
import dto.UserDtoLombok;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;
import utils.RandomUtils;

public class BaseRa {
    UserApi userApi = new UserApi();
    ContactsService contactsService = new ContactsService();
    static String token = "";
    RandomUtils randomUtils = new RandomUtils();
    SoftAssert softAssert = new SoftAssert();
    String email = randomUtils.generateEmail(7);
    UserDtoLombok userLogin = UserDtoLombok.builder()
            .username("awqfwf@gmail.com")
            .password("Beer12345!")
            .build();
    UserDtoLombok userReg = UserDtoLombok.builder()
            .username(email)
            .password("Beer12345!")
            .build();

    @BeforeSuite
    public void preConditionApiRALog(){
      token =  userApi.getTokenFromLoginResponse(userLogin);
    }

    @BeforeSuite
    public void preConditionApiRAReg(){
        token =  userApi.getTokenFromRegResponse(userReg);
    }

    public NewContactDTO createNewContact(){
        String phoneNumber = randomUtils.generateStringDigits(12);
        NewContactDTO contact1 = NewContactDTO.builder()
                .address("afawf")
                .description("awdtjrtf")
                .email("sehjkw124@gmail.com")
                .id("0")
                .lastName("Kuk")
                .name("Old")
                .phone(phoneNumber)
                .build();
        return contact1;
    }
    public NewContactDTO updateContact(String id){
        String phoneNumber = randomUtils.generateStringDigits(12);
        NewContactDTO contact1 = NewContactDTO.builder()
                .id(id)
                .name("Old")
                .lastName("Pal")
                .email("sehjkw124@gmail.com")
                .phone(phoneNumber)
                .address("af34wf")
                .description("awd2tjrtf")
                .build();
        return contact1;
    }
}
