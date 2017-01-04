package com.ecovacs.test.activity;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by ecosqa on 16/9/18.
 *
 */
public class CountrySelectActivity {

    private static Logger logger = LoggerFactory.getLogger(CountrySelectActivity.class);
    private static CountrySelectActivity countrySelectActivity = null;

    @AndroidFindBy(id = "com.ecovacs.ecosphere.intl:id/right")
    private AndroidElement btnOK = null;
    /*@AndroidFindBy(id = "com.ecovacs.ecosphere.intl:id/title_back")
    private AndroidElement btnBack = null;*/

    private AndroidDriver androidDriver = null;

    private CountrySelectActivity(){

    }

    public static CountrySelectActivity getInstance(){
        if(countrySelectActivity == null){
            countrySelectActivity = new CountrySelectActivity();
        }
        return countrySelectActivity;
    }

    public void init(AndroidDriver androidDriver){
        PageFactory.initElements(new AppiumFieldDecorator(androidDriver), this);
        this.androidDriver = androidDriver;
    }

    /*public void back(){
        btnBack.click();
    }*/

    public boolean selectCountry(String strCountry){
        //androidDriver.scrollTo(strCountry).click();
        //String str = "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Japan\").instance(0))";

        String str = "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(";
        str = str + "\"" + strCountry + "\"" + ").instance(0))";
        MobileElement textViewCountry;
        try {
            textViewCountry = (MobileElement) androidDriver
                    .findElementByAndroidUIAutomator(str);
        }catch (NoSuchElementException e){
            logger.error("Can not find country: " + strCountry);
            return false;
        }
        textViewCountry.click();
        btnOK.click();
        logger.info("Selected country - " + strCountry);
        return true;
    }
}
