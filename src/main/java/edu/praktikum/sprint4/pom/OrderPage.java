package edu.praktikum.sprint4.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OrderPage {
    private final WebDriver webDriver;

    // Локаторы полей ввода
    private final By firstNameInput = By.xpath("//input[@placeholder='* Имя']");
    private final By lastNameInput = By.xpath("//input[@placeholder='* Фамилия']");
    private final By addressInput = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By metroStationInput = By.xpath("//input[@placeholder='* Станция метро']");
    private final By phoneNumberInput = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By nextButton = By.xpath("//button[text()='Далее']");

    // Локаторы для формы доставки
    private final By dateInput = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    private final By rentalPeriodDropdownArrow = By.cssSelector(".Dropdown-arrow");
    private final By rentalPeriodOption = By.xpath("//div[@class='Dropdown-option' and text()='сутки']");
    private final By blackPearlCheckbox = By.xpath("//label[@for='black']");
    private final By greyHopelessnessCheckbox = By.xpath("//label[@for='grey']");
    private final By commentInput = By.xpath("//input[@placeholder='Комментарий для курьера']");
    private final By orderButton = By.xpath("//button[text()='Заказать']");

    // Локатор модального окна заказа
    private final By orderModal = By.className("Order_ModalHeader__3FDaJ");

    public OrderPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void fillOrderForm(String firstName, String lastName, String address, String metroStation, String phoneNumber) {
        webDriver.findElement(firstNameInput).sendKeys(firstName);
        webDriver.findElement(lastNameInput).sendKeys(lastName);
        webDriver.findElement(addressInput).sendKeys(address);
        selectMetroStation(metroStation);
        webDriver.findElement(phoneNumberInput).sendKeys(phoneNumber);
    }

    private void selectMetroStation(String metroStation) {
        webDriver.findElement(metroStationInput).click();
        webDriver.findElement(metroStationInput).sendKeys(metroStation);
        // Ожидаем появления и кликаем на выпадающий вариант
        By metroStationOption = By.xpath("//div[@class='select-search__select']//div[text()='" + metroStation + "']");
        webDriver.findElement(metroStationOption).click();
    }

    public void clickNext() {
        webDriver.findElement(nextButton).click();
    }

    public void fillDeliveryForm(String date, String rentalPeriod, boolean blackPearl, boolean greyHopelessness, String comment) throws InterruptedException {
        webDriver.findElement(dateInput).sendKeys(date);
        webDriver.findElement(rentalPeriodDropdownArrow).click();
        Thread.sleep(1000);  // Ждем секунду
        webDriver.findElement(rentalPeriodOption).click();
        if (blackPearl) {
            webDriver.findElement(blackPearlCheckbox).click();
        }
        if (greyHopelessness) {
            webDriver.findElement(greyHopelessnessCheckbox).click();
        }
        webDriver.findElement(commentInput).sendKeys(comment);
    }

    public void clickOrder() {
        webDriver.findElement(orderButton).click();
    }

    public boolean isOrderModalVisible() {
        return !webDriver.findElements(orderModal).isEmpty();
    }
}
