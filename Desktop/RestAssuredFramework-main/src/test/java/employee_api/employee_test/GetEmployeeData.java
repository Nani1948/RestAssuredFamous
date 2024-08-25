package src.test.java.employee_api.employee_test;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import src.test.java.employee_api.employee_src.EmpResources;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.comparesEqualTo;

public class GetEmployeeData extends EmpResources {

    @BeforeTest
    public void gettingAuthentication(){
        setBaseUri(System.getProperty("emp.uri"));
        settingAuthorization();
    }
    @BeforeMethod
    public void accessingServer(){
        setBaseUri(System.getProperty("emp.uri"));
    }

    @Test
    public void TC_001_getData(){
        // get response
        response = getData(System.getProperty("emp.contentType"), System.getProperty("emp.getData.endpoint"));

        // verify response code
        assertThat(response.getStatusCode(),comparesEqualTo(oCons.iHTTPCode200()));

        // verify return status message
        assertThat(response.getStatusLine(), comparesEqualTo(oCons.sHTTP200StatusLine()));

           // verify content
//        assertThat(response.getBody().asString(), hasProperty(System.getProperty("emp.id")));

    }
}