package src.test.java.booking_api.booking_src;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class StandAloneTest {
    public static void main(String[] args) {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        Response response = RestAssured
                .given().param("id")
                .when().contentType("application/json").get("/booking")
                .then().extract().response();
        System.out.println(response.asString());
    }
}
