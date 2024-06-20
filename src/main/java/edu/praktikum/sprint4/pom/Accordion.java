package edu.praktikum.sprint4.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Accordion {
    private final WebDriver webDriver;

    // Локаторы для заголовков вопросов
    private final By[] headings = {
            By.id("accordion__heading-0"),
            By.id("accordion__heading-1"),
            By.id("accordion__heading-2"),
            By.id("accordion__heading-3"),
            By.id("accordion__heading-4"),
            By.id("accordion__heading-5"),
            By.id("accordion__heading-6"),
            By.id("accordion__heading-7")
    };

    // Локаторы для панелей
    private final By[] panels = {
            By.id("accordion__panel-0"),
            By.id("accordion__panel-1"),
            By.id("accordion__panel-2"),
            By.id("accordion__panel-3"),
            By.id("accordion__panel-4"),
            By.id("accordion__panel-5"),
            By.id("accordion__panel-6"),
            By.id("accordion__panel-7")
    };

    // Ожидаемые тексты вопросов
    private final String[] expectedTexts = {
            "Сутки — 400 рублей. Оплата курьеру — наличными или картой.",
            "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.",
            "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.",
            "Только начиная с завтрашнего дня. Но скоро станем расторопнее.",
            "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.",
            "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.",
            "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.",
            "Да, обязательно. Всем самокатов! И Москве, и Московской области."
    };

    // Локатор для выбранного заголовка
    private By headingElement;

    public Accordion(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    // Метод для клика по заголовку
    public void clickHeading(int index) {
        headingElement = headings[index];
        webDriver.findElement(headingElement).click();
    }

    // Метод для получения текста панели
    public String getPanelText(int index) {
        return webDriver.findElement(panels[index]).getText();
    }

    // Метод для получения ожидаемого текста
    public String getExpectedText(int index) {
        return expectedTexts[index];
    }

    // Метод для получения размера
    public int getAccordionSize() {
        return headings.length;
    }

    // Метод для получения массива локаторов заголовков
        public By[] getHeadings() {
        return headings;
    }

    // Метод для получения локатора выбранного заголовка
    public By getHeadingElement() {
        return headingElement;
    }
}
