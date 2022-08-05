package validators;

import base.Reqres;
import extent.ExtentTestManager;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import pojos.UserPOJO;
import utils.Constants;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class UsersTest extends BaseValidation {

    @Test
    public void tc001_statusCodeValidation() {
        ExtentTestManager.startTest(Constants.USERS_ENDPOINT + " StatusCode", "Status Code Verification");
        Response response = Reqres.getInstance().user.getUser.getUsers();
        validateResponseStatusCode(response, Constants.USERS_ENDPOINT, 200);
    }

    @Test
    public void tc002_responseValidation() {
        ExtentTestManager.startTest(Constants.USERS_ENDPOINT + " CheckIfUserExist", "User ID Verification");
        Response response = Reqres.getInstance().user.getUser.getUsers();
        UserPOJO users = getObjectFromResponse(response, UserPOJO.class);

        softAssert.get().assertNotEquals(users.getPer_page(), 0, "account NOT closed");
        softAssert.get().assertNotEquals(users.getPer_page(), 0, "account NOT closed");
        softAssert.get().assertNotEquals(users.getTotal(), 0, "account NOT closed");
        softAssert.get().assertNotEquals(users.getTotal_pages(), 0, "account NOT closed");
        softAssert.get().assertNotEquals(users.getTotal_pages(), 0, "account NOT closed");
        softAssert.get().assertNotNull(users.getSupport().getText(), "account NOT closed");
        softAssert.get().assertNotNull(users.getSupport().getUrl(), "account NOT closed");

        validateList(users.getData().size(), "");
        UserPOJO.Data userData = users.getData().stream().findFirst().get();
        softAssert.get().assertEquals(userData.getId(), 1);
        softAssert.get().assertEquals(userData.getEmail(), "george.bluth@reqres.in");
        softAssert.get().assertEquals(userData.getFirst_name(), "George");
        softAssert.get().assertEquals(userData.getLast_name(), "Bluth");
        softAssert.get().assertEquals(userData.getAvatar(), "https://reqres.in/img/faces/1-image.jpg");
        skipRetryAndSoftAssert();

    }


    @Test
    public void tc003_schemaValidation() {
        ExtentTestManager.startTest(Constants.USERS_ENDPOINT + " SchemaValidation", "Schema Validation");
        Response response = Reqres.getInstance().user.getUser.getUsers();
        response.then().assertThat()
                .body(matchesJsonSchemaInClasspath("users_schema.json"));
    }

    @Test
    public void tc004_userCreation() {
        ExtentTestManager.startTest(Constants.USERS_ENDPOINT + " SchemaValidation", "Schema Validation");

        JSONObject user = new JSONObject();
        user.put("name", "abdur");
        user.put("job", "qa");

        Response response = Reqres.getInstance().user.createUser.createUser_post(user);

        validateResponseStatusCode(response, Constants.USERS_ENDPOINT, 201);

        softAssert.get().assertNotNull(getResponsePath(response, "id").toString(), "");
        softAssert.get().assertEquals(getResponsePath(response, "name").toString(), user.get("name"));
        softAssert.get().assertEquals(getResponsePath(response, "job").toString(), user.get("job"));
        skipRetryAndSoftAssert();
    }
}

