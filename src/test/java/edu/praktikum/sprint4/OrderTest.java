package edu.praktikum.sprint4;

import edu.praktikum.sprint4.pom.MainPage;
import edu.praktikum.sprint4.pom.OrderPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class OrderTest {
    private WebDriver webDriver;
    private WebDriverWait wait;
    private String firstName;
    private String lastName;
    private String address;
    private String metroStation;
    private String phone;
    private String date;
    private String rentalPeriod;
    private boolean isBlack;
    private boolean isGrey;
    private String comment;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"Иван", "Иванов", "ул. Пушкина, д. 10", "Черкизовская", "89991234567", "25.05.2024", "сутки", true, false, "Позвонить за час"},
                {"Петр", "Петров", "ул. Лермонтова, д. 15", "Сокольники", "89991234568", "26.05.2024", "двое суток", false, true, "Не звонить"}
        });
    }

    public OrderTest(String firstName, String lastName, String address, String metroStation, String phone, String date, String rentalPeriod, boolean isBlack, boolean isGrey, String comment) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.date = date;
        this.rentalPeriod = rentalPeriod;
        this.isBlack = isBlack;
        this.isGrey = isGrey;
        this.comment = comment;
    }

    @Before
    public void setup() {
        WebDriverManager.firefoxdriver().setup();
        webDriver = new FirefoxDriver();
        webDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        webDriver.manage().window().maximize();
        wait = new WebDriverWait(webDriver, 20);
    }

    @Test
    public void testOrderProcess() {
        MainPage mainPage = new MainPage(webDriver);
        OrderPage orderPage = new OrderPage(webDriver);

        mainPage.open();
        closeCookieConsent();

        mainPage.clickOrder();
        orderPage.fillOrderForm(firstName, lastName, address, metroStation, phone);
        orderPage.clickNext();
        orderPage.fillDeliveryForm(date, rentalPeriod, isBlack, isGrey, comment);
        orderPage.clickOrder();

        assertTrue("Order modal is not visible", orderPage.isOrderModalVisible());
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
