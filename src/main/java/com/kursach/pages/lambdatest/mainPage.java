package com.kursach.pages.lambdatest;

import com.kursach.pages.BasePage;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class mainPage extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger(mainPage.class);

    @FindBy(tagName = "h2")
    private WebElement header;

    @FindBy(className = "ng-binding")
    private WebElement remainingText;

    @FindBy(xpath = "//li[1]/input")
    private WebElement firstItemCheckbox;

    @FindBy(xpath = "//li/input")
    private List<WebElement> itemCheckboxes;

    @FindBy(xpath = "//ul[@class='list-unstyled']/li/span")
    private List<WebElement> todoItems;

    @FindBy(id = "sampletodotext")
    private WebElement inputField;

    @FindBy(id = "addbutton")
    private WebElement addButton;

    @FindBy(xpath = "//li[last()]/input")
    private WebElement lastItemCheckbox;


    @Step("Проверяем, что текст заголовка равен LambdaTest Sample App")
    public mainPage checkHeader() {
        assertEquals("LambdaTest Sample App", header.getText());

        logger.info("Проверяем, что текст заголовка равен LambdaTest Sample App");


        return pageManager.getMainPage();

    }

    @Step("Проверяем, что remainingText равен ожидаемому тексту")
    public mainPage checkRemainingText(String expectedText) {
        assertEquals(expectedText, remainingText.getText());

        logger.info("Проверяем, что remainingText равен ожидаемому тексту");

        return pageManager.getMainPage();
    }

    @Step("Шаг для проверки, что первый элемент не завершен")
    public mainPage checkFirstItemNotCompleted() {
        try {
                Thread.sleep(1000);
        } catch (InterruptedException e) {
                e.printStackTrace();
        }
        String classAttribute = firstItemCheckbox.getAttribute("class");
        assertTrue(classAttribute.contains("ng-pristine"));
        assertTrue(classAttribute.contains("ng-untouched"));
        Assertions.assertTrue(classAttribute.contains("ng-valid"));

        logger.info("Шаг для проверки, что первый элемент не завершен");

        return pageManager.getMainPage();
    }

    @Step("Проверка, что элемент списка не зачеркнут")
    public mainPage clickFirstItemCheckbox(String nameOfItem) {
        for (WebElement item : todoItems) {
            if (item.getText().trim().equals(nameOfItem)) {
                Assertions.assertTrue(item.getAttribute("class").contains("done-false"));
                return this;
            }
        }
        logger.info("Проверка, что элемент списка не зачеркнут");
        Assertions.fail("Элемент '" + nameOfItem + "' не присутствует на странице");
        return this;
    }

    @Step("Шаг для клика по чекбоксам элементов")
    public mainPage clickItemCheckbox() {
        for (int i = 0; i < 5; i++) {
            itemCheckboxes.get(i).click();
        }
        logger.info("Шаг для клика по чекбоксам элементов");
        return pageManager.getMainPage();
    }

    @Step("Шаг для добавления нового элемента")
    public mainPage addNewItem(String item) {
        inputField.sendKeys(item);
        addButton.click();

        logger.info("Шаг для добавления нового элемента");
        return pageManager.getMainPage();
    }

    @Step("Шаг для клика по последнему элементу")
    public void clickLastItem() {
        lastItemCheckbox.click();

        Assertions.assertTrue(lastItemCheckbox.isSelected());
        logger.info("Шаг для клика по последнему элементу");
        pageManager.getMainPage();
    }



}
