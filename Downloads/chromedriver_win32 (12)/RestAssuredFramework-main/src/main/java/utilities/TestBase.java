package src.main.java.utilities;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.path.json.JsonPath;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import src.main.java.listeners.ExtentManager;
import src.main.java.listeners.TestListener;

import java.util.HashMap;

@Listeners(TestListener.class)
public class TestBase {
    Logger log = Logger.getLogger(getClass().getSimpleName());
    public  static CommonUtilities  oCommUtil = new CommonUtilities();
    public static RestAssuredUtilities oResUtil = new RestAssuredUtilities();
    public static Constants oCons = new Constants();
    public static ExtentManager oExt = new ExtentManager();
    public static ObjectMapper objectMapper;

    public static HashMap<String, String > sMapAuthorizationHeader = new HashMap<>();
    public static String sHost;
    public static String sErrorMsg = " ";

    @BeforeSuite
    public void TriggerDependencies() throws Exception{
        oCommUtil.loadPropertyFiles(System.getProperty("user.dir")+ "\\src\\properties\\employee.properties");
        oCommUtil.loadPropertyFiles(System.getProperty("user.dir")+"\\src\\properties\\responsestatus.properties");
        oCommUtil.loadLog4jProperty(System.getProperty("user.dir")+ "\\src\\properties\\log4j.properties");
    }
    @AfterSuite
    public void shuttingDownAllDependencies(){
    }
}
