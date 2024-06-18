package edu.praktikum.sprint4.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
    private static final String URL = "https://qa-scooter.praktikum-services.ru/";

    private final WebDriver webDriver; //testtest

    private final By checkOrderButton = By.xpath(".//button[@class='Header_Link__1TAG7']");
    private final By orderButton = By.xpath(".//button[@class='Button_Button__ra12g']");
    private final By inputOrderNumber = By.xpath(".//input[contains(@class, 'Header_Input__xIoUq')]");
    private final By goButton = By.xpath(".//button[@class='Button_Button__ra12g Header_Button__28dPO']");

    private NotFound notFound;

    public MainPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        notFound = new NotFound(webDriver);
    }

    public void open() {
        webDriver.get(URL);
    }

    public void clickCheckOrder() {
        webDriver.findElement(checkOrderButton).click();
    }

    public void clickOrder() {
        webDriver.findElement(orderButton).click();
    }

    public void inputOrderNumberInField(String text) {
        webDriver.findElement(inputOrderNumber).sendKeys(text);
    }

    public void clickGo() {
        webDriver.findElement(goButton).click();
    }

    public NotFound getNotFound() {
        return notFound;
    }
}
