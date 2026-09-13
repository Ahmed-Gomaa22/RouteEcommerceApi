package com.routemisr.api.tests;

import com.routemisr.api.models.SignUpRequest;
import com.routemisr.api.services.SignUpService;
import com.routemisr.api.testdata.SignUpRequestFactory;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class signUpTestCases {
    SignUpService signUpService =  new SignUpService();
    @Test
    public void newUserCanSignUpSuccessfully() {
        SignUpRequest request = SignUpRequestFactory.validNewUser();
        Response response = signUpService.signUp(request);
        signUpService.validateUserCanSignUpSuccessfully(response, request);
    }
}
