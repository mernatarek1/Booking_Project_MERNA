package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Functions.Helper_Functions;

import java.util.List;

public class Main_Page {

    static WebDriver driver;

     public Main_Page(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    /////Locators/////



//    @FindBy(id = ":R55amr5:")
//    public static WebElement Where_are_you_going_Field;
    @FindBy(name = "ss")
    public WebElement Where_are_you_going_Field;

    @FindBy(xpath = "//button[@aria-label='Dismiss sign-in info.']")
    public WebElement close_button;



    @FindBy(id = "hprt_nos_select_78883120_386871369_0_33_0_131741")
    public WebElement selector;


    @FindBy(xpath = "//div[@class='b08850ce41 d704c15739'][normalize-space()='Alexandria']")
    public WebElement alex_selection;


    @FindBy(xpath = "//h3[@aria-live='polite']")
    public WebElement currentMonthDisplay;

    @FindBy(xpath = "//button[@aria-label='Next month'] | //button[contains(@class, 'next')]")
    public WebElement nextMonthButton;


    @FindBy(xpath = "//span[@class='fc70cba028 e1f1de0f5d c2a70a5db2 bc7d708ceb']//*[name()='svg']")
    public WebElement date_selector;


    @FindBy(xpath = "//button[@type='submit']")
    public  WebElement Search ;



//    @FindBy(xpath = "//button[@data-testid=\"date-display-field-start\"]")
//    public WebElement checkInBtn ;
//
//
//    @FindBy(xpath = "//button[@data-testid=\"date-display-field-end\"]")
//    public WebElement checkOutBtn ;
//
//    @FindBy(xpath = "//div[@id=\"calendar-searchboxdatepicker\"]//span[@class=\"b6dc9a9e69 e6c50852bd\"]")
//    public WebElement calendarNextBtn ;
//    @FindBy(xpath = "(//h3[@aria-live=\"polite\"])[2]")
//    public WebElement calendarMonthTitle ;
//    @FindBy(xpath = "//button[@type=\"submit\"]//span[text()=\"Search\"]")
//    public WebElement searchBtn ;
//


    ////////methods//////////
    public void Where_are_you_going(String Place) throws InterruptedException {
//        WebDriverWait wait = new WebDriverWait(driver, 15);
        Helper_Functions.Waiting(2);

        System.out.println("Tracinggggg 1");

        Helper_Functions.WaitForVisibility(Where_are_you_going_Field,30 , driver);
        System.out.println("2");

        Where_are_you_going_Field.clear();
        System.out.println("3");

        Helper_Functions.SendKeysToElement(Where_are_you_going_Field , Place , 30  , driver);
        //Where_are_you_going_Field.sendKeys(Place);
        System.out.println("4");

    }

    public void selectDate(String date) {
        String xpath = String.format("//span[@data-date='%s']", date);
        driver.findElement(By.xpath(xpath)).click();
    }

    public void selectCheckInAndOut(String Month , String checkInDate, String checkOutDate) throws InterruptedException {
//        WebDriverWait wait = new WebDriverWait(driver, 15);


        Helper_Functions.WaitForVisibility(currentMonthDisplay , 15 , driver);
//        wait.until(ExpectedConditions.visibilityOf(currentMonthDisplay));

        while (!currentMonthDisplay.getText().contains(Month)) {
            Helper_Functions.ClickElement(nextMonthButton , 15 , driver);
            Helper_Functions.Waiting(0.8);
        }

        selectDate(checkInDate);
        selectDate(checkOutDate);
    }

    public void selectTolipHotelAndSeeAvailability(String hotelName) throws InterruptedException {
        boolean hotelFoundflagg = false;
        WebDriverWait wait = new WebDriverWait(driver, 15);

        while (!hotelFoundflagg) {
            //Locate the hotel title specifically
            By hotelTitleLocator = By.xpath("//div[@data-testid='title' and contains(text(), '" + hotelName + "')]");
            java.util.List<WebElement> hotels = driver.findElements(hotelTitleLocator);

            if (hotels.size() > 0) {
                System.out.println("Hotel founddddddd");
                WebElement hotel = hotels.get(0);

                // Scroll to the hotel soooo the button is visible and clickable
                ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", hotel);
                Helper_Functions.Waiting(1);

                // Click See availability button for THIIIIIS hotel
                // 3shan ma3mlsh click 3la hotel 8alat
                By availabilityBtn = By.xpath("//div[contains(., '" + hotelName + "')]//following::a[@data-testid='availability-cta-btn'][1]");
                wait.until(ExpectedConditions.elementToBeClickable(availabilityBtn)).click();
                hotelFoundflagg = true;


            } else {
                // If not found, scroll down or click Load more
                By loadMoreBtn = By.xpath("//span[text()='Load more results']/parent::button");

                if (driver.findElements(loadMoreBtn).size() > 0) {
                    WebElement btn = driver.findElement(loadMoreBtn);
                    ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btn);
                    Helper_Functions.Waiting(0.5);
                    btn.click();
                    Helper_Functions.Waiting(2);
                } else {
                    // Scroll down to trigger lazy loading
                    ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollBy(0, 1000);");
                    Helper_Functions.Waiting(1);

                }
            }
        }
    }

    public void selectOctober2026() throws InterruptedException {
        By currentMonthLocator = By.xpath("//h3[@aria-live='polite']");
        By nextButtonLocator = By.xpath("//button[@aria-label='Next month'] | //button[contains(@class, 'next')]");

        while (!driver.findElement(currentMonthLocator).getText().contains("October 2026")) {
            if (driver.findElements(nextButtonLocator).size() > 0) {
                driver.findElement(nextButtonLocator).click();
            } else {
                System.out.println("Could not find the Next button");
                break;
            }
            Thread.sleep(800);
        }
    }


}


