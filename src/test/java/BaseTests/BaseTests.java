package BaseTests;

import Functions.Helper_Functions;
import GlobalParams.GlobalParams;
import Pages.Main_Page;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import Functions.Helper_Functions;



public class BaseTests {

    protected WebDriver driver = null;



    @BeforeMethod
    public void SetUp() throws InterruptedException {



        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();


        Helper_Functions.OpenURL(GlobalParams.BookingHomePage_Url , driver);

    }


    @AfterMethod
        public void Quit() throws InterruptedException{
        driver.quit();
    }

}
