package tests.restassured;

import dto.UserDtoLombok;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegTestRa extends BaseRa{
    @Test
    public void RegStatusCodeTest(){
        Assert.assertEquals(userApi.getStatusCodeResponseReg(userReg), 200,
                "status code not 200 for registration test with correct data");
    }

    @Test
    public void testToken(){
        System.out.println("token " + userApi.getTokenFromRegResponse(userReg));
    }
    @Test
    public void negativeRegistration(){
        userApi.setResponseRegNull();
        UserDtoLombok userNeg = UserDtoLombok.builder()
                .username("awqfwfgmail.com")
                .password("Beer12345")
                .build();
        int statusCode = userApi.getStatusCodeResponseReg(userNeg);
        userApi.setResponseRegNull();
        Assert.assertEquals(statusCode, 400);
    }
}
