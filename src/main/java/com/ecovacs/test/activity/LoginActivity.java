package com.ecovacs.test.activity;

import com.ecovacs.test.common.Common;
import com.ecovacs.test.common.PropertyData;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by ecosqa on 16/12/7.
 * login activity
 */
public class LoginActivity {
    private static LoginActivity loginActivity = null;
    private static Logger logger = LoggerFactory.getLogger(LoginActivity.class);

    private LoginActivity(){}

    @FindBy(id = "com.ecovacs.ecosphere.intl:id/lly_select_country")
    private AndroidElement eleCountry = null;
    @FindBy(id = "com.ecovacs.ecosphere.intl:id/edt_email")
    private AndroidElement editEmail = null;
    @FindBy(id = "com.ecovacs.ecosphere.intl:id/edt_pass")
    private AndroidElement editPass = null;
    @FindBy(id = "com.ecovacs.ecosphere.intl:id/btn_login")
    private AndroidElement btnLogin = null;
    @FindBy(id = "com.ecovacs.ecosphere.intl:id/tv_pass_recover")
    private AndroidElement textViewForget = null;

    public static LoginActivity getInstance(){
        if(loginActivity == null){
            loginActivity = new LoginActivity();
        }
        return loginActivity;
    }

    public void init(AndroidDriver driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void clickForgetPass(){
        textViewForget.click();
    }

    public boolean showLoginActivity(){
        return Common.getInstance().showActivity(btnLogin);
    }

    public void clickCountry(){
        eleCountry.click();
    }

    public void fillInfoAndClick(String strEmail, String strPass){
        editEmail.sendKeys(strEmail);
        editPass.sendKeys(strPass);
        btnLogin.click();
    }



}
