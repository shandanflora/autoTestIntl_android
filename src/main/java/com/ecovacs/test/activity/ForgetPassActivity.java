package com.ecovacs.test.activity;

import com.ecovacs.test.common.Common;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by ecosqa on 16/12/7.
 * forget password activity
 */
public class ForgetPassActivity {
    private static ForgetPassActivity forgetPassActivity = null;
    private static Logger logger = LoggerFactory.getLogger(ForgetPassActivity.class);
    private AndroidDriver androidDriver = null;

    private ForgetPassActivity(){

    }

    @FindBy(id = "com.ecovacs.ecosphere.intl:id/rll_yuYan")
    private AndroidElement eleCountry = null;
    @FindBy(id = "com.ecovacs.ecosphere.intl:id/edt_email")
    private AndroidElement editEmail = null;
    @FindBy(id = "com.ecovacs.ecosphere.intl:id/btn_send_email")
    private AndroidElement btnSendEmail = null;

    public static ForgetPassActivity getInstance(){
        if(forgetPassActivity == null){
            forgetPassActivity = new ForgetPassActivity();
        }
        return forgetPassActivity;
    }

    public void init(AndroidDriver driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        this.androidDriver = driver;
    }

    public boolean showActivity(){
        return Common.getInstance().showActivity(btnSendEmail);
    }

    public boolean sendEmail(String strCountry, String strEmail){
        eleCountry.click();
        if(!CountrySelectActivity.getInstance().selectCountry(strCountry)){
            logger.info("Select country failed!!!");
            return false;
        }
        editEmail.sendKeys(strEmail);
        Common.getInstance().goBack(androidDriver, 1);
        btnSendEmail.click();
        logger.info("Finished to send verify email!!!");
        return true;
    }
}
