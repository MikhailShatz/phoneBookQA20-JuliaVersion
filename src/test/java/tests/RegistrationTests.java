package tests;

import dto.UserDtoLombok;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.RandomUtils;

public class RegistrationTests extends BaseTests {

    @BeforeClass(alwaysRun = true)
    public void preconditionsBeforeClass() {
        if(app.isPageUrlHome()) {
            app.getUserHelper().openLoginPage();
        }
    }

    @AfterMethod(alwaysRun = true)
    public void preconditionsBeforeMethod() {
        preconditionForLoginAndRegTests();
    }

    @Test(groups={"smoke","regression"})
    public void positiveRegistration() {
        RandomUtils randomUtils = new RandomUtils();
        String email = randomUtils.generateEmail(7);
        UserDtoLombok user = UserDtoLombok.builder()
                .email(email)
                .password("123456Aa$")
                .build();
        app.getUserHelper().fillRegUserDtoLombok(user);
        flagIsUserLogin = true;
        Assert.assertTrue(app.getUserHelper().validateContactTextDisplaysMainMenu());
    }

    @Test
    public void negativeRegNoSymbol() {
        RandomUtils randomUtils = new RandomUtils();
        String email = randomUtils.generateEmail(7);
        UserDtoLombok user = UserDtoLombok.builder()
                .email(email)
                .password("123456Aa")
                .build();
        app.getUserHelper().fillRegUserDtoLombok(user);
        flagIsAlertPresent = true;
        Assert.assertTrue(app.getUserHelper().validateMessageAlertWrongEmailPasswordCorrectReg());
    }

    @Test
    public void negativeRegNoLetters() {
        RandomUtils randomUtils = new RandomUtils();
        String email = randomUtils.generateEmail(7);
        UserDtoLombok user = UserDtoLombok.builder()
                .email(email)
                .password("12345699#")
                .build();
        app.getUserHelper().fillRegUserDtoLombok(user);
        flagIsAlertPresent = true;
        Assert.assertTrue(app.getUserHelper().validateMessageAlertWrongEmailPasswordCorrectReg());
    }

    @Test
    public void negativeRegNoDigits() {
        RandomUtils randomUtils = new RandomUtils();
        String email = randomUtils.generateEmail(7);
        UserDtoLombok user = UserDtoLombok.builder()
                .email(email)
                .password("Agshsjsks#")
                .build();
        app.getUserHelper().fillRegUserDtoLombok(user);
        flagIsAlertPresent = true;
        Assert.assertTrue(app.getUserHelper().validateMessageAlertWrongEmailPasswordCorrectReg());
    }
}
