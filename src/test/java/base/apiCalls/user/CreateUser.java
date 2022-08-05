package base.apiCalls.user;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import pojos.UserPOJO;
import utils.Constants;
import validators.BaseValidation;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.config;
import static validators.BaseValidation.BASE_URL;


@Slf4j
public class CreateUser {

    /**
     * To retrieve all the users
     */
    public Response createUser_post(JSONObject user) {
        log.info("createUser_post()");
        RequestSpecBuilder build = new RequestSpecBuilder();
        build.setBaseUri(BASE_URL)
                .addHeader("Accept", "application/json")
                .addHeader("Content-type", "application/json");
        RequestSpecification requestSpecification = build.setConfig(config).build().body(user);

        return RestAssured.given().spec(requestSpecification).post(Constants.USERS_ENDPOINT);
    }

}