package com.ecovacs.test.activity;

import com.ecovacs.test.common.Common;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by ecosqa on 16/12/9.
 * retrieve activity
 */
public class RetrievePassActivity {
    private static RetrievePassActivity retrievePassActivity = null;

    @FindBy(id = "com.ecovacs.ecosphere.intl:id/tv_youBian")
    private AndroidElement textViewLogin = null;
    @FindBy(id = "com.ecovacs.ecosphere.intl:id/rll_bark")
    private AndroidElement textViewBack = null;
    @FindBy(id = "com.ecovacs.ecosphere.intl:id/tv_wangJiMimaInfo")
    private AndroidElement textViewForgetInfo = null;
    @FindBy(id = "com.ecovacs.ecosphere.intl:id/tv_access_email")
    private AndroidElement textViewResInfo = null;

    private RetrievePassActivity(){

    }

    public static RetrievePassActivity getInstance(){
        if(retrievePassActivity == null){
            retrievePassActivity = new RetrievePassActivity();
        }
        return retrievePassActivity;
    }

    public void init(AndroidDriver driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void clickLogin(){
        textViewLogin.click();
    }

    public void clickBack(){
        textViewBack.click();
    }

    public boolean showRetrieveConfirmActivity(){
        return Common.getInstance().showActivity(textViewForgetInfo);
    }

    public boolean ShowResisterConfirmActivity(){
        return Common.getInstance().showActivity(textViewResInfo);
    }


}
