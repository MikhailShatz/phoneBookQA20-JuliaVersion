package tests;

import dto.UserDTO;
import dto.UserDTOWith;
import dto.UserDtoLombok;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTests {
    UserDTOWith userDTOWith = new UserDTOWith()
            .withEmail("testqa20@gmail.com")
            .withPassword("123456Aa$");
    UserDTO userDTO = new UserDTO("testqa20@gmail.com", "123456Aa$");

    @Test
    public void positiveLoginUserDto() {
        logger.info("logger info: start test positiveLoginUserDto");
        logger.info(String
                .format("in the next function: open login page, fill email input with email: %s and with the password: %s and click on button login",
                        userDTO.getEmail(), userDTO.getPassword()));
        app.getUserHelper().fillLoginUserDto(userDTO);
        logger.info("validation by assertTrue, that contacts link displays in the menu");
        Assert.assertTrue(app.getUserHelper().validateContactTextDisplaysMainMenu());
    }

    @Test
    public void positiveLoginUserDtoWith() {
        app.getUserHelper().fillLoginUserDtoWith(userDTOWith);
        Assert.assertTrue(app.getUserHelper().validateContactTextDisplaysMainMenu());
    }

    @Test
    public void positiveLoginUserDtoLombok() {
        UserDtoLombok user = UserDtoLombok.builder()
                .email("testqa20@gmail.com")
                .password("123456Aa$")
                .build();
        app.getUserHelper().fillLoginUserDtoLombok(user);
        Assert.assertTrue(app.getUserHelper().validateContactTextDisplaysMainMenu());
    }

    @Test
    public void negativeWrongPasswordWrongSymbol() {
        UserDtoLombok user = UserDtoLombok.builder()
                .email("testqa20@gmail.com")
                .password("123456Aa5")
                .build();
        app.getUserHelper().fillLoginUserDtoLombok(user);
        Assert.assertTrue(app.getUserHelper().validateMessageAlertWrongEmailPasswordCorrect()); // Wrong email or password
    }

    @Test
    public void negativeWrongPasswordNoLetters() {
        UserDtoLombok user = UserDtoLombok.builder()
                .email("testqa20@gmail.com")
                .password("12345655$")
                .build();
        app.getUserHelper().fillLoginUserDtoLombok(user);
        Assert.assertTrue(app.getUserHelper().validateMessageAlertWrongEmailPasswordCorrect()); // Wrong email or password
    }

    @Test
    public void negativeWrongPasswordNoDigits() {
        UserDtoLombok user = UserDtoLombok.builder()
                .email("testqa20@gmail.com")
                .password("ajdsbH#$dmk")
                .build();
        app.getUserHelper().fillLoginUserDtoLombok(user);
        Assert.assertTrue(app.getUserHelper().validateMessageAlertWrongEmailPasswordCorrect()); // Wrong email or password
    }


}
