package src.test.java.employee_api.employee_test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.comparesEqualTo;
import static org.hamcrest.Matchers.*;


import src.test.java.employee_api.employee_src.EmpDataProvider;
import src.test.java.employee_api.employee_src.EmpResources;

import java.util.HashMap;

public class CreateEmployeeData extends EmpResources {

    @BeforeTest
    public void gettingAuthentication(){
        setBaseUri(System.getProperty("emp.uri"));
        settingAuthorization();
    }
    @BeforeMethod
    public void accessingServer(){
        setBaseUri(System.getProperty("emp.uri"));
    }

    @Test(dataProvider = "addEmpData",dataProviderClass = EmpDataProvider.class)
    public void TC_003_addData(HashMap<String, Object> addingData){

        response = addData(System.getProperty("emp.contentType"),addingData,System.getProperty("emp.addData.endpoint"));

        // verify status code
        assertThat(response.getStatusCode(),comparesEqualTo(oCons.iHTTPCode201()));

        // verify status line
        assertThat(response.getStatusLine(), comparesEqualTo(oCons.sHTTP201StatusLine()));
    }

}
