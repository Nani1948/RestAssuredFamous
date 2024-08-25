package src.main.java.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.Platform;

import java.io.File;

public class ExtentManager {
    private static ExtentReports extent;
    public static ExtentTest extLogger;

    private static Platform platform;
    private static String reportFileName = "my_report_Report.html";
    private static String macPath = System.getProperty("user.dir") + "\\test_reports";
    private static String windowPath = System.getProperty("user.dir") + "\\test_reports";
    private static String linuxPath = System.getProperty("user.dir") + "\\test_reports";
    private static String macReportFileLoc = macPath + "/" + reportFileName;
    private static String winReportFieLoc = windowPath + "\\" + reportFileName;
    private static String linuxReportFileLoc = linuxPath + "\\" + reportFileName;


    public static ExtentReports getInstance() {
        if (extent == null) {
            createInstance();
        }
        return extent;
    }

    //Create an extent report instant
    public static ExtentReports createInstance() {
        platform = getCurrentPlatform();
        String fileName = getReportFileLocation(platform);
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
        htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setDocumentTitle(fileName);
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName(fileName);

//        htmlReporter.setAppendExisting(true);
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        return extent;
    }
    // Select teh extent report file location based on platform
    private static String getReportFileLocation(Platform platform) {
        String reportFileLocation = null;
        switch (platform) {
            case MAC:
                reportFileLocation = macReportFileLoc;
                createReportPath(macPath);
                System.out.println("ExtentReport Path for Mac: " + macPath + "\n");
                break;
            case WINDOWS:
                reportFileLocation = winReportFieLoc;
                createReportPath(windowPath);
                System.out.println("ExtentReport Path for Windows " + windowPath + "\n");
            case LINUX:
                reportFileLocation = linuxReportFileLoc;
                createReportPath(linuxPath);
                System.out.println("ExtentReport Path for Linux: " + linuxPath + "\n");
                break;
            default:
                System.out.println("ExtentReport path has not been set! There is a problem!\n");
        }
        return reportFileLocation;

    }

    private static void createReportPath(String path) {
        File testDirectory = new File(path);
        if (!testDirectory.exists()) {
            if (testDirectory.mkdir()) {
                System.out.print("Directory: " + path + " is created");
            } else {
                System.out.print("Failed to create: " + path);
            }

        } else {
            System.out.print("Directory already exits: " + path);
        }

    }

    private static Platform getCurrentPlatform() {
        if (platform == null) {
            String operSys = System.getProperty("os.name").toLowerCase();
            System.out.println(operSys);
            if (operSys.contains("win")) {
                platform = Platform.WINDOWS;
            } else if (operSys.contains("nix") || operSys.contains("nux") || operSys.contains("aix")) {
                platform = Platform.LINUX;
            } else if (operSys.contains("mac")) {
                platform = Platform.MAC;
            }
        }
        return platform;
    }
}
