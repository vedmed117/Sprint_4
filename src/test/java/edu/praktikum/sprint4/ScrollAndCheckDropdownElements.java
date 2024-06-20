package edu.praktikum.sprint4;

import edu.praktikum.sprint4.pom.Accordion;
import edu.praktikum.sprint4.pom.MainPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ScrollAndCheckDropdownElements {
    private WebDriver webDriver;
    private int index;
    private String expectedText;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {0, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {1, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {2, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {3, "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {4, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {5, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {6, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {7, "Да, обязательно. Всем самокатов! И Москве, и Московской области."}
        });
    }

    public ScrollAndCheckDropdownElements(int index, String expectedText) {
        this.index = index;
        this.expectedText = expectedText;
    }

    @Before
    public void setup() {
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @Test
    public void scrollAndCheckDropdownElements() {
        MainPage mainPage = new MainPage(webDriver);
        Accordion accordion = new Accordion(webDriver);

        mainPage.open();
        JavascriptExecutor js = (JavascriptExecutor) webDriver;

        // Прокрутка к элементу
        WebElement headingElement = webDriver.findElement(accordion.getHeadings()[index]);
        js.executeScript("arguments[0].scrollIntoView(true);", headingElement);

        // Ожидание, пока элемент станет кликабельным, клик по нему
        new WebDriverWait(webDriver, 5).until(ExpectedConditions.elementToBeClickable(headingElement));
        headingElement.click();

        // Проверка текста панели
        String actualText = accordion.getPanelText(index);
        assertEquals("Текст выпадающего элемента не соответствует ожидаемому", expectedText, actualText);
    }

    @After
    public void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}
