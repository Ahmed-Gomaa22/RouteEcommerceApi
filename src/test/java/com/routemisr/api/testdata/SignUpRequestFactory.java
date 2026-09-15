package com.routemisr.api.testdata;

import com.routemisr.api.models.SignUpRequest;
import net.datafaker.Faker;

public class SignUpRequestFactory {
    private static final Faker faker = new Faker();
    private static SignUpRequest validBase(){
        String name = faker.name().fullName();
        String email = faker.internet().emailAddress();
        String password = "Test@1234";
        String phone = generateEgyptianPhone();
        return new SignUpRequest(name, password, email , password, phone);
    }
    public static SignUpRequest validNewUser(){
        return validBase();
    }
    private static String generateEgyptianPhone(){
        String[] prefixes = {"010", "011", "012", "015"};
        String prefix = prefixes[faker.random().nextInt(prefixes.length)];
        return prefix + faker.number().digits(8);
    }
    public static SignUpRequest duplicateOf(SignUpRequest original){
        SignUpRequest base = validBase();
        return new SignUpRequest(base.getName(), original.getPassword(), original.getEmail(), original.getRePassword(), base.getPhone());
    }

    public static SignUpRequest invalidEmail(){
        SignUpRequest base = validBase();
        return new SignUpRequest(base.getName(), base.getPassword(), "invalid@mail", base.getRePassword(), base.getPhone());
    }
    public static SignUpRequest invalidPasswordConfirmation(){
        SignUpRequest base = validBase();
        return new SignUpRequest(base.getName(), base.getPassword(), base.getEmail(), "invalid123", base.getPhone());
    }
    public static SignUpRequest missingEmail(){
        SignUpRequest base = validBase();
        return new SignUpRequest(base.getName(), base.getPassword(), null, base.getRePassword(), base.getPhone());
    }

}

