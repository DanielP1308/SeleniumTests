//Name: Daniel Polak
//Date: 15/09/2021
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumTests {
    private static boolean dateChosen = false;
    public static void initialize(WebDriver driver) {
        driver.navigate().to("https://www.booking.com/");
        driver.findElement(By.id("ss")).sendKeys("Ireland");

        if(dateChosen == false) {
            driver.findElement(By.cssSelector("[data-mode='checkin']")).click();
            driver.findElement(By.cssSelector("[data-bui-ref='calendar-next']")).click();
            driver.findElement(By.cssSelector("[data-bui-ref='calendar-next']")).click();
            driver.findElement(By.cssSelector("[data-date='2021-12-21']")).click();
            driver.findElement(By.cssSelector("[data-date='2021-12-22']")).click();
            dateChosen = true;
        }

        driver.findElement(By.className("sb-searchbox__button")).click();
    }
    public static void IsListed(String hotelName, String testName, String filter) {
        if(hotelName.equalsIgnoreCase(testName)) {
            System.out.println("Filter: " + filter + " Hotel: " + hotelName + " Islisted: True");
        }
        else {
            System.out.println("Filter: " + filter + " Hotel: " + hotelName + " Islisted: False");
        }
    }
    //Filter: None
    public static void TestIsListedWithoutFilters(WebDriver d) {
        String firstHotelOnPage = d.findElement(By.className("sr-hotel__name")).getText();

        IsListed(firstHotelOnPage, " Screebe House ", "None");
    }
    //Filter: 5-Star
    public static void TestIsListedWithFilters(WebDriver d) {
        d.navigate().refresh();
        d.findElement(By.cssSelector("[data-id='class-5']")).click();

        String firstHotelOnPage = "";
        firstHotelOnPage = d.findElement(By.className("sr-hotel__name")).getText();

        IsListed(firstHotelOnPage, " The Marked Hotel - A Leading Hotel of the World ", "5-Star");
    }
    //Filter: Spa and Wellness
    public static void TestIsListedWithFilters1(WebDriver d) {
        initialize(d);
        d.findElement(By.cssSelector("[data-id='popular_activities-54']")).click();

        String firstHotelOnPage = "";
        firstHotelOnPage = d.findElement(By.className("sr-hotel__name")).getText();

        IsListed(firstHotelOnPage, " The Marked Hotel - A Leading Hotel of the World ", "Spa and Wellness");
    }
    //Filter: Hot tub
    public static void TestIsListedWithFilters2(WebDriver d) {
        initialize(d);
        d.findElement(By.cssSelector("[data-id='popular_activities-63']")).click();

        String firstHotelOnPage = "";
        firstHotelOnPage = d.findElement(By.className("sr-hotel__name")).getText();

        IsListed(firstHotelOnPage, " The Marked Hotel - A Leading Hotel of the World ", "Hot tub/Jacuzzi");
    }
    //Filter: Indoor Pool
    public static void TestIsListedWithFilters3(WebDriver d) {
        initialize(d);
        d.findElement(By.cssSelector("[data-id='popular_activities-103']")).click();

        String firstHotelOnPage = "";
        firstHotelOnPage = d.findElement(By.className("sr-hotel__name")).getText();

        IsListed(firstHotelOnPage, " The Marked Hotel - A Leading Hotel of the World ", "Indoor Pool");
    }
    //Filter: 5-Star, Spa and Wellness
    public static void TestIsListedWithMultipleFilters(WebDriver d) {
        initialize(d);
        d.findElement(By.cssSelector("[data-id='class-5']")).click();
        d.findElement(By.cssSelector("[data-id='popular_activities-54']")).click();

        String firstHotelOnPage = "";
        firstHotelOnPage = d.findElement(By.className("sr-hotel__name")).getText();

        IsListed(firstHotelOnPage, " The Marked Hotel - A Leading Hotel of the World ", "5-Star, Spa and Wellness");
    }
    //Filter: 5-Star, Spa and Wellness, Indoor Pool, Hot tub/Jacuzzi
    public static void TestIsListedWithAllFilters(WebDriver d) {
        initialize(d);
        d.findElement(By.cssSelector("[data-id='class-5']")).click();
        d.findElement(By.cssSelector("[data-id='popular_activities-54']")).click();
        d.findElement(By.cssSelector("[data-id='popular_activities-103']")).click();
        d.findElement(By.cssSelector("[data-id='popular_activities-63']")).click();


        String firstHotelOnPage = "";
        firstHotelOnPage = d.findElement(By.className("sr-hotel__name")).getText();

        IsListed(firstHotelOnPage, " The Marked Hotel - A Leading Hotel of the World ", "5-Star, Spa and Wellness, Indoor Pool, Hot tub/Jacuzzi");
    }


    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver_win32\\chromedriver.exe");
        //Only needed if chrome is not installed in default directory for ChromeDriver(ReadME for more information)
        ChromeOptions opt = new ChromeOptions();
        opt.setBinary("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");

        WebDriver driver = new ChromeDriver(opt);

        //On Start
        initialize(driver);

        //Tests
        TestIsListedWithoutFilters(driver);
        TestIsListedWithFilters(driver);
        TestIsListedWithFilters1(driver);
        TestIsListedWithFilters2(driver);
        TestIsListedWithFilters3(driver);
        TestIsListedWithMultipleFilters(driver);
        TestIsListedWithAllFilters(driver);

        driver.quit();
    }
}
