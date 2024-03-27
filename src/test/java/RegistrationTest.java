import helpers.EmailGenerator;
import helpers.PasswordGenerator;
import helpers.PropertiesWriter;
import helpers.TestConfig;
import models.AuthenticationRequestModel;
import models.AuthenticationResponseModel;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;


@Test
public class RegistrationTest {

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
}
