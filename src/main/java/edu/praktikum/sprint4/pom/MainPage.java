package edu.praktikum.sprint4.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
    private static final String URL = "https://qa-scooter.praktikum-services.ru/";

    private final WebDriver webDriver;

    // Локатор для кнопки проверки заказа
    private final By checkOrderButton = By.xpath(".//button[@class='Header_Link__1TAG7']");
    // Локатор для кнопки заказа
    private final By orderButton = By.xpath(".//button[@class='Button_Button__ra12g']");
    // Локатор для поля ввода номера заказа
    private final By inputOrderNumber = By.xpath(".//input[contains(@class, 'Header_Input__xIoUq')]");
    // Локатор для кнопки "Go"
    private final By goButton = By.xpath(".//button[@class='Button_Button__ra12g Header_Button__28dPO']");

    private NotFound notFound;

    // Локатор для кнопки cookie
    private final By cookieConsentButton = By.id("rcc-confirm-button");

    public MainPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        notFound = new NotFound(webDriver);
    }

    public void open() {
        webDriver.get(URL);
    }

    // Метод для клика по кнопке проверки заказа
    public void clickCheckOrder() {
        webDriver.findElement(checkOrderButton).click();
    }

    // Метод для клика по кнопке заказа
    public void clickOrder() {
        webDriver.findElement(orderButton).click();
    }

    // Метод для ввода номера заказа в поле
    public void inputOrderNumberInField(String text) {
        webDriver.findElement(inputOrderNumber).sendKeys(text);
    }

    // Метод для клика по кнопке "Go"
    public void clickGo() {
        webDriver.findElement(goButton).click();
    }

    public NotFound getNotFound() {
        return notFound;
    }

    // Метод для получения локатора cookie
    public By getCookieConsentButton() {
        return cookieConsentButton;
    }
}
