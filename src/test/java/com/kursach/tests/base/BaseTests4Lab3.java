package com.kursach.tests.base;

import com.kursach.managers.DriverManager;
import com.kursach.managers.InitManager;
import com.kursach.managers.PageManager;
import com.kursach.managers.TestPropManager;
import com.kursach.utils.PropsConst;
import org.junit.jupiter.api.BeforeEach;

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

//    @AfterEach
//    public void tearDown() {
//        InitManager.quitFramework();
//    }
}
