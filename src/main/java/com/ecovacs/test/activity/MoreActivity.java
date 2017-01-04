package com.ecovacs.test.activity;

import com.ecovacs.test.common.Common;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by ecosqa on 16/12/8.
 * handle more activity
 */
public class MoreActivity {
    private static MoreActivity moreActivity = null;

    private MoreActivity(){

    }

    @FindBy(id = "com.ecovacs.ecosphere.intl:id/btn_exit")
    private AndroidElement btnLogout = null;
    @FindBy(id = "android:id/button1")
    private AndroidElement btnConfirm = null;

    public static MoreActivity getInstance(){
        if (moreActivity == null){
            moreActivity = new MoreActivity();
        }
        return moreActivity;
    }

    public void clickLogout(){
        btnLogout.click();
    }

    public boolean showMoreActivity(){
        return Common.getInstance().showActivity(btnLogout);
    }

    public void init(AndroidDriver driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void clickConfirm(){
        btnConfirm.click();
    }
}
