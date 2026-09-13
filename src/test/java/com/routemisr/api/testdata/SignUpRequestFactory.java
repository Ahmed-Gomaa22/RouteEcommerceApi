package com.routemisr.api.testdata;

import com.routemisr.api.models.SignUpRequest;
import net.datafaker.Faker;

public class SignUpRequestFactory {
    private static final Faker faker = new Faker();
    public static SignUpRequest validNewUser(){
        String name = faker.name().fullName();
        String email = faker.internet().emailAddress();
        String password = "Test@1234";
        String phone = generateEgyptianPhone();
        return new SignUpRequest(name, password, email , password, phone);
    }
    private static String generateEgyptianPhone(){
        String[] prefixes = {"010", "011", "012", "015"};
        String prefix = prefixes[faker.random().nextInt(prefixes.length)];
        return prefix + faker.number().digits(8);
    }


}

