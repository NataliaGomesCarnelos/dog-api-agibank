package com.agibank.client;

import static io.restassured.RestAssured.given;
import com.agibank.config.BaseTest;
import io.restassured.response.Response;

public class DogApiClient extends BaseTest {

    public Response getBreedsImageRandom() {

        return given()
                .spec(requestSpec)
        .when()
                .get("/breeds/image/random");
    }

    public Response getListAllBreeds() {

        return given()
                .spec(requestSpec)
        .when()
                .get("/breeds/list/all");
    }

    public Response getImagesSpecificBreeds(String breed) {

        return given()
                .spec(requestSpec)
        .when()
                .get("/breed/" + breed + "/images");
    }
}