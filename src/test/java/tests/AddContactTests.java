package tests;

import dto.NewContactDTO;
import manager.ContactHelper;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.RandomUtils;

public class AddContactTests extends BaseTests {
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
    public void addContactPositive(){
        String phone = randomUtils.generateStringDigits(12);
        System.out.println("phone fro the new contact: " + phone);
        logger.info("phone for the new contact: " + phone);
        NewContactDTO newContactDTO = NewContactDTO.builder()
                .address("awf")
                .description("afwaf")
                .email("dwafe23@gmail.com")
                .lastName("Dert")
                .name("Head")
                .phone(phone)
                .build();
        app.getContactHelper().addNewContact(newContactDTO);
        Assert.assertTrue(app.getContactHelper().validateContactCreated(phone));
    }


}
