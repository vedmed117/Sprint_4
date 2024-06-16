package edu.praktikum.sprint4.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By firstNameField = By.xpath("//input[@placeholder='* Имя']");
    private By lastNameField = By.xpath("//input[@placeholder='* Фамилия']");
    private By addressField = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    private By metroStationField = By.className("select-search__input");
    private By phoneField = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    private By nextButton = By.xpath("//button[text()='Далее']");
    private By dateField = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    private By rentalPeriodField = By.className("Dropdown-arrow");
    private By rentalPeriodOption = By.xpath("//div[@class='Dropdown-option' and text()='сутки']");
    private By orderButton = By.xpath("//button[contains(@class, 'Button_Button__ra12g Button_Middle__1CSJM') and text()='Заказать']");
    private By commentField = By.xpath("//input[@placeholder='Комментарий для курьера']");
    private By checkBoxBlack = By.id("black");
    private By checkBoxGrey = By.id("grey");
    private By orderModalHeader = By.className("Order_ModalHeader__3FDaJ");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    public void fillOrderForm(String firstName, String lastName, String address, String metroStation, String phone) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField)).sendKeys(firstName);
        driver.findElement(lastNameField).sendKeys(lastName);
        driver.findElement(addressField).sendKeys(address);
        driver.findElement(metroStationField).sendKeys(metroStation);
        driver.findElement(By.xpath("//div[text()='" + metroStation + "']")).click();
        driver.findElement(phoneField).sendKeys(phone);
    }

    public void clickNext() {
        wait.until(ExpectedConditions.elementToBeClickable(nextButton)).click();
    }

    public void fillDeliveryForm(String date, String rentalPeriod, boolean isBlack, boolean isGrey, String comment) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(dateField)).sendKeys(date);
        driver.findElement(rentalPeriodField).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(rentalPeriodOption)).click();

        if (isBlack) {
            driver.findElement(checkBoxBlack).click();
        }

        if (isGrey) {
            driver.findElement(checkBoxGrey).click();
        }

        driver.findElement(commentField).sendKeys(comment);
    }

    public void clickOrder() {
        wait.until(ExpectedConditions.elementToBeClickable(orderButton)).click();
    }

    public boolean isOrderModalVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(orderModalHeader)).isDisplayed();
    }
}
