package com.agibank.testes;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.lessThan;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

import com.agibank.client.DogApiClient;
import com.agibank.config.BaseTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ListAllBreeds extends BaseTest{

    DogApiClient dogApi = new DogApiClient();

	@Test
	public void testListAllBreeds() {
		Response response = dogApi.getListAllBreeds()
				.then()
	            .spec(responseSpec)
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
		message.values()
	       .forEach(value ->
	           assertThat(value, instanceOf(List.class))
	       );
	}
}
