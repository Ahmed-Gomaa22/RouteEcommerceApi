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

}
