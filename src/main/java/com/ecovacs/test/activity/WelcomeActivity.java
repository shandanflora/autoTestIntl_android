package com.ecovacs.test.activity;

import com.ecovacs.test.common.Common;
import com.ecovacs.test.common.TranslateErrorReport;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by ecosqa on 16/11/30.
 * welcome activity
 */
public class WelcomeActivity {
    private static WelcomeActivity welcomeActivity = null;
    private static Logger logger = LoggerFactory.getLogger(WelcomeActivity.class);
    /*private AndroidDriver androidDriver = null;*/

    @AndroidFindBy(id = "com.ecovacs.ecosphere.intl:id/register")
    @iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAButton[1]")
    private MobileElement btnRegister = null;
    @AndroidFindBy(id = "com.ecovacs.ecosphere.intl:id/login")
    @iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAButton[2]")
    private MobileElement btnLogin = null;

    private WelcomeActivity(){

    }

    public static WelcomeActivity getInstance(){
        if(welcomeActivity == null){
            welcomeActivity = new WelcomeActivity();
        }
        return welcomeActivity;
    }

    public void init(AppiumDriver driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        //androidDriver = driver;
    }

    public void clickRegister(){
        btnRegister.click();
    }

    public void clickLogin(){
        btnLogin.click();
    }

    public boolean showWelcomeActivity(){
        return Common.getInstance().showActivity(btnLogin);
    }

    public boolean translate(Map<String, String> tranMap){
        boolean bRegister = btnRegister.getText().equalsIgnoreCase(tranMap.get("register"));
        if(!bRegister){
            logger.error("welcome activity--btn--register--App--" + btnRegister.getText());
            logger.error("welcome activity--btn--register--Excel--" + tranMap.get("register"));
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "welcome", btnRegister.getText(),
                    tranMap.get("register"), "fail");
        }
        boolean bLogin = btnLogin.getText().equalsIgnoreCase(tranMap.get("login"));
        if(!bLogin){
            logger.error("welcome activity--btn--login--App--" + btnLogin.getText());
            logger.error("welcome activity--btn--login--Excel--" + tranMap.get("login"));
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "welcome", btnLogin.getText(),
                    tranMap.get("login"), "fail");
        }
        return  bRegister && bLogin;
    }
}
