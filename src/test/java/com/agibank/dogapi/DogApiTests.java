package com.agibank.dogapi;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.startsWith;
import static org.hamcrest.Matchers.lessThan;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class DogApiTests {

	@BeforeAll
	public static void setup() {
		RestAssured.baseURI = "https://dog.ceo/api";
	}

	@Test
	public void testRandomImage() {
		given()
		.when().get("/breeds/image/random")
		.then().statusCode(200)
	    .time(lessThan(3000L))
		.body("status", equalTo("success"))
		.body("message", containsString("https://images.dog.ceo/breeds/"))
		.body("message", containsString(".jpg"));
	}
	
	@Test
	public void testListAllBreeds() {
		Response response = RestAssured
				.given()
				.when().get("/breeds/list/all")
				.then()
				.statusCode(200)
			    .time(lessThan(3000L))
				.extract().response();

		String status = response.jsonPath().getString("status");
		assertThat(status, equalTo("success"));

		// Pegar o mapa de mensagens (raças e sub-raças)
		Map<String, Object> message = response.jsonPath().getMap("message");

		// Validar que existem chaves esperadas
		assertThat(message, hasKey("affenpinscher"));
		assertThat(message, hasKey("bulldog"));
		assertThat(message, hasKey("labrador"));

		// Validar que os valores são listas
		for (Map.Entry<String, Object> entry : message.entrySet()) {
			assertThat(entry.getValue(), instanceOf(java.util.List.class));
		}
	}

	@Test
	public void testBreedImages() {
		String breed = "hound";
		
		Response response = RestAssured
		.given()
		.when().get("/breed/" + breed + "/images")
		.then()
		.statusCode(200)
	    .time(lessThan(3000L))
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
		
		Response response = RestAssured
		.given()
		.when().get("/breed/" + breed + "/images")
		.then()
		.statusCode(404)
	    .time(lessThan(3000L))
		.extract().response();
		
		response.then()
        .body("status", equalTo("error"))
        .body("message", equalTo("Breed not found (main breed does not exist)"))
        .body("code", equalTo(404));
	}
}
