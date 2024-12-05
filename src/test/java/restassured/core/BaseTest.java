package restassured.core;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class BaseTest {
	

	    protected static ExtentReports extent;
	    protected static ExtentTest test;

	    @BeforeAll
	    public static void setUpReport() {
	        extent = ExtentManager.getExtentReports();
	    }

	    @AfterAll
	    public static void tearDownReport() {
	        if (extent != null) {
	            extent.flush();
	        }
	    }
	}