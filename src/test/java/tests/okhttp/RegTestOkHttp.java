package tests.okhttp;

import com.google.gson.Gson;
import dto.AuthResponseDTO;
import dto.ErrorDTO;
import dto.UserDtoLombok;
import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.BaseTests;
import java.io.IOException;

public class RegTestOkHttp extends BaseTests {
    String email = randomUtils.generateEmail(7);
    UserDtoLombok user = UserDtoLombok.builder()
            .username(email)
            .password("Beer12345!")
            .build();

    UserDtoLombok userNegative = UserDtoLombok.builder()
            .username("Beer123@gmail.com")
            .password("Beer12345")
            .build();
    public static final MediaType JSON = MediaType.get("application/json");
    Gson gson = new Gson();
    OkHttpClient okHttpClient = new OkHttpClient();
    String baseUrl = "https://contactapp-telran-backend.herokuapp.com";

    @Test
    public void regPositive(){
        RequestBody requestBody = RequestBody.create(gson.toJson(user), JSON);
        Request request = new Request.Builder()
                .url(baseUrl + "/v1/user/registration/usernamepassword")
                .post(requestBody)
                .build();
        Response response;
        try {
            response = okHttpClient.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (response==null){
            Assert.fail("got null response");
        } else if (response.isSuccessful()) { //return OK 200 and token
            String responseJson;
            try {
                responseJson = response.body().string();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            AuthResponseDTO authResponseDTO = gson.fromJson(responseJson, AuthResponseDTO.class);
            System.out.println(authResponseDTO.getToken());
            System.out.println(response.code());
            System.out.println(response.message());
            Assert.assertEquals(response.code(), 200, "response not 200");
        }else {
            System.out.println(response.code() + " error");
            Assert.fail("response not successfully");
        }
    }

    @Test
    public void regNegative(){
        RequestBody requestBody = RequestBody.create(gson.toJson(userNegative), JSON);
        Request request = new Request.Builder()
                .url(baseUrl + "/v1/user/registration/usernamepassword")
                .post(requestBody)
                .build();
        Response response;
        try {
            response = okHttpClient.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (response==null){
            Assert.fail("got null response");
        } else if (response.isSuccessful()) { //return OK 200 and token
            Assert.fail("got response with status code " + response.code());
        }else if(response.code()==409) {
            String responseJson;
            try {
                responseJson = response.body().string();
                System.out.println("Response JSON: " + responseJson);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            ErrorDTO errorDTO = gson.fromJson(responseJson, ErrorDTO.class);
            System.out.println(response.code());
            System.out.println(response.message());
            System.out.println("string error " + errorDTO.getError());
            System.out.println("int status " + errorDTO.getStatus());
            Assert.assertEquals(response.code(), 409, "response not 409");
        }else  {
            System.out.println("Conflict detected. Status code: " + response.code());
        }
    }
}
