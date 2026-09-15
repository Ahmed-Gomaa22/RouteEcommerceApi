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
    @Test
    public void userCantSignUpWithExistingEmail() {
        SignUpRequest firstUser = SignUpRequestFactory.validNewUser();
        signUpService.signUp(firstUser);

        SignUpRequest duplicatedUser = SignUpRequestFactory.duplicateOf(firstUser);
        Response response = signUpService.signUp(duplicatedUser);
        signUpService.validateUserCantSignUpWithExistingEmail(response, duplicatedUser);
    }
    @Test
    public void userCantSignUpWithInvalidEmail() {
        SignUpRequest invalidEmailUser = SignUpRequestFactory.invalidEmail();
        signUpService.signUp(invalidEmailUser);
        Response response = signUpService.signUp(invalidEmailUser);
        signUpService.validateUserCantSignUpWithInvalidEmail(response,invalidEmailUser);
    }

    @Test
    public void userCantSignUpWithNoEmailProvided() {
        SignUpRequest noEmailUser = SignUpRequestFactory.missingEmail();
        signUpService.signUp(noEmailUser);
        Response response = signUpService.signUp(noEmailUser);
        signUpService.validateUserCantSignUpWithNoEmailProvided(response,noEmailUser);
    }

    @Test
    public void userCantSignUpWithWrongPasswordConfirmation() {
        SignUpRequest wrongPassConfirm = SignUpRequestFactory.invalidPasswordConfirmation();
        signUpService.signUp(wrongPassConfirm);
        Response response = signUpService.signUp(wrongPassConfirm);
        signUpService.validateUserCantSignUpWithInvalidPasswordConfirmation(response,wrongPassConfirm);
    }
}
