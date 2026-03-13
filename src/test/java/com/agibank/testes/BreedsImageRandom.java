package com.agibank.testes;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import org.junit.jupiter.api.Test;
import com.agibank.client.DogApiClient;
import com.agibank.config.BaseTest;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class BreedsImageRandom extends BaseTest{

    DogApiClient dogApi = new DogApiClient();

	@Test
	public void testRandomImage() {
		dogApi.getBreedsImageRandom()
		.then()
        .spec(responseSpec)
	    .body(matchesJsonSchemaInClasspath("schemas/random-image-schema.json"))
		.body("status", equalTo("success"))
		.body("message", containsString("https://images.dog.ceo/breeds/"))
		.body("message", containsString(".jpg"));
	}
}
