package edu.praktikum.sprint4;

import edu.praktikum.sprint4.pom.MainPage;
import edu.praktikum.sprint4.pom.OrderPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class OrderTest {
    private WebDriver webDriver;
    private WebDriverWait wait;

    @Before
    public void setup() {
        // Инициализируем поле класса webDriver для Firefox
        WebDriverManager.firefoxdriver().setup();
        webDriver = new FirefoxDriver();
        // Инициализируем поле класса webDriver для Сhrome
        // webDriver = new ChromeDriver();

        webDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        webDriver.manage().window().maximize();
        wait = new WebDriverWait(webDriver, 20);
    }

    @Test
    public void testOrderProcess() throws InterruptedException {
        MainPage mainPage = new MainPage(webDriver);
        OrderPage orderPage = new OrderPage(webDriver);

        // Открываем главную страницу
        mainPage.open();
        Thread.sleep(2000);  // Задержка 2 секунды

        // Закрываем куки сообщение
        closeCookieConsent();

        // Переход на страницу заказа
        mainPage.clickOrder();
        Thread.sleep(2000);  // Задержка 2 секунды

        // Заполнение формы заказа
        orderPage.fillOrderForm("Иван", "Иванов", "Абая 16", "Черкизовская", "83332463488");
        Thread.sleep(2000);  // Задержка 2 секунды

        orderPage.clickNext();
        Thread.sleep(2000);  // Задержка 2 секунды

        // Заполнение формы доставки
        orderPage.fillDeliveryForm("12.06.2024", "сутки", true, false, "альфа 1 комментарий");
        Thread.sleep(2000);

        // Нажатие на кнопку "Заказать"
        WebElement orderButton = webDriver.findElement(By.xpath("//button[contains(@class, 'Button_Button__ra12g Button_Middle__1CSJM') and text()='Заказать']"));
        orderButton.click();
        Thread.sleep(2000);  // Задержка 2 секунды

        // Проверка видимости модального окна заказа
        WebElement orderModal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'Order_ModalHeader__3FDaJ') and contains(text(), 'Хотите оформить заказ?')]")));
        assertTrue("Order modal is not visible", orderModal.isDisplayed());

        // Нажатие на кнопку "Да" в модальном окне
        WebElement confirmButton = webDriver.findElement(By.xpath("//button[contains(@class, 'Button_Button__ra12g Button_Middle__1CSJM') and text()='Да']"));
        confirmButton.click();
        Thread.sleep(2000);  // Задержка 2 секунды

        // Проверка наличия элемента с подтверждением заказа
        WebElement orderConfirmation = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'Order_ModalHeader__3FDaJ') and contains(text(), 'Заказ оформлен')]")));
        assertTrue("Order confirmation is not visible", orderConfirmation.isDisplayed());
    }

    private void closeCookieConsent() {
        By cookieConsentButton = By.id("rcc-confirm-button");
        if (!webDriver.findElements(cookieConsentButton).isEmpty()) {
            webDriver.findElement(cookieConsentButton).click();
        }
    }

    @After
    public void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}
