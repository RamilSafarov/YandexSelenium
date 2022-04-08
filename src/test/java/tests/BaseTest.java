package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTest {
    private static final String baseURL = "https://yandex.ru/";
    protected WebDriver webDriver;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver","src\\test\\resources\\chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.get(baseURL);
        webDriver.manage().window().maximize();
    }

    @AfterEach
    public void tearDown() {
    webDriver.quit();
    }
}
