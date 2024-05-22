package edu.praktikum.sprint4;

import edu.praktikum.sprint4.pom.Accordion;
import edu.praktikum.sprint4.pom.MainPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class ScrollAndCheckDropdownElements {
    private WebDriver webDriver;

    @Before
    public void setup() {
        // Инициализация драйвера Chrome перед каждым тестом
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @Test
    public void scrollAndCheckDropdownElements() {
        MainPage mainPage = new MainPage(webDriver);
        Accordion accordion = new Accordion(webDriver);

        // Открытие главной страницы
        mainPage.open();

        JavascriptExecutor js = (JavascriptExecutor) webDriver;

        // Проход по всем элементам массива с заголовками
        for (int i = 0; i < accordion.getAccordionSize(); i++) {
            // Получаем текущий элемент заголовка
            WebElement headingElement = webDriver.findElement(accordion.getHeadings()[i]);

            // Прокручиваем страницу до элемента заголовка
            js.executeScript("arguments[0].scrollIntoView(true);", headingElement);

            // Ожидаем, пока элемент станет кликабельным,после чего кликаем по нему
            new WebDriverWait(webDriver, 5).until(ExpectedConditions.elementToBeClickable(headingElement));
            headingElement.click();

            // Проверяем текст выпадающего элемента
            String actualText = accordion.getPanelText(i);
            String expectedText = accordion.getExpectedText(i);
            assertEquals("Текст выпадающего элемента не соответствует ожидаемому", expectedText, actualText);
        }
    }

    @After
    public void tearDown() {
        // Закрытие браузера после выполнения тестов
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}
