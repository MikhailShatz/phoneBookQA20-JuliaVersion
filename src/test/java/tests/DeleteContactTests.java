package tests;

import dto.NewContactDTO;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DeleteContactTests extends BaseTests{
    @BeforeClass(alwaysRun = true)
    public void preconditionsBeforeClass() {
        if(app.isPageUrlHome()) {
            app.getUserHelper().openLoginPage();
            app.getUserHelper().fillLoginUserDtoLombok(user);
        }
    }

    @AfterClass(alwaysRun = true)
    public void postConditions(){
        app.getUserHelper().logout();
    }

    @Test
    public void deleteCreatedContactPositive(){
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
        app.getContactHelper().addNewContact(newContactDTO);
        app.getContactHelper().openContactInfoByPhone(phone);
        app.getContactHelper().removeActiveContact();
        app.getContactHelper().navigateToContactPage();
        Assert.assertTrue(app.getContactHelper().validateContactCreated(phone));
    }
}
