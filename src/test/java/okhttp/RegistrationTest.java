package okhttp;

import helpers.*;
import models.AuthenticationRequestModel;
import models.AuthenticationResponseModel;
import models.ErrorModel;
import models.NewUserModel;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;


@Test
public class RegistrationTest implements TestHelper {

    @Test
            public void RegistrationPositiveTest() throws Exception {
    AuthenticationRequestModel requestModel = AuthenticationRequestModel
            .username(EmailGenerator.generateEmail(5,1,3))
            .password(PasswordGenerator.generateString());
    RequestBody requestBody = RequestBody
            .create(TestConfig.gson.toJson(requestModel),
                    TestConfig.JSON);
    Request request = new Request.Builder()
            .url("https://contactapp-telran-backend.herokuapp.com/v1/user/registration/usernamepassword")
            .post(requestBody)
            .build();
    Response response = TestConfig.client.newCall(request).execute();
        System.out.println("Responce code : " + response.code());

        if(response.isSuccessful()){
        AuthenticationResponseModel responseModel =
                TestConfig.gson.fromJson(response.body().string(),
                        AuthenticationResponseModel.class);

        PropertiesWriter.writeProperties("token",responseModel.getToken()); //false);
        System.out.println("Token : "+ responseModel.getToken());
        Assert.assertTrue(response.isSuccessful());
    }
        else {
        System.out.println("Error");
    }


}

    @Test
    public void RegistrationPositive() throws IOException {
        NewUserModel newUserModel = new NewUserModel(EmailGenerator.generateEmail(5,2,3),
                PasswordGenerator.generateString());
        RequestBody requestBody = RequestBody.create(gson.toJson(newUserModel),JSON);
        Request request = new Request.Builder()
                .url(REGISTRATION_PATH)
                .post(requestBody)
                .build();
        Response response = client.newCall(request).execute();
        String res = response.body().string();
        System.out.println("Response : " + res);
        if (response.isSuccessful()){
            AuthenticationResponseModel responseModel = gson.fromJson(res,AuthenticationResponseModel.class);
            String resultToken = responseModel.getToken();
            System.out.println("Token: " + responseModel.getToken());
            System.out.println("Code: " + response.code());
            PropertiesWriterXML propertiesWriterXML = new PropertiesWriterXML(FILE_PATH);
            propertiesWriterXML.setProperties(TOKEN_KEY,resultToken,false );
            Assert.assertTrue(response.isSuccessful());

        }
        else{
            ErrorModel errorModel = gson.fromJson(res, ErrorModel.class);
            System.out.println("STATUS " +errorModel.getStatus());
            System.out.println("ERROR "+errorModel.getError());
            System.out.println("MESSAGE "+errorModel.getMessage());

        }
    }


}
