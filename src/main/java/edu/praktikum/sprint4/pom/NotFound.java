package edu.praktikum.sprint4.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NotFound {

    private final WebDriver webDriver;

    private final By notFoundMessage = By.xpath(".//img[@alt='Not found']");

    public NotFound(WebDriver webDriver){
        this.webDriver = webDriver;
    }

    public boolean checkMessageExists(){
        return !webDriver.findElements(notFoundMessage).isEmpty();
    }
}