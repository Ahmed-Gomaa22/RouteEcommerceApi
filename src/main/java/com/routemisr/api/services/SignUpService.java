package com.routemisr.api.services;

import com.routemisr.api.models.SignUpRequest;
import com.routemisr.config.http.endpoints.AuthEndpoints;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class SignUpService extends BaseService {
    public Response signUp(SignUpRequest signUpRequest) {
        String signUpEndpoint = AuthEndpoints.SIGN_UP_ENDPOINT;
        return given().spec(requestSpec)
                .body(signUpRequest)
                .when().post(signUpEndpoint);
    }

    public void validateUserCanSignUpSuccessfully(Response response, SignUpRequest request) {
        response.then().statusCode(201);
        assertEquals(response.jsonPath().getString("user.email"), request.getEmail());
        assertEquals(response.jsonPath().getString("user.name"), request.getName());
        assertNotNull(response.jsonPath().getString("token"));
    }
    public void validateUserCantSignUpWithExistingEmail(Response response, SignUpRequest request) {
        response.then().statusCode(409);
        assertEquals(response.jsonPath().getString("message"), "Account Already Exists");
    }
    public void validateUserCantSignUpWithInvalidEmail(Response response, SignUpRequest request) {
        response.then().statusCode(400);
        assertEquals(response.jsonPath().getString("errors.msg"), "Invalid email ");
    }
    public void validateUserCantSignUpWithNoEmailProvided(Response response, SignUpRequest request) {
        response.then().statusCode(400);
        assertEquals(response.jsonPath().getString("errors.msg"), "Email is required");
    }
    public void validateUserCantSignUpWithInvalidPasswordConfirmation(Response response, SignUpRequest request) {
        response.then().statusCode(400);
        assertEquals(response.jsonPath().getString("errors.msg"), "Password confirmation is incorrect");
    }

}
