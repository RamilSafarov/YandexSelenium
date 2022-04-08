package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class YandexMarketPage extends YandexMainPage {
    //Локаторы
    private static final String catalogButton = "//span[text() ='Каталог']/parent::div/parent::span/parent::button";
    private static final String zooButton = "//span[text()='Зоотовары']/parent::a";
    private static final String goodiesButton = "(//a[text()='Лакомства']/parent::div)[1]";
    private static final String inputFrom = "//input[@name='Цена от']";
    private static final String inputTo = "//input[@name='Цена до']";
    private static final String deliveryButton = "//span[text()='Доставка курьером']/parent::div";
    private static final String deliveryButton2 = "//span[text()='Курьером']/parent::span";
    private static final String showallButton = "//legend[text()='Производитель']/following-sibling::footer/button[text()='Показать всё']";
    private static final String inputManufacturer = "//input[@name='Поле поиска']";
    private static final String checkboxManufacturer = "//input[@name='Производитель Dreamies']/parent::label";
    private static final String selectFirst = "(//article[@data-zone-name=\"snippet-cell\"])[1]/descendant::a[@data-node-name='title']";
    private static final String selectCompare = "//span[text()='Сравнить']/parent::div";
    public YandexMarketPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void getGoodiesForCat() throws InterruptedException {
        //Каталог
        WebElement catalogElement = webDriver.findElement(By.xpath(catalogButton));
        catalogElement.click();

        Actions action = new Actions(webDriver);
        WebElement zooElement = new WebDriverWait(webDriver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.xpath(zooButton)));
        action.moveToElement(zooElement).perform();

        WebElement goodiesElement = new WebDriverWait(webDriver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.xpath(goodiesButton)));
        goodiesElement.click();
        try {
            WebElement deliveryElement = new WebDriverWait(webDriver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.xpath(deliveryButton)));
            deliveryElement.click();
        }
        catch (TimeoutException e){
            WebElement deliveryElement2 = new WebDriverWait(webDriver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.xpath(deliveryButton2)));
            deliveryElement2.click();
        }

        try{
            WebElement showallElement = new WebDriverWait(webDriver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.xpath(showallButton)));
            showallElement.click();
            WebElement inputManufacturerElement = new WebDriverWait(webDriver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.xpath(inputManufacturer)));
            webDriver.findElement(By.xpath(inputManufacturer)).sendKeys("Dreamies");
        } catch (TimeoutException e){
            WebElement inputManufacturerElement = new WebDriverWait(webDriver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.xpath(inputManufacturer)));
            webDriver.findElement(By.xpath(inputManufacturer)).sendKeys("Dreamies");
        }

        WebElement manufacturerElement = webDriver.findElement(By.xpath(checkboxManufacturer));
        manufacturerElement.click();

        webDriver.findElement(By.xpath(inputFrom)).sendKeys("50");
        webDriver.findElement(By.xpath(inputTo)).sendKeys("150");

        TimeUnit.SECONDS.sleep(3);

        WebElement firstElement = new WebDriverWait(webDriver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.xpath(selectFirst)));
        firstElement.click();

        for (String windowHandle : webDriver.getWindowHandles()) {
            webDriver.switchTo().window(windowHandle);
        }

        WebElement compareElement = new WebDriverWait(webDriver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.xpath(selectCompare)));
        compareElement.click();
    }
}
