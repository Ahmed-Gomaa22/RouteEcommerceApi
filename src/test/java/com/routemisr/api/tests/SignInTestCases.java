package com.routemisr.api.tests;

import com.routemisr.api.models.SignInRequest;
import com.routemisr.api.models.SignUpRequest;
import com.routemisr.api.services.SignInService;
import com.routemisr.api.services.SignUpService;
import com.routemisr.api.testdata.SignUpRequestFactory;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class SignInTestCases {
    SignUpService signUpService = new SignUpService();
    SignInService signInService = new SignInService();
    @Test
    public void userCanSignInAfterRegistration() {
        SignUpRequest signUpRequest = SignUpRequestFactory.validNewUser();
        signUpService.signUp(signUpRequest);
        SignInRequest signInRequest = new SignInRequest(signUpRequest.getEmail(), signUpRequest.getPassword());
        Response response = signInService.signIn(signInRequest);
        signInService.validateUserCanSignInAfterRegistrationSuccessfully(response, signInRequest);
    }

    @Test
    public void userCantSignInWithWrongPassword(){
        SignUpRequest signUpRequest = SignUpRequestFactory.validNewUser();
        signUpService.signUp(signUpRequest);
        SignInRequest signInRequest = new SignInRequest(signUpRequest.getEmail(), "wrongPassword");
        Response response = signInService.signIn(signInRequest);
        signInService.validateUserCantSignInWithIncorrectPass(response);
    }
    @Test
    public void userCantSignInWithInvalidEmailFormat(){
        SignInRequest signInRequest = new SignInRequest("invalidEmail", "Test@1234");
        Response response = signInService.signIn(signInRequest);
        signInService.validateUserCantSignInWithInvalidEmailFormat(response);
    }
    @Test
    public void userCantSignInWithMissingPassword(){
        SignInRequest signInRequest = new SignInRequest("ahmedmuttii4112@gmail.com", null);
        Response response = signInService.signIn(signInRequest);
        signInService.validateThatPasswordFieldIsRequired(response);
    }

}
