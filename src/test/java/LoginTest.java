import helpers.PropertiesReader;
import helpers.PropertiesWriter;
import helpers.TestConfig;
import models.AuthenticationRequestModel;
import models.AuthenticationResponseModel;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest {
    @Test
    public void loginPositive() throws IOException {
        // AuthenticationRequestModel класс представляет модель запроса аутентификации.
        AuthenticationRequestModel requestModel = AuthenticationRequestModel
                .username(PropertiesReader.getProperty("existingUserEmail"))
                .password(PropertiesReader.getProperty("existingUserPassword"));
        RequestBody requestBody = RequestBody
                .create(TestConfig.gson.toJson(requestModel),
                        TestConfig.JSON); // Создается объект RequestBody, содержащий данные запроса в формате JSON.
        Request request = new Request.Builder()
                .url("https://contactapp-telran-backend.herokuapp.com/v1/user/login/usernamepassword")
                .post(requestBody)
                .build();
        Response response = TestConfig.client.newCall(request).execute(); // Выполняется HTTP-запрос с помощью объекта client из класса TestConfig, и возвращается объект Response т.е. какой-то ответ.
        System.out.println("Responce code : " + response.code());
// если ответ содержит код 2** , то все прошло успешно.
        if(response.isSuccessful()){
            // И теперь можно десериализовать в модель объекта с которым можно работать дальше. т.е. из объекта JSON получили объект класса AuthenticationRequestModel
            AuthenticationResponseModel responseModel =
                    TestConfig.gson.fromJson(response.body().string(),
                            AuthenticationResponseModel.class);

            PropertiesWriter.writeProperties("token ",responseModel.getToken());//, false);
            System.out.println("Token : "+ responseModel.getToken());
            Assert.assertTrue(response.isSuccessful());
        }
        else {
            System.out.println("Error");
        }


    }
}