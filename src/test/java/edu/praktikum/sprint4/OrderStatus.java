package edu.praktikum.sprint4;

import edu.praktikum.sprint4.pom.MainPage;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertTrue;

public class OrderStatus {
    private WebDriver webDriver;

    @Before
    public void setup(){
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
