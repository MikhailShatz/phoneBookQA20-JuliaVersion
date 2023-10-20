package tests;

import dto.UserDtoLombok;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.RandomUtils;

public class RegistrationTests extends BaseTests {

    @Test
    public void positiveRegistration() {
        RandomUtils randomUtils = new RandomUtils();
        String email = randomUtils.generateEmail(7);
        UserDtoLombok user = UserDtoLombok.builder()
                .email(email)
                .password("123456Aa$")
                .build();
        app.getUserHelper().fillRegUserDtoLombok(user);
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
        Assert.assertTrue(app.getUserHelper().validateMessageAlertWrongEmailPasswordCorrectReg());
    }
}
