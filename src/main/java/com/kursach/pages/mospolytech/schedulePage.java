package com.kursach.pages.mospolytech;

import com.kursach.pages.BasePage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;


public class schedulePage extends BasePage {

    @FindBy(xpath = "//input[@class=\"groups\"and@placeholder=\"группа ...\"]")
    private WebElement inputGroup;
    @FindBy(xpath = "//div[@class=\"found-groups row not-print\"]/div[1]")
    private WebElement resultGroup;
    @FindBy(xpath = "//div[@class=\"found-groups row not-print\"]")
    private List<WebElement> resultGroups;
    @FindBy(xpath = "//div[@class='schedule-day schedule-day_today']")
    private WebElement todayScheduleDay;
    @FindBy(xpath = "//div[@class='schedule-day schedule-day_today']/div[1]")
    private WebElement todayScheduleDayTitle;


    public schedulePage clearfield(String groupNumber) {
        waitUtilElementToBeVisible(inputGroup);
        waitUtilElementToBeClickable(inputGroup);

        inputGroup.clear();
        inputGroup.sendKeys(groupNumber);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class=\"found-groups row not-print\"]")));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(1, resultGroups.size(), "Expected exactly one search result for group number: " + groupNumber + " but found " + resultGroups.size());

        WebElement searchResult = resultGroup;
        Assertions.assertTrue(searchResult.getAttribute("id").contains(groupNumber), "Search result does not contain the group number: " + groupNumber);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return pageManager.getSchedulepage();
    }

    public schedulePage checkClickSearchForGroup() {
        waitUtilElementToBeClickable(resultGroup);

        scrollWithOffset(resultGroup, 0, 100);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        resultGroup.click();

        waitUtilElementToBeVisible(todayScheduleDay);

        String currentDay = getCurrentDayOfWeek();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assertions.assertTrue(todayScheduleDay.getAttribute("class").contains("schedule-day_today"), "Current day is not highlighted on the schedule page");
        Assertions.assertTrue(todayScheduleDayTitle.getText().contains(currentDay), "Highlighted day does not match the current day of the week");

        return pageManager.getSchedulepage();
    }

    public String getCurrentDayOfWeek() {

        LocalDate currentDate = LocalDate.now();

        DayOfWeek dayOfWeek = currentDate.getDayOfWeek();

        switch (dayOfWeek) {
            case MONDAY:
                return "Понедельник";
            case TUESDAY:
                return "Вторник";
            case WEDNESDAY:
                return "Среда";
            case THURSDAY:
                return "Четверг";
            case FRIDAY:
                return "Пятница";
            case SATURDAY:
                return "Суббота";
            case SUNDAY:
                return "Воскресенье";
            default:
                return "";
        }
    }

}
