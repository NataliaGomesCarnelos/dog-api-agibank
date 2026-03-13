package com.agibank;

import org.junit.jupiter.api.BeforeAll;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class BaseTest {

    protected static RequestSpecification requestSpec;

    @BeforeAll
    public static void setup() {

        RestAssured.baseURI = "https://dog.ceo/api";

        requestSpec = new RequestSpecBuilder()
                .setBaseUri(RestAssured.baseURI)
                .setContentType("application/json")
                .build();
    }
}