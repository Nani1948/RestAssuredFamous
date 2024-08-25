package src.test.java.employee_api.employee_src;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import src.main.java.utilities.TestBase;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EmpResources extends TestBase {

    Logger log = Logger.getLogger(getClass().getSimpleName());
    static JsonPath path = new JsonPath(new File(System.getProperty("user.dir") + "\\src\\test_data\\employee-data.json"));
    public static String token, id, userid;
    public static Response response;
    public static Header header;
    public static String id_2, userid_2, accountno, departmentno, pincode, salary;

    public void setBaseUri(String uri) {
        RestAssured.baseURI = uri;

    }

    public void settingAuthorization() {

        response = oResUtil
                .getResponsePostMethod(System.getProperty("emp.contentType"), loginCredential(), System.getProperty("emp.login.endPoint"));
        HashMap<String, Object> maps = response.jsonPath().get(System.getProperty("emp.response.index"));
        token = maps.get(System.getProperty("emp.token")).toString();
        userid = maps.get(System.getProperty("emp.userid")).toString();
        header = new Header("token", token);
    }

    public HashMap<String, String> loginCredential() {
        HashMap<String, String> credential = new HashMap<>();
        credential.put("username", System.getProperty("emp.username"));
        credential.put("password", System.getProperty("emp.password"));
        return credential;
    }

    public Response getData(String contentType, String endPoint) {
        response = oResUtil.getResponsePostMethod(header, contentType, endPoint);
        return response;
    }

    public Response addData(String contentType, HashMap<String, Object> addingData, String endPoint) {
        response = oResUtil.getResponsePostMethod(header, contentType, addingData, endPoint);
        return response;
    }

    public Response updateData(String contentType, JSONObject jsonObject, String endpoint) {
        getTableInfo();
        response = oResUtil.getResponsePutMethod(header, contentType, jsonObject, endpoint);
        return response;
    }
    public Response deleteData(String contentType, JSONObject jsonObject, String endPoint){
        getTableInfo();
        response = oResUtil.getResponseDeleteMethod(header,contentType,jsonObject, endPoint);
        return response;
    }


    public void getTableInfo() {

        response = getData(System.getProperty("emp.contentType"), System.getProperty("emp.getData.endpoint"));
        HashMap<String, Object> firstData = response.jsonPath().get(System.getProperty("emp.first.row.table.data"));
        if (firstData.get("userid").toString().equals(userid)) {
            id = firstData.get("id").toString();
        } else {
            log.info("user not authorized");
        }
        HashMap<System, Object> secondData = response.jsonPath().get(System.getProperty("emp.second.row.table.data"));
        id_2 = secondData.get("id").toString();
        userid_2 = secondData.get("userid").toString();
        accountno = secondData.get("accountno").toString();
        departmentno = secondData.get("departmentno").toString();
        pincode = secondData.get("pincode").toString();
        salary = secondData.get("salary").toString();
    }
}