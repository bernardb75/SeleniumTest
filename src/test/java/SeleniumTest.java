import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SeleniumTest {
    private static WebDriver driver;

    @BeforeClass
    public static void before() throws InterruptedException {
        //Driver znajduje się w resource
        System.setProperty("webdriver.gecko.driver","src/main/resources/geckodriver.exe");
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability("marionette", true);
        // Tworzymy nową instancję Firefoxa
        driver = new FirefoxDriver(capabilities);
        //Otwieramy stronę
        driver.get("http://www.kubamaterac.pl");
        // Wyświetlamy informacje, że udało się otwozyć stronę
        System.out.println("Successfully opened the website");
        //Czekamy 2 sekund
        Thread.sleep(2000);



    }
    @Test
    public void checkIfHelloScreenHaveCorrectText(){
        WebElement element=driver.findElement(By.xpath("//div[contains(@class, 'bubble table__cell')]"));
        String napis=element.getText();
        System.out.println("");
        assertEquals("Jestem Kuba,\ndeveloper c#, javy,\nphp i androida.",napis);
    }
    @Test
    public void checkIfThingsILikeExists() throws InterruptedException {
        //krok 1-znalezc menu
        WebElement menu=driver.findElement(By.id("parallax"));
        menu.findElement(By.id("omnie")).click();
        //krok 2-znalezienie zainteresowan
        WebElement interests=driver.findElement(By.xpath("//div[contains(@class,'interests')]"));
        //krok 3-przescrolllowanie strony
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView()",interests);
        Thread.sleep(1000);
        //krok 4-wyszukanie wszystkich figcaption
        List<WebElement> aaa=interests.findElements(By.xpath(".//figcaption"));
        //krok 5-czy lista jest długosci 4
        assertEquals(4,aaa.size());
        //krok 6-sprawdzamy napisy
        assertEquals("Programowanie",aaa.get(0).getText());
        assertEquals("Motocykle",aaa.get(1).getText());
        assertEquals("Piwo",aaa.get(2).getText());
        assertEquals("Dobra muzyka",aaa.get(3).getText());
    }
    @Test
    public void checkLink() throws InterruptedException {
        WebElement menu=driver.findElement(By.id("parallax"));
        //znaleziemie menu
        menu.findElement(By.id("omnie")).click();
        Thread.sleep(1000);
        //krok 2-pobranie środkowego
        WebElement element=driver.findElement(By.xpath("//div[@id='mCSB_1']"));
        //krok 3 wyszukiwanie linka
        //PODEJSCIE 1-xpath z kropka .//a[contains(@class,'company-link')]
//        WebElement link=element.findElement(By.xpath("//div[@id='mCSB_1']//a[contains(@class,'company-link')]"));
        //PODEJSCIE 2-class
//       WebElement link=element.findElement(By.className("company-link"));
        //PODEJSCIE 3-text
        WebElement link = element.findElement(By.xpath(".//a[contains(text(),'Wydziale')]"));
        //krok 4-pobranie href
        assertEquals("http://cs.pwr.edu.pl/",link.getAttribute("href"));
    }

    @AfterClass
    public static void after(){
        driver.quit(); // Zamykamy driver
    }
}
