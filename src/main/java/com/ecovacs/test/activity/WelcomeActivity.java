package com.ecovacs.test.activity;

import com.ecovacs.test.common.Common;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by ecosqa on 16/11/30.
 * welcome activity
 */
public class WelcomeActivity {
    private static WelcomeActivity welcomeActivity = null;
    /*private static Logger logger = LoggerFactory.getLogger(WelcomeActivity.class);
    private AndroidDriver androidDriver = null;*/

    @AndroidFindBy(id = "com.ecovacs.ecosphere.intl:id/register")
    private AndroidElement btnRegister = null;
    @AndroidFindBy(id = "com.ecovacs.ecosphere.intl:id/login")
    private AndroidElement btnLogin = null;

    private WelcomeActivity(){

    }

    public static WelcomeActivity getInstance(){
        if(welcomeActivity == null){
            welcomeActivity = new WelcomeActivity();
        }
        return welcomeActivity;
    }

    public void init(AndroidDriver driver){
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
}
