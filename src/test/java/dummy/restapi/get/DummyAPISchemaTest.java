package dummy.restapi.get;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class DummyAPISchemaTest {

	@Test
	public void DummyAPISchemaValidation() {
		
		RestAssured.baseURI = "http://dummy.restapiexample.com";
	
		given().log().all()
			.contentType(ContentType.JSON)
		.when().log().all()
			.get("/api/v1/employees")
		.then().log().all()
			.assertThat()
				.statusCode(200)
				.and()
				.body(matchesJsonSchemaInClasspath("EmployeesSchema.json"));

	}

}
