package com.ecovacs.test.activity;

import com.ecovacs.test.common.Common;
import com.ecovacs.test.common.TranslateErrorReport;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by ecosqa on 17/2/9.
 *
 */
public class LanguageActivity {
    private static LanguageActivity languageActivity = null;
    private AppiumDriver driver = null;
    private static Logger logger = LoggerFactory.getLogger(LanguageActivity.class);

    @AndroidFindBy(id = "com.ecovacs.ecosphere.intl:id/tv_zhongJian")
    private MobileElement title = null;
    @AndroidFindBy(id = "com.ecovacs.ecosphere.intl:id/tv_youBian")
    private MobileElement btnOK = null;
    @AndroidFindBy(id = "com.ecovacs.ecosphere.intl:id/rll_bark")
    private MobileElement back = null;

    private LanguageActivity(){

    }

    public static LanguageActivity getInstance(){
        if(languageActivity == null){
            languageActivity = new LanguageActivity();
        }
        return languageActivity;
    }

    public void init(AppiumDriver driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        this.driver = driver;
    }

    public void clickBack(){
        back.click();
    }

    public boolean selectLanguage(String strLanguage){
        String str;
        if (Common.getInstance().isAndroid()){
            str = "new UiScrollable(new UiSelector().scrollable(true).instance(0))" +
                    ".scrollIntoView(new UiSelector().textContains("
                    + "\"" + strLanguage + "\"" + ").instance(0))";
        }else {
            str = "UIATarget.localTarget().frontMostApp().mainWindow()." +
                    "tableViews()[0].cells()[\"" + strLanguage + "\"]";
        }
        MobileElement textViewCountry;
        try {
            if (Common.getInstance().isAndroid()){
                textViewCountry = (MobileElement) ((AndroidDriver)driver)
                        .findElementByAndroidUIAutomator(str);
            }else {
                textViewCountry = (MobileElement)((IOSDriver)driver)
                        .findElementByIosUIAutomation(str);
            }
        }catch (NoSuchElementException e){
            logger.error("Can not find language: " + strLanguage);
            return false;
        }
        textViewCountry.click();
        btnOK.click();
        logger.info("Selected Language - " + strLanguage);
        return true;
    }

    public boolean staticUITranslation(Map<String, String> tranMap){
        boolean bTitle = title.getText().equalsIgnoreCase(tranMap.get("multi_lingual_a"));
        if (!bTitle){
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "Language", title.getText(),
                    tranMap.get("multi_lingual_a"), "fail");
        }
        return bTitle;
    }
}
