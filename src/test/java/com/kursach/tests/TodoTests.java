package com.kursach.tests;

import com.kursach.tests.base.BaseTests4Lab1;
import org.junit.jupiter.api.Test;

public class TodoTests extends BaseTests4Lab1 {

    @Test
    public void tests() {
        pageManager.getMainPage()
        .checkHeader()
        .checkRemainingText("5 of 5 remaining")
        .checkFirstItemNotCompleted()
        .clickFirstItemCheckbox()
        .checkRemainingText("4 of 5 remaining")
        .clickItemCheckbox()
        .checkRemainingText("0 of 5 remaining")
        .addNewItem("salom")
        .checkRemainingText("1 of 6 remaining")
        .clickLastItem();
    }
}
