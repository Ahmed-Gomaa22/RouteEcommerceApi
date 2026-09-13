package com.routemisr.api.services;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import io.github.cdimascio.dotenv.Dotenv;

public class BaseService {
    protected final RequestSpecification requestSpec;
    private static final Dotenv dotenv = Dotenv.load();
    public BaseService() {
        this.requestSpec = new RequestSpecBuilder().
                setBaseUri(dotenv.get("BASE_URL")).
                setContentType("application/json").build();
    }
}
