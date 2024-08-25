package src.test.java.employee_api.employee_src;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.DataProvider;


import java.io.File;
import java.util.HashMap;
import java.util.List;

public class EmpDataProvider extends EmpResources {
    static JsonPath path = new JsonPath(new File(System.getProperty("user.dir") + "\\src\\test_data\\employee-data.json"));
    static JSONObject jsonObject;

    @DataProvider(name = "addEmpData")
    public static Object[] addingData() {
        List<HashMap<String, Object>> mapList = path.getList("data");
        Object[] addingData = mapList.toArray();
        return addingData;
    }

    @DataProvider(name = "updateEmpData")
    public static Object[] updatingData() {

        jsonObject = new JSONObject();
        jsonObject.accumulate("accountno", System.getProperty("emp.accountno"));
        jsonObject.accumulate("departmentno", departmentno);
        jsonObject.accumulate("id", id_2);
        jsonObject.accumulate("pincode", pincode);
        jsonObject.accumulate("salary", salary);
        jsonObject.accumulate("userid", userid_2);

        Object[] updateData = new Object[][]{{jsonObject}};
        return updateData;
    }

    @DataProvider(name = "deleteEmpData")
    public static Object[] deletingData() {
        jsonObject = new JSONObject();
        jsonObject.accumulate("id", id);
        jsonObject.accumulate("userid", userid);
        Object[] deletingData = new Object[][]{{jsonObject}};
        return deletingData;

    }

}
