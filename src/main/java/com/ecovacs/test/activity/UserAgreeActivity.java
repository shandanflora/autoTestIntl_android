package com.ecovacs.test.activity;

import com.ecovacs.test.common.Common;
import com.ecovacs.test.common.TranslateErrorReport;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Map;

/**
 * Created by ecosqa on 17/2/14.
 *
 */
public class UserAgreeActivity {

    private static UserAgreeActivity userAgreeActivity = null;
    //private AndroidDriver androidDriver = null;

    private UserAgreeActivity(){

    }

    @FindBy(id = "com.ecovacs.ecosphere.intl:id/tv_zhongJian")
    private AndroidElement title = null;
    @FindBy(xpath = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.webkit.WebView[1]/android.webkit.WebView[1]/android.view.View[1]/android.view.View[1]")
    private AndroidElement webView = null;

    public static UserAgreeActivity getInstance(){
        if (userAgreeActivity == null){
            userAgreeActivity = new UserAgreeActivity();
        }
        return userAgreeActivity;
    }

    public void init(AndroidDriver driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        //this.androidDriver = driver;
    }

    public void showActivity(){
        Common.getInstance().showActivity(webView);
    }

    public boolean staticUITranslate(Map<String, String> tranMap){
        Common.getInstance().showActivity(title);
        boolean btitle = title.getText().equalsIgnoreCase(tranMap.get("User_agreement"));
        if (!btitle){
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "UserAgreement", title.getText(),
                    tranMap.get("User_agreement"), "fail");
        }
        return btitle;
    }
}
