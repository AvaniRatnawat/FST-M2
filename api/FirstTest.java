package examples;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import static org.testng.AssertJUnit.assertEquals;

public class FirstTest {

    //Base URI
    String baseURI = "https://petstore.swagger.io/v2/pet";

    @Test
    public void getRequestTest(){

        //Generate Response
        Response response =
                given().header("content-type", "application/json").queryParam("status","sold")
                        .when().get(baseURI+"/findByStatus");

        //Print the response body
        System.out.println(response.getBody().asString());
        System.out.println(response.getBody().asPrettyString());

        //Print the response header
        System.out.println(response.getHeaders().asList());

        //Extract properties from the response
        String petStatus = response.then().extract().path("[0].status");
        System.out.println("petStatus: "+petStatus);

        //Assertions
        assertEquals(petStatus, "sold");
        response.then().body("[1].status", equalTo("sold"));
    }

    @Test
    public void getRequestWithPathParameterTest(){
        //Generate Response and assert
        given().header("content-type","application/json").pathParams("petId",77232)
                .when().get(baseURI+"/{petId}").then()
                .statusCode(200).body("status",equalTo("sold")).time(lessThan(2000L));
    }
}
