package com.jayway.Jayway;

import org.junit.Test;

import io.restassured.RestAssured;
import static org.hamcrest.Matchers.equalTo;

/**
 * Unit test for simple App.
 */
public class AppTest {
	@Test
	public void readJsonValid() {
		
		RestAssured
			.given()
				.baseUri("https://jsonplaceholder.typicode.com")
			.when()
				.get("/todos/1")
			.then()
				.assertThat()
				.statusCode(200)
				.and()
				.body("userId", equalTo(1))
				.and()
				.body("id", equalTo(1))
				.and()
				.body("title", equalTo("delectus aut autem"))
				.and()
				.body("completed", equalTo(false));
	}
	
	@Test
	public void readJsonInvalid() {
		
		RestAssured
			.given()
				.baseUri("https://jsonplaceholder.typicode.com")
			.when()
				.get("/todos/2")
			.then()
				.assertThat()
				.statusCode(200)
				.and()
				.body("userId", equalTo(1))
				.and()
				.body("id", equalTo(1))
				.and()
				.body("title", equalTo("delectus aut autem"))
				.and()
				.body("completed", equalTo(false));
	}
}
