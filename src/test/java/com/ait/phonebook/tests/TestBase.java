package com.ait.phonebook.tests;

import com.ait.phonebook.fw.ApplicationManager;
import org.openqa.selenium.remote.Browser;
import org.testng.ITestResult;
import org.testng.annotations.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.Arrays;

public class TestBase {
    protected static ApplicationManager app = new ApplicationManager(System.getProperty("browser",
            Browser.CHROME.browserName()));
//            Browser.EDGE.browserName()));
//    Browser.FIREFOX.browserName()));
//    Browser.SAFARI.browserName()));

    Logger logger = LoggerFactory.getLogger(TestBase.class);

    @BeforeMethod
    public void startTest(Method method, Object[] p) {
        logger.info("Start test " + method.getName() + " with data: " + Arrays.asList(p));
    }

    @BeforeSuite
    public void setUp() {
        app.init();
        System.out.println("Before Suite!");
    }

    @BeforeTest
    public void beforeTest() {
        System.out.println("*****Before Test!");
    }

    @AfterMethod
    public void stopTest(ITestResult result) {
        if (result.isSuccess()) {
            logger.info("PASSED: " + result.getMethod().getMethodName());
        } else {
            logger.error("FAILED: " + result.getMethod().getMethodName() + app.getContact().takeScreenshot());
        }
        logger.info("============================");
    }

    @AfterTest
    public void afterTest() {
        System.out.println("*****After Test!");
    }


    // @AfterMethod(enabled = true)
    @AfterSuite
    public void tearDown() {
        app.stop();
        System.out.println("After Suite!");
    }

}
