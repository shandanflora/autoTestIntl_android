package com.ecovacs.test.activity;

import com.ecovacs.test.common.Common;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by ecosqa on 16/12/9.
 * retrieve activity
 */
public class RetrievePassActivity {
    private static RetrievePassActivity retrievePassActivity = null;

    @AndroidFindBy(id = "com.ecovacs.ecosphere.intl:id/tv_youBian")
    @iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[3]")
    private MobileElement textViewLogin = null;
    @AndroidFindBy(id = "com.ecovacs.ecosphere.intl:id/rll_bark")
    @iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[1]")
    private MobileElement textViewBack = null;
    @AndroidFindBy(id = "com.ecovacs.ecosphere.intl:id/tv_wangJiMimaInfo")
    @iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAStaticText[2]")
    private MobileElement textViewForgetInfo = null;
    @AndroidFindBy(id = "com.ecovacs.ecosphere.intl:id/tv_access_email")
    @iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAStaticText[2]")
    private MobileElement textViewResInfo = null;

    private RetrievePassActivity(){

    }

    public static RetrievePassActivity getInstance(){
        if(retrievePassActivity == null){
            retrievePassActivity = new RetrievePassActivity();
        }
        return retrievePassActivity;
    }

    public void init(AppiumDriver driver){
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
