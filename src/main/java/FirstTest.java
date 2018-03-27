import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
public class FirstTest {
    public static void main(String[] args) throws InterruptedException {


        //Driver znajduje się w resource
        System.setProperty("webdriver.gecko.driver","src/main/resources/geckodriver.exe");
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability("marionette", true);
        // Tworzymy nową instancję Firefoxa
        WebDriver driver = new FirefoxDriver(capabilities);
        //Otwieramy stronę
        driver.get("http://www.kubamaterac.pl");
        // Wyświetlamy informacje, że udało się otwozyć stronę
        System.out.println("Successfully opened the website");
        //Czekamy 5 sekund
        Thread.sleep(5000);
        // Zamykamy driver
        driver.quit();
    }
}