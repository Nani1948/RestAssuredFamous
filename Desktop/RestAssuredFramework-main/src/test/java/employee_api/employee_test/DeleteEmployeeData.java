package src.test.java.employee_api.employee_test;

import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import src.test.java.employee_api.employee_src.EmpDataProvider;
import src.test.java.employee_api.employee_src.EmpResources;

import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.comparesEqualTo;

public class DeleteEmployeeData extends EmpResources {

    @BeforeTest
    public void gettingAuthentication(){
        setBaseUri(System.getProperty("emp.uri"));
        settingAuthorization();
    }

    @BeforeMethod
    public void accessingServer(){
        setBaseUri(System.getProperty("emp.uri"));
    }

    @Test(dataProvider = "deleteEmpData", dataProviderClass = EmpDataProvider.class)
    public void TC_005_deleteData(JSONObject deletingData){

        response = deleteData(System.getProperty("emp.contentType"),deletingData,System.getProperty("emp.deleteData.endpoint"));

        // verify status code
        assertThat(response.getStatusCode(),comparesEqualTo(oCons.iHTTPCode200()));

        // verify status description line
        assertThat(response.getStatusLine(),comparesEqualTo(oCons.sHTTP200StatusLine()));
    }
}

