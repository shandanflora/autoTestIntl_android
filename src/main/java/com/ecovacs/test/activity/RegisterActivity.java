package com.ecovacs.test.activity;

import com.ecovacs.test.common.Common;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by ecosqa on 16/11/30.
 *
 */
public class RegisterActivity {
    private static RegisterActivity registerActivity = new RegisterActivity();
    private static Logger logger = LoggerFactory.getLogger(RegisterActivity.class);
    private AndroidDriver androidDriver = null;

    @AndroidFindBy(id = "com.ecovacs.ecosphere.intl:id/btn_register")
    private AndroidElement btnRegister = null;
    @AndroidFindBy(id = "com.ecovacs.ecosphere.intl:id/rll_yuYan")
    private AndroidElement layOutCountry = null;
    @AndroidFindBy(id = "com.ecovacs.ecosphere.intl:id/edt_email")
    private AndroidElement editEmail = null;
    @AndroidFindBy(id = "com.ecovacs.ecosphere.intl:id/edt_pass")
    private AndroidElement editPassword = null;
    @AndroidFindBy(id = "com.ecovacs.ecosphere.intl:id/edt_pass_repeat")
    private AndroidElement editRePassword = null;
    @AndroidFindBy(id = "com.ecovacs.ecosphere.intl:id/tv_xieYi")
    private AndroidElement textViewUserAgree = null;

    private RegisterActivity(){

    }

    public static RegisterActivity getInstance(){
        if(registerActivity == null){
            registerActivity = new RegisterActivity();
        }
        return registerActivity;
    }

    public void init(AndroidDriver driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        androidDriver = driver;
    }

    public boolean showRegisterActivity(){
        return Common.getInstance().showActivity(btnRegister);
    }

    public boolean fill_Screenshot_Click(String strCountry, String strEmail, String strPass){
        layOutCountry.click();
        logger.info("Click 'Country/Region'!!!'");
        Common.getInstance().waitForSecond(1000);
        if(!CountrySelectActivity.getInstance().selectCountry(strCountry)){
            return false;
        }
        if(!RegisterActivity.getInstance().showRegisterActivity()){
            logger.error("Can not find country--" + strCountry);
            Common.getInstance().goBack(androidDriver, 2);
            return false;
        }
        editEmail.sendKeys(strEmail);
        editPassword.sendKeys(strPass);
        editRePassword.sendKeys(strPass);
        logger.info("Filled all information of user");
        //hide keyboard
        Common.getInstance().goBack(androidDriver, 1);
        //screen shot user agreement
        clickAgreement();
        Common.getInstance().screenShot("UserAgree_" + strCountry + ".png", androidDriver);
        Common.getInstance().goBack(androidDriver, 1);
        logger.info("Finished to screen shot user agreement!!!");
        btnRegister.click();
        return true;
    }



    private void clickAgreement(){
        textViewUserAgree.click();
        Common.getInstance().waitForSecond(2500);
    }
}
