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

public class Hotel_Page {
    static WebDriver driver;

    public Hotel_Page(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }


    /////Locators/////

    @FindBy(css = "select.hprt-nos-select")
    public WebElement amountSelect;


    @FindBy(xpath = "//button[contains(@class, 'hp-reservation-button')] | //button[contains(., 'reserve')] | //button[contains(., 'Reserve')] | //div[@class='hprt-reservation']//button")
    public WebElement reserveButton;



    @FindBy(xpath = "(//select[@class='hprt-nos-select js-hprt-nos-select'])[1]")
    public WebElement roomsAmountDropdown ;


    @FindBy(xpath = "//div[@class='rt-bed-type-select']//input")
    public WebElement bedRadioBtn ;

    @FindBy(xpath = "//button[@type='submit']//span[contains(text(), 'reserve')]")
    public  WebElement reserveBtn ;




    ////////methods//////////


    public void clickBedRadioBtn(String bedType) {
        Helper_Functions.ClickElement(bedRadioBtn , 30 , driver);
    }

    public static void selectOptionFromDropdown(WebElement dropdownElement, String optionValue) {
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByValue(optionValue);
    }

    public WebElement getRoomsAmountDropdown() { return Helper_Functions.FindElement(roomsAmountDropdown , 20 , driver); }


    public void clickReserveBtn() {
        Helper_Functions.ClickElement(reserveBtn ,30, driver);
    }


    public void select_room_and_select_bedType_and_amount(String roomNamePart, String bedType, String amount) throws InterruptedException {

        clickBedRadioBtn(bedType);
        selectOptionFromDropdown(getRoomsAmountDropdown(), amount);
        clickReserveBtn();
    }





}
