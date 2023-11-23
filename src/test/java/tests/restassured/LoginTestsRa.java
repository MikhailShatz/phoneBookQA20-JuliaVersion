package tests.restassured;

import dto.UserDtoLombok;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTestsRa extends BaseRa{
    @Test
    public void loginStatusCodeTest(){
      //  System.out.println("token from base " + token);
        Assert.assertEquals(userApi.getStatusCodeResponseLogin(userLogin), 200, "status code not 200 for" +
                " login test with correct data");
    }
    @Test
    public void testToken(){
        System.out.println("token " + userApi.getTokenFromLoginResponse(userLogin));
    }
    @Test
    public void negativeLogin(){
        userApi.setResponseLoginNull();
        UserDtoLombok userNeg = UserDtoLombok.builder()
                .username("awqfwf@gmail.com")
                .password("Beer12345")
                .build();
        int statusCode = userApi.getStatusCodeResponseLogin(userNeg);
        userApi.setResponseLoginNull();
        Assert.assertEquals(statusCode, 401);
    }
}
