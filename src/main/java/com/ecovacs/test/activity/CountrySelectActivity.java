package com.ecovacs.test.activity;

import com.ecovacs.test.common.Common;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
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
    @iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[3]")
    private MobileElement btnOK = null;
    @AndroidFindBy(id = "com.ecovacs.ecosphere.intl:id/title_back")
    @iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[1]")
    private MobileElement btnBack = null;

    private AppiumDriver driver = null;

    private CountrySelectActivity(){

    }

    public static CountrySelectActivity getInstance(){
        if(countrySelectActivity == null){
            countrySelectActivity = new CountrySelectActivity();
        }
        return countrySelectActivity;
    }

    public void init(AppiumDriver driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        this.driver = driver;
    }

    public void back(){
        btnBack.click();
    }

    public boolean selectCountry(String strCountry){
        //androidDriver.scrollTo(strCountry).click();
        //String str = "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Japan\").instance(0))";
        String str;
        MobileElement textViewCountry;
        try {
            if (Common.getInstance().isAndroid()){
                str = "new UiScrollable(new UiSelector().scrollable(true).instance(0))." +
                        "scrollIntoView(new UiSelector().textContains("
                        + "\"" + strCountry + "\"" + ").instance(0))";
                textViewCountry = (MobileElement) ((AndroidDriver)driver)
                        .findElementByAndroidUIAutomator(str);
            }else {
                str = "UIATarget.localTarget().frontMostApp().mainWindow()." +
                        "tableViews()[0].cells()[\"" + strCountry + "\"]";
                textViewCountry = (MobileElement) ((IOSDriver)driver)
                        .findElementByIosUIAutomation(str);
            }
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
