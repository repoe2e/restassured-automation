package restassured.core;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

	  private static ExtentReports extent;

	    public static ExtentReports getExtentReports() {
	        if (extent == null) {
	            ExtentSparkReporter sparkReporter = new ExtentSparkReporter("target/ExtentReport.html");
	            sparkReporter.config().setDocumentTitle("API Test Report");
	            sparkReporter.config().setReportName("RestAssured API Test Execution");
	            sparkReporter.config().setTheme(com.aventstack.extentreports.reporter.configuration.Theme.STANDARD);

	            extent = new ExtentReports();
	            extent.attachReporter(sparkReporter);
	            extent.setSystemInfo("Environment", "Localhost");
	            extent.setSystemInfo("Tester", "Your Name");
	        }
	        return extent;
	    }
	}