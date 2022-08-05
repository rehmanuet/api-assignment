package base.apiCalls.user;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import utils.Constants;
import validators.BaseValidation;



@Slf4j
public class GetUser {

    /**
     * To retrieve all the users
     */
    public Response getUsers() {
        log.info("getUsers()");
        return RestAssured.get(BaseValidation.BASE_URL + Constants.USERS_ENDPOINT);
    }
}