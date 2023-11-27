package tests;

import api.ContactsService;
import api.UserApi;
import dto.NewContactDTO;
import dto.UserDtoLombok;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class DeleteOneContactTestWithApi extends BaseTests{
    UserApi userApi = new UserApi();
    ContactsService contactsService = new ContactsService();
    static String token = "";

    SoftAssert softAssert = new SoftAssert();

    UserDtoLombok userLogin = UserDtoLombok.builder()
            .username("awqfwf@gmail.com")
            .password("Beer12345!")
            .build();

    @BeforeSuite
    public void preConditionApiRALog(){
        token =  userApi.getTokenFromLoginResponse(userLogin);
        app.init();
    }

    @BeforeClass(alwaysRun = true)
    public void preconditionsBeforeClass() {
        if(app.isPageUrlHome()) {
            app.getUserHelper().openLoginPage();
            app.getUserHelper().fillLoginUserDtoLombok(user);
        }
    }

    @AfterClass(alwaysRun = true)
    public void postConditions() throws InterruptedException {
        app.getUserHelper().logout();
        Thread.sleep(500);
    }

    @Test
    public void deleteCreatedContactPositive() throws InterruptedException {
        String phone = randomUtils.generateStringDigits(12);
        System.out.println("phone fro the new contact: " + phone);
        logger.info("phone fro the new contact: " + phone);
        NewContactDTO newContactDTO = NewContactDTO.builder()
                .address("awf")
                .description("afwaf")
                .email("dwafe23@gmail.com")
                .lastName("Dert")
                .name("Head")
                .phone(phone)
                .build();
        softAssert.assertEquals(contactsService.getStatusCodeResponseAddNewContact(newContactDTO, token), 200);
        // app.getContactHelper().addNewContact(newContactDTO);
        app.getContactHelper().openContactInfoByPhone(phone);
        Thread.sleep(5000);
        app.getContactHelper().removeActiveContact();
        app.getContactHelper().navigateToContactPage();
        System.out.println(phone);
        Thread.sleep(5000);
        softAssert.assertTrue(app.getContactHelper().validateContactCreated(phone));
        softAssert.assertAll();
    }
}
