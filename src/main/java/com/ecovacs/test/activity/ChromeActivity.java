package com.ecovacs.test.activity;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


/**
 * Created by ecosqa on 16/12/2.
 */
public class ChromeActivity {
    private static ChromeActivity chromeActivity = null;
    private static Logger logger = LoggerFactory.getLogger(ChromeActivity.class);
    private AndroidDriver androidDriver = null;

    private ChromeActivity(){

    }

    public static ChromeActivity getInstance(){
        if(chromeActivity == null){
            chromeActivity = new ChromeActivity();
        }
        return chromeActivity;
    }

    public void init(AndroidDriver driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        androidDriver = driver;
    }

    @AndroidFindBy(id = "com.android.chrome:id/search_box_text")
    private AndroidElement searchText = null;
    @AndroidFindBy(id = "org.mozilla.firefox:id/url_bar_entry")
    private AndroidElement textUrl = null;


    public boolean loginHotmail(){
        //androidDriver.navigate().to("www.hotmail.com");
        logger.info("Load firefox main activity!!!");
        //searchText.click();
        //logger.info("click search text!!!");
        //androidDriver.get("www.hotmail.com");
        textUrl.click();
        textUrl.sendKeys("www.hotmail.com");
        String cmdstr="adb shell input keyevent 66";
        try {
            Runtime.getRuntime().exec(cmdstr).waitFor();
            //Thread.sleep(10000);
        }catch (IOException e){
            logger.error(e.toString());
        }catch (InterruptedException e){
            logger.error(e.toString());
        }

        return true;

    }
}
