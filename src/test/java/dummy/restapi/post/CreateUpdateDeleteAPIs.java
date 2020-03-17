package dummy.restapi.post;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import pojo.Employee;
import utils.TestUtil;

public class CreateUpdateDeleteAPIs extends TestUtil {
	int EmpId;
	Employee emp;
	
	@Test(priority=1)
	public void CreateNewEmployee() {
		
		emp = new Employee ("Peter", 123, 45);
		
		String jsonPayload = getSerializedJason(emp);
		
		RestAssured.baseURI = "http://dummy.restapiexample.com";
				
		EmpId = given().log().all()
			.contentType(ContentType.JSON)
				.body(jsonPayload)
		.when().log().all()
		.post("/api/v1/create")
		.then().log().all()
			.assertThat()
			.contentType(ContentType.JSON)
			.statusCode(200)
			.body("status", equalTo("success"))
			.body("data.name", equalTo(emp.getName()))
			.body("data.salary", equalTo(emp.getSalary()))
			.body("data.age", equalTo(emp.getAge()))
			.extract().path("data.id");;
	
			System.out.println(EmpId);
			
	}

	@Test (priority=2)

	public void ValidateNewlyCreatedEmployee() {

		RestAssured.baseURI = "http://dummy.restapiexample.com";
		RestAssured.basePath = "/api/v1/employee";
		
		given().log().all()
			.contentType(ContentType.JSON)
		.when().log().all()
		.get("/"+EmpId)
		.then().log().all()
			.assertThat()
			.statusCode(200)
			.body("status", equalTo("success"))
			.body("data.name", equalTo(emp.getName()))
			.body("data.salary", equalTo(emp.getSalary()))
			.body("data.age", equalTo(emp.getAge()))
			.body("data.id", equalTo(EmpId));
	
			
	}
	
	@Test (priority=3)

	public void UpdateNewlyCreatedEmployee() {
		
		emp = new Employee ("Peter", 1234, 55);
		
		String jsonPayload = getSerializedJason(emp);
		

		RestAssured.baseURI = "http://dummy.restapiexample.com";
		RestAssured.basePath = "/api/v1/update";
		
		given().log().all()
			.contentType(ContentType.JSON)
				.body(jsonPayload)
		.when().log().all()
		.put("/"+EmpId)
		.then().log().all()
			.assertThat()
			.statusCode(200)
			.body("status", equalTo("success"))
			.body("data.name", equalTo(emp.getName()))
			.body("data.salary", equalTo(emp.getSalary()))
			.body("data.age", equalTo(emp.getAge()))
			.body("data.id", equalTo(EmpId));
	
			
	}
	
	@Test (priority=4)

	public void DeleteNewlyCreatedEmployee() {
		
		RestAssured.baseURI = "http://dummy.restapiexample.com";
		RestAssured.basePath = "/api/v1/delete";
		
		given().log().all()
			.contentType(ContentType.JSON)
		.when().log().all()
		.delete("/"+EmpId)
		.then().log().all()
			.assertThat()
			.statusCode(200)
			.body("status", equalTo("success"))
			.body("message", equalTo("successfully! deleted Records"));
	
	}
	
}
