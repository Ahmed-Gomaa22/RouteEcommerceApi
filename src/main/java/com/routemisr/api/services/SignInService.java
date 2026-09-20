package com.routemisr.api.services;

import com.routemisr.api.models.SignInRequest;
import com.routemisr.config.http.endpoints.AuthEndpoints;
import io.restassured.response.Response;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;


import static io.restassured.RestAssured.given;

public class SignInService extends BaseService {
    public Response signIn(SignInRequest signInRequest) {
        String signInEndpoint = AuthEndpoints.SIGN_IN_ENDPOINT;
        return given().spec(requestSpec).body(signInRequest)
                .when().post(signInEndpoint);
    }
    public void validateUserCanSignInAfterRegistrationSuccessfully(Response response, SignInRequest signInRequest) {
        response.then().statusCode(200);
        assertEquals(response.jsonPath().getString("user.email"), signInRequest.getEmail());
        assertNotNull(response.jsonPath().getString("token"));
    }
    public String getUserToken(Response response) {
       return response.jsonPath().getString("token");
    }

    public void validateUserCantSignInWithIncorrectPass(Response response) {
        response.then().statusCode(401);
        assertEquals(response.jsonPath().getString("message"), "Incorrect email or password");
    }
    public void validateUserCantSignInWithInvalidEmailFormat(Response response) {
        response.then().statusCode(400);
        assertEquals(response.jsonPath().getString("errors.msg"), "Invalid email");
    }
    public void validateThatPasswordFieldIsRequired(Response response) {
        response.then().statusCode(400);
        assertEquals(response.jsonPath().getString("errors.msg"), "Password is required");
    }

}
