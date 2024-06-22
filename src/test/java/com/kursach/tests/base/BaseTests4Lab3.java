package com.kursach.tests.base;

import com.kursach.managers.DriverManager;
import com.kursach.managers.InitManager;
import com.kursach.managers.PageManager;
import com.kursach.managers.TestPropManager;
import com.kursach.utils.PropsConst;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseTests4Lab3 {

    private final DriverManager driverManager = DriverManager.getInstance();
    protected PageManager pageManager = PageManager.getInstance();
    private final TestPropManager testPropManager = TestPropManager.getInstance();

    @BeforeEach
    public void beforeClass(){
        InitManager.initFramework();
    }

    @BeforeEach
    public void setUp() {
        DriverManager.getInstance().getDriver();
        driverManager.getDriver().get(testPropManager.getProperty(PropsConst.YANDEX_URL));
    }

//
//  @AfterEach
//    public void takeScreenshot(TestInfo info) throws IOException {
//      InitManager.quitFramework();
//    }
}
