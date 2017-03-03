package com.ecovacs.test.activity;

import com.ecovacs.test.common.TranslateErrorReport;
import io.appium.java_client.MobileElement;
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
public class AboutActivity {
    private static AboutActivity aboutActivity = null;

    @FindBy(id = "com.ecovacs.ecosphere.intl:id/titleContent")
    private MobileElement title = null;
    @FindBy(id = "com.ecovacs.ecosphere.intl:id/The_current_version_number_fill_out")
    private AndroidElement textViewCurrentVer = null;
    @FindBy(id = "com.ecovacs.ecosphere.intl:id/user_agreement")
    private AndroidElement textViewUser = null;
    @FindBy(xpath = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[2]/android.widget.TextView[1]")
    private AndroidElement textViewCheckVer = null;

    private AboutActivity(){

    }

    public static AboutActivity getInstance(){
        if (aboutActivity == null){
            aboutActivity = new AboutActivity();
        }
        return aboutActivity;
    }

    public void init(AndroidDriver driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public boolean staticUITranslate(Map<String, String> tranMap){
        String strLanguage = tranMap.get("language");
        boolean btitle = title.getText().equalsIgnoreCase(tranMap.get("about"));
        if(!btitle){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "About", title.getText(),
                    tranMap.get("about"), "fail");
        }
        String strCurVer = textViewCurrentVer.getText();
        boolean bCurVer = strCurVer.contains(tranMap.get("The_current_version_number_fill_out"));
        if(!bCurVer){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "About", strCurVer,
                    tranMap.get("The_current_version_number_fill_out"), "fail");
        }
        boolean btextViewUser = textViewUser.getText().equalsIgnoreCase(tranMap.get("User_agreement"));
        if(!btextViewUser){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "About", textViewUser.getText(),
                    tranMap.get("User_agreement"), "fail");
        }
        boolean btextViewCheckVer = textViewCheckVer.getText().equalsIgnoreCase(tranMap.get("version_check"));
        if(!btextViewCheckVer){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "About", textViewCheckVer.getText(),
                    tranMap.get("version_check"), "fail");
        }
        return btitle && bCurVer && btextViewUser
                && btextViewCheckVer;
    }

    public void clickUserAgree(){
        textViewUser.click();
    }

}
