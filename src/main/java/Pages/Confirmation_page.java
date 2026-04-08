package Pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class Confirmation_page {

    static WebDriver driver;

    public Confirmation_page(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }


    /////Locators/////


    //el check in w check out fehom nfs el class name part da [1] checkin [2] checkout bs m4 btt-locate bardo
    @FindBy(className = "de576f5064")
    public List<WebElement> dateDetailsList;


    @FindBy(xpath = "//h3[text()='Check-in']/following::div[1]")
    public WebElement CheckIn_details;


    @FindBy(xpath = "//h3[text()='Check-out']/following::div[1]")
    public WebElement CheckOut_details;

    @FindBy(xpath = "/html/body/div[3]/div/div[3]/div[3]/aside/div/div[1]/div/div/div/div[2]/div/div/div/div[1]/div[1]/h1\"")
    public By hotelNameElement;






    //    @FindBy(xpath = "//*[@id=\"fullwidth\"]/div[4]/div[1]/div[2]/div[2]/ul/li[1]/div[2]")
//    public WebElement CheckIn_details ;
//
//    @FindBy(xpath = "//*[@id=\"fullwidth\"]/div[4]/div[1]/div[2]/div[2]/ul/li[2]/div[2]")
//    public WebElement CheckOut_details ;




//    public String Month_organize(Integer month) {
//        String monthName;
//        switch (month) {
//            case 1:  monthName = "January";   break;
//            case 2:  monthName = "February";  break;
//            case 3:  monthName = "March";     break;
//            case 4:  monthName = "April";     break;
//            case 5:  monthName = "May";       break;
//            case 6:  monthName = "June";      break;
//            case 7:  monthName = "July";      break;
//            case 8:  monthName = "August";    break;
//            case 9:  monthName = "September"; break;
//            case 10: monthName = "October";   break;
//            case 11: monthName = "November";  break;
//            case 12: monthName = "December";  break;
//            default: monthName = "Invalid Month"; break;
//        }
//        return monthName;
//    }



}
