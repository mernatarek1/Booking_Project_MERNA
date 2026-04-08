package Tests;

import Functions.Helper_Functions;
import Utils.data;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import Pages.*;
import BaseTests.BaseTests;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;

import org.testng.asserts.SoftAssert;

public class Test_one extends BaseTests {

    @Test(dataProvider = "reservationData", dataProviderClass = data.class)
    public void Test_case(String searchText, String checkInDate, String checkOutDate , String Calender_head , String HotelName) throws InterruptedException {
        Main_Page mainPage = new Main_Page(driver);
        Hotel_Page hotelPage = new Hotel_Page(driver);
        Confirmation_page confirmationPage = new Confirmation_page(driver);

        WebDriverWait wait1 = new WebDriverWait(driver, 40);
        WebDriverWait wait2 = new WebDriverWait(driver, 20);

        //Step 1 :
        Helper_Functions.ClosePopUp(driver);

        //Step 2 :
        mainPage.Where_are_you_going(searchText);
        System.out.println("5");

        //Step 3 :
        Helper_Functions.ClickElement(mainPage.date_selector, 10 , driver);
        mainPage.selectCheckInAndOut(Calender_head,checkInDate,checkOutDate);


        //Step 5 :
        Helper_Functions.ClickElement(mainPage.Search , 20 , driver);


        //Step 6 :
        mainPage.selectTolipHotelAndSeeAvailability(HotelName);


        //Step 7 : switch to new tap
        Helper_Functions.SwitchTap(driver);

        System.out.println("Switched to Hotel Confirmation_page: " + driver.getTitle());

        hotelPage.select_room_and_select_bedType_and_amount("Double", "twin", "1");

        wait2.until(ExpectedConditions.urlContains("checkout"));
        System.out.println("Now on the checkout pageeee: " + driver.getCurrentUrl());

        Helper_Functions.Waiting(2);


//////////////////FIRST ASSERTION (Check in and checkout date are as the same as those in the details page)//////////////


        SoftAssert softAssert = new SoftAssert();


        String checkInText = confirmationPage.CheckIn_details.getText();
        String checkOutText = confirmationPage.CheckOut_details.getText();


        DateTimeFormatter format1 = DateTimeFormatter.ofPattern("yyyy-M-d");
        DateTimeFormatter flexibleFormat = new DateTimeFormatterBuilder()
                .appendPattern("EEE, [MMMM][MMM] d, yyyy")
                .toFormatter(Locale.ENGLISH);


        LocalDate localDate1_IN = LocalDate.parse(checkInDate, format1);
        LocalDate localDate2_IN = LocalDate.parse(checkInText, flexibleFormat);
        LocalDate localDate1_OUTT = LocalDate.parse(checkOutDate, format1);
        LocalDate localDate2_OUTT = LocalDate.parse(checkOutText, flexibleFormat);

        System.out.println("checkInText" + checkInText );
        System.out.println("checkInDate from excel" + checkInDate);
        System.out.println("checkOutText" + checkOutText );
        System.out.println("checkOutDate from excel" + checkOutDate);

        System.out.println("localDate1_IN" + localDate1_IN);
        System.out.println("localDate2_IN" + localDate2_IN);
        System.out.println("localDate1_OUTT" + localDate1_OUTT);
        System.out.println("localDate2_OUTT" + localDate2_OUTT);


        try {
            softAssert.assertEquals(localDate1_IN, localDate2_IN);
            System.out.println("PASSED: " + "CHECK IN EQUAL EXPECTED");
        } catch (AssertionError e) {
            System.out.println("FAILED: " + "CHECK IN NOT EQUAL EXPECTED" + " | " + e.getMessage());
            softAssert.fail("CHECK IN NOT EQUAL EXPECTED" + " | " + e.getMessage());
        }

       // softAssert.assertEquals(localDate1_IN , localDate2_IN , "CHECK IN NOT EQUAL EXPECTED");



        try {
            softAssert.assertEquals(localDate1_OUTT, localDate2_OUTT);
            System.out.println("PASSED: " + "CHECK OUT EQUAL EXPECTED");
        } catch (AssertionError e) {
            System.out.println("FAILED: " + "CHECK OUT NOT EQUAL EXPECTED" + " | " + e.getMessage());
            softAssert.fail("CHECK OUT NOT EQUAL EXPECTED" + " | " + e.getMessage());
        }

        //softAssert.assertEquals(localDate1_OUTT , localDate2_OUTT , "CHECK OUTT NOT EQUAL EXPECTED");



//////////////////Second ASSERTION (hotel name is the same as the confirmation page)//////////////////////////////

        WebElement hotelNameElement = driver.findElement(By.xpath("/html/body/div[3]/div/div[3]/div[3]/aside/div/div[1]/div/div/div/div[2]/div/div/div/div[1]/div[1]/h1"));
        String hotelName = hotelNameElement.getText();
        System.out.println("Hotel Name in Sidebar: " + hotelName);

        try {
            softAssert.assertTrue(hotelName.contains(HotelName));
            System.out.println("PASSED: " + "Error: hotel name passed!");
        } catch (AssertionError e) {
            System.out.println("FAILED: " + "Error: hotel name failed!" + " | " + e.getMessage());
            softAssert.fail("Error: hotel name failed!" + " | " + e.getMessage());
        }


        //softAssert.assertTrue(hotelName.contains(HotelName), "Error: hotel name failed!");

        softAssert.assertAll();
    }
}