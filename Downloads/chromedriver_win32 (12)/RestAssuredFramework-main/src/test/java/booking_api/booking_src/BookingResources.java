package src.test.java.booking_api.booking_src;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import src.main.java.utilities.TestBase;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class BookingResources extends TestBase {
    public static String token, id;
    public static Header header;
    public static Response response;

    public void setBaseUri(String uri){
        RestAssured.baseURI = uri;
    }
    public void getAuthorization(){
        response =  oResUtil.
                getResponsePostMethod(System.getProperty("bk.content.type"),authCredential(),System.getProperty("bk.auth.endpoint"));
        HashMap<String ,Object> map = response.jsonPath().get(System.getProperty("bk.token.index"));
        token = map.get("token").toString();
        header = new Header("token",token);
    }

    public HashMap<String,String> authCredential(){
        HashMap<String ,String > credentialMap = new HashMap<>();
        credentialMap.put("username",System.getProperty("booking.username"));
        credentialMap.put("password",System.getProperty("booking.password"));
        return credentialMap;
    }
//    public Response getIds(){
//        response = oResUtil
//                .getResponseGetMethod("id",System.getProperty("bk.content.type"),System.getProperty("bk.getId.endpoint"));
//    }
}
