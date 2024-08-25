package src.test.java.employee_api.employee_test;

import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.comparesEqualTo;

import src.test.java.employee_api.employee_src.EmpDataProvider;
import src.test.java.employee_api.employee_src.EmpResources;



public class UpdateEmployeeData extends EmpResources {

    @BeforeTest
    public void gettingAuthentication(){
        setBaseUri(System.getProperty("emp.uri"));
        settingAuthorization();
    }

    @BeforeMethod
    public void accessingServer(){
        setBaseUri(System.getProperty("emp.uri"));
    }

    @Test(dataProvider = "updateEmpData", dataProviderClass = EmpDataProvider.class)
    public void TC_004_updateData(JSONObject updatingData){

        response = updateData(System.getProperty("emp.contentType"),updatingData,System.getProperty("emp.updateData.endpoint"));

        // verify status code
        assertThat(response.getStatusCode(),comparesEqualTo(oCons.iHTTPCode200()));

        // verify status description line
        assertThat(response.getStatusLine(),comparesEqualTo(oCons.sHTTP200StatusLine()));
    }
}
