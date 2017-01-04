package com.ecovacs.test.activity;

import com.ecovacs.test.common.Common;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * robot list activity
 * Created by ecosqa on 16/12/8.
 */
public class MainActivity {
    private static MainActivity mainActivity = null;

    private MainActivity(){

    }

    @FindBy(id = "com.ecovacs.ecosphere.intl:id/right")
    AndroidElement textViewMore = null;

    public static MainActivity getInstance(){
        if (mainActivity == null){
            mainActivity = new MainActivity();
        }
        return mainActivity;
    }

    public void init(AndroidDriver driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public boolean showActivity(){
       return Common.getInstance().showActivity(textViewMore);
    }

    public void clickMore(){
        textViewMore.click();
    }
}
