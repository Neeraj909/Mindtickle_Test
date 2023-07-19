package com.test.swagger.apitestcases;
import com.test.swagger.reports.ExtentReport;
import com.test.swagger.reports.LogStatus;
import com.test.swagger.utils.Constants;
import io.restassured.response.Response;
import org.apache.commons.io.output.WriterOutputStream;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringWriter;

public class BaseTest {

    public static Response response;

    protected static StringWriter writer;
    protected static PrintStream captor;
    @BeforeSuite
    public void setUpSuite() {
        ExtentReport.initialize();
    }
    @BeforeMethod
    public void loadResponseIntoExtentReport(){
        writer = new StringWriter();
        captor = new PrintStream(new WriterOutputStream(writer), true);
    }
    public static void
    updateResponseAndRequestIntoReport(String request, String response) {
        LogStatus.info("---- Request ---");
        formatAPIAndLogInReport(request);
        LogStatus.info("---- Response ---");
        formatAPIAndLogInReport(response);
    }
    @AfterMethod
    public static long responseTime(){
       System.out.println("Response Time "+response.time());
       return response.time();
    }

    protected static void formatAPIAndLogInReport(String content) {

        String prettyPrint = content.replace("\n", "<br>");
        LogStatus.info("<pre>" + prettyPrint + "</pre>");

    }
    @AfterSuite
    public void afterSuite() throws IOException {
        ExtentReport.report.flush();
        File htmlFile = new File(Constants.EXTENTREPORTPATH);
        Desktop.getDesktop().browse(htmlFile.toURI());

    }


}
