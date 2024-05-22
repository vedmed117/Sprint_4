package edu.praktikum.sprint4;

import edu.praktikum.sprint4.pom.MainPage;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class OrderStatus {
    private WebDriver webDriver;

    @Before
    public void setup(){
        // Инициализируем поле класса webDriver
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @Test
    public void notFoundIfOrderNotExists() {
        MainPage mainPage = new MainPage(webDriver);

        mainPage.open();
        mainPage.clickCheckOrder();
        mainPage.inputOrderNumberInField("1234");
        mainPage.clickGo();

        assertTrue("Сообщение об ошибке не показывается", mainPage.getNotFound().checkMessageExists());
    }

    @After
    public void tearDown(){
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}

//WebDriverManager.chromedriver().setup();
//ChromeOptions options = new ChromeOptions();
//options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");


 //       webDriver.findElement(By.xpath(".//button[@class='Header_Link__1TAG7']")).click();

//WebDriverWait wait = new WebDriverWait(webDriver, 10);
//WebElement orderNumberInput = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input.Input_Input__1iN_Z.Header_Input__xIoUq")));



        //webDriver.findElement(By.xpath(".//input[contains(@class, 'Header_Input__xIoUq')]")).sendKeys("1234");

        //webDriver.findElement(By.xpath(".//button[@class='Button_Button__ra12g Header_Button__28dPO']")).click();

//WebElement resultElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//div[contains(@class, 'Track_NotFound__6oaoY')]")));
        //Assert.assertNotNull("Order not found message is not displayed", resultElement);