package src.main.java.utilities;


import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.Validatable;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RestAssuredUtilities {

    Logger log = Logger.getLogger(getClass().getSimpleName());
    public Response getResponseGetMethod(String parameter,String contentType, String endpoint){
        Response response = RestAssured
                .given().param(parameter)
                .when().contentType(contentType).get(endpoint)
                .then().extract().response();
        return response;
    }
    public Response getResponsePostMethod(String contentType, HashMap<String, String> credentials, String endpoint){
       Response response = RestAssured
                .given()
                .when().contentType(contentType).body(credentials).post(endpoint)
                .then().extract().response();
        return response;
    }

    public Response getResponsePostMethod(Header header, String contentType, String endPoint){
        Response response = RestAssured
                .given().header(header).contentType(contentType)
                .when().get(endPoint)
                .then().extract().response();
        return response;
    }

    public Response getResponsePostMethod(Header header, String contentType,HashMap<String, Object> mapData, String endPoint){
    Response response = RestAssured
            .given().header(header).contentType(contentType).body(mapData)
            .when().post(endPoint)
            .then().extract().response();
    return response;
    }

    public Response getResponsePutMethod(Header header, String contentType,JSONObject jsonObject, String endPoint){
        Response response = RestAssured
                .given().header(header).contentType(contentType).body(jsonObject.toString())
                .when().put(endPoint)
                .then().extract().response();
        return response;
    }

    public Response getResponseDeleteMethod(Header header, String contentType,JSONObject jsonObject, String endPoint){
        Response response = RestAssured
                .given().header(header).contentType(contentType).body(jsonObject.toString())
                .when().delete(endPoint)
                .then().extract().response();
        return response;
    }
}

