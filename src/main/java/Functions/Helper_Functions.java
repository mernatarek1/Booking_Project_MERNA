package Functions;

import Pages.Main_Page;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Helper_Functions {
        WebDriver driver;
        static WebDriverWait wait;

//------------------------------------------------------------------------------------------
//---------------------------- - 1 - Helper Methods ----------------------------------------


        public static void Waiting(double sec) throws InterruptedException {
                Thread.sleep((long) (sec*1000));

        }
        public static void OpenURL(String url , WebDriver driver) {
                driver.get(url);
        }
        public static void ClosePopUp(WebDriver driver) {
                Main_Page mainPage = new Main_Page(driver);

                try {
                        //WebDriverWait wait = new WebDriverWait(driver, 15);

                        WebElement closeBtn = WaitForVisibility(mainPage.close_button , 30, driver);

                        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", closeBtn);
                        System.out.println("First pop up is closed");
                } catch (Exception e) {
                        System.out.println("pop up not found");
                }
        }
        public void WaitForPageToLoad(Integer seconds) {
                wait = new WebDriverWait(driver,seconds);
                wait.until(driver -> ((org.openqa.selenium.JavascriptExecutor) driver)
                        .executeScript("return document.readyState").equals("complete"));
        }

        public static String GetTextFromElement(WebElement locator, Integer seconds , WebDriver driver) {
                WebElement element = WaitForVisibility(locator , seconds , driver);
                return element.getText();
        }
        public static WebElement WaitForVisibility(WebElement locator , Integer seconds , WebDriver driver) {
                try {
                        wait = new WebDriverWait(driver, seconds);
                        return wait.until(ExpectedConditions.visibilityOf(locator));
                } catch (TimeoutException e) {
                        throw new RuntimeException("Element not visible: " + locator.toString(), e);
                }
        }

        public static void SendKeysToElement(WebElement locator, String text, Integer seconds , WebDriver driver) {
                WaitForVisibility(locator, seconds , driver);
                try {
                        FindElement(locator, seconds, driver).sendKeys(text);
                } catch (Exception e) {
                        throw new RuntimeException("Failed to send keys to element: " + locator, e);
                }
        }

        public static WebElement FindElement(WebElement element, Integer seconds, WebDriver driver) {
                return WaitForVisibility(element, seconds, driver);
        }

        public static void WaitForClickable(WebElement element, Integer seconds, WebDriver driver) {
                try {
                        WebDriverWait localWait = new WebDriverWait(driver, seconds);
                        localWait.until(ExpectedConditions.elementToBeClickable(element));
                } catch (TimeoutException e) {
                        throw new RuntimeException("Element not clickable: " + element.toString(), e);
                }
        }

        public static void ClickElement(WebElement element, Integer seconds, WebDriver driver) {
                System.out.println("Entering clickElement (6)");
                WaitForClickable(element, seconds, driver);
                System.out.println("Element is clickable (7)");
                try {
                        element.click();
                        System.out.println("Clicked successfully (9)");
                } catch (Exception e) {
                        System.out.println("Click failed (10)");
                        throw new RuntimeException("Failed to click element: " + element.toString(), e);
                }
        }

        public static void SwitchTap(WebDriver driver){
                // Store the ID of the current window (Search Results)
                String searchWindow = driver.getWindowHandle();

                // WINDOW HANDLING: Wait for the new tab to open and switch to it
                wait.until(ExpectedConditions.numberOfWindowsToBe(2));
                for (String windowHandle : driver.getWindowHandles()) {
                        if (!windowHandle.equals(searchWindow)) {
                                driver.switchTo().window(windowHandle);
                                break;
                        }
                }
        }

//        public static String getMonthName(String monthNumber) {
//                String[] months = {"", "January", "February", "March", "April", "May", "June",
//                        "July", "August", "September", "October", "November", "December"};
//                return months[Integer.parseInt(monthNumber)];
//        }
//        public static String[] splitDate(String date) {
//                //    "2026-10-23"
//                return date.split("-");
//        }

}
