import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class TestYandexMarket {
    private WebDriver webDriver;

    private static final String baseURL = "https://yandex.ru/";

    //локаторы
    private static final String marketButton = "//div[@class = 'services-new__icon services-new__icon_market']";
    private static final String catalogButton = "//span[text() ='Каталог']/parent::div/parent::span/parent::button";
    private static final String zooButton = "//span[text()='Зоотовары']/parent::a";
    private static final String goodiesButton = "(//a[text()='Лакомства']/parent::div)[1]";
    private static final String inputFrom = "//input[@name='Цена от']";
    private static final String inputTo = "//input[@name='Цена до']";
    private static final String deliveryButton = "//span[text()='Доставка курьером']/parent::div";
    private static final String checkboxManufacturer = "//input[@name='Производитель Dreamies']/parent::label";
    private static final String selectFirst = "(//div[@data-zone-name=\"snippetList\"]/child::article[1])[1]/child::div[4]/child::div/child::h3/child::a";

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver","src\\test\\resources\\chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.get(baseURL);
        webDriver.manage().window().maximize();
    }

    @Test
    @DisplayName("Лакомства для кошек")
    public void catTest() throws InterruptedException, IOException {
        WebElement webElement = webDriver.findElement(By.xpath(marketButton));
        webElement.click();

        for (String windowHandle : webDriver.getWindowHandles()) {
            webDriver.switchTo().window(windowHandle);
        }
        WebElement catalogElement = webDriver.findElement(By.xpath(catalogButton));
        catalogElement.click();

        TimeUnit.SECONDS.sleep(2);

        Actions action = new Actions(webDriver);

        WebElement zooElement = webDriver.findElement(By.xpath(zooButton));
        action.moveToElement(zooElement).perform();

        TimeUnit.SECONDS.sleep(2);

        WebElement goodiesElement = webDriver.findElement(By.xpath(goodiesButton));
        goodiesElement.click();

        TimeUnit.SECONDS.sleep(2);

        WebElement deliveryElement = webDriver.findElement(By.xpath(deliveryButton));
        deliveryElement.click();

        WebElement manufacturerElement = webDriver.findElement(By.xpath(checkboxManufacturer));
        manufacturerElement.click();

        webDriver.findElement(By.xpath(inputFrom)).sendKeys("50");
        webDriver.findElement(By.xpath(inputTo)).sendKeys("150");

        TimeUnit.SECONDS.sleep(3);

        WebElement firstElement = webDriver.findElement(By.xpath(selectFirst));
        firstElement.click();

    }

    @AfterEach
    public void tearDown() {
        webDriver.quit();
    }

}
