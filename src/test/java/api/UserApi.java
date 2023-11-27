package api;


import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import dto.AuthResponseDTO;
import dto.UserDtoLombok;

import static com.jayway.restassured.RestAssured.given;

public class UserApi extends BaseAPI{
    Response responseLogin = null;
    Response responseReg = null;
    private Response regRequest(UserDtoLombok userReg){
        System.out.println("-----------------------------------regRequest method run");
        responseReg = given()
                .contentType(ContentType.JSON)
                .body(userReg)
                .when()
                .post(baseUrl + "/v1/user/registration/usernamepassword")
                .thenReturn();
        return responseReg;
    }

    public void setResponseRegNull(){
        responseReg = null;
    }

    public int getStatusCodeResponseReg(UserDtoLombok userReg){
        if(responseReg==null) {
            responseReg = regRequest(userReg);
        }
        return responseReg.getStatusCode();
    }
    public String getTokenFromRegResponse(UserDtoLombok userReg){
        if(responseReg==null) {
            responseReg = regRequest(userReg);
        }
        return responseReg.then().extract().path("token");
    }
    private Response loginRequest(UserDtoLombok userLogin){
        System.out.println("-----------------------------------loginRequest method run");
        responseLogin = given()
                .contentType(ContentType.JSON)
                .body(userLogin)
                .when()
                .post(baseUrl + "/v1/user/login/usernamepassword")
                .thenReturn();
        return responseLogin;
    }

    public void setResponseLoginNull(){
        responseLogin = null;
    }
    public int getStatusCodeResponseLogin(UserDtoLombok userLogin){
        if (responseLogin == null){
            loginRequest(userLogin);

        }
        return responseLogin.getStatusCode();
    }

    public String getTokenFromLoginResponse(UserDtoLombok userLogin){
        if (responseLogin == null){
           responseLogin = loginRequest(userLogin);
        }
        return responseLogin.body().as(AuthResponseDTO.class).getToken();
    }
}
