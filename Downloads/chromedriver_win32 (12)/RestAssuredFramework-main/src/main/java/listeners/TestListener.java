package src.main.java.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    Logger log = Logger.getLogger(getClass().getSimpleName());
    //Extent Report Declarations
    private static ExtentReports extent = ExtentManager.createInstance();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public synchronized void onStart(ITestContext context) {
        log.info(" Test suite is starting.");

    }

    @Override
    public synchronized void onFinish(ITestContext context) {
        log.info("Test Suite is ending");
        extent.flush();
    }

    @Override
    public synchronized void onTestStart(ITestResult result) {
        log.info(result.getMethod().getMethodName() + " started.");
        ExtentManager.extLogger = extent.createTest(result.getMethod().getMethodName(), result.getMethod().getDescription());
        test.set(ExtentManager.extLogger);
    }

    @Override
    public synchronized void onTestSuccess(ITestResult result) {
        log.info(result.getMethod().getMethodName() + " passed");
        test.get().pass("Test passed");

    }

    @Override
    public synchronized void onTestFailure(ITestResult result) {
        log.info(result.getMethod().getMethodName() + " failed.");
        test.get().fail(result.getThrowable());
    }

    @Override
    public synchronized void onTestSkipped(ITestResult result) {
        log.info(result.getMethod().getMethodName() + " skipped");
        test.get().skip(result.getThrowable());

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        log.info("onTestFailedButWithinSuccessPercentage for " + result.getMethod().getMethodName());

    }
}
