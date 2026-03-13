package com.agibank.testes;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.startsWith;
import java.util.List;
import org.junit.jupiter.api.Test;

import com.agibank.client.DogApiClient;
import com.agibank.config.BaseTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ImagesSpecificBreeds extends BaseTest {
	
    DogApiClient dogApi = new DogApiClient();

	@Test
	public void testBreedImages() {
		String breed = "hound";
		
		Response response = dogApi.getImagesSpecificBreeds(breed)
		.then()
        .spec(responseSpec)
		.extract().response();
		
		response.then()
        .body("status", equalTo("success"))
        .body("message", not(empty()))
        .body("message", everyItem(startsWith("https://images.dog.ceo")))
		.body("message", everyItem(containsString(breed)))
        .body("message.size()", greaterThan(100));
		
		List<String> imagens = response.jsonPath().getList("message");
		
		imagens.stream()
		        .limit(5)
		        .parallel()
		        .forEach(url ->
		                RestAssured.given()
		                        .when()
		                        .get(url)
		                        .then()
		                        .statusCode(200)
		        );
	}
	
	@Test
	public void testBreedImagesNotFound() {
		String breed = "xxxxx";
		
		Response response = dogApi.getImagesSpecificBreeds(breed)
		.then()
		.statusCode(404)
	    .time(lessThan(5000L))
		.extract().response();
		
		response.then()
        .body("status", equalTo("error"))
        .body("message", equalTo("Breed not found (main breed does not exist)"))
        .body("code", equalTo(404));
	}

}
