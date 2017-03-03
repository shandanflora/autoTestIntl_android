package com.ecovacs.test.activity;

import com.ecovacs.test.common.Common;
import com.ecovacs.test.common.TranslateErrorReport;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.Point;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import java.util.Map;

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
    @FindBy(id = "com.ecovacs.ecosphere.intl:id/lly_multi_lingual")
    private AndroidElement rowLanguage = null;
    @FindBy(id = "com.ecovacs.ecosphere.intl:id/tv_zhongJian")
    private AndroidElement title = null;
    @FindBy(xpath = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.TextView[1]")
    private AndroidElement textAccount = null;
    @FindBy(xpath = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[2]/android.widget.RelativeLayout[1]/android.widget.TextView[1]")
    private AndroidElement textChange = null;
    @FindBy(xpath = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[3]/android.widget.RelativeLayout[1]/android.widget.TextView[1]")
    private AndroidElement textLanguage = null;
    @FindBy(xpath = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[4]/android.widget.RelativeLayout[1]/android.widget.TextView[1]")
    private AndroidElement textAbout = null;
    @FindBy(id = "android:id/alertTitle")
    private MobileElement promptMessage = null;
    @FindBy(id = "android:id/button1")
    private MobileElement btnConfirm = null;
    @FindBy(id = "android:id/button2")
    private MobileElement btnCancel = null;

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

    public void clickLanguage(){
        rowLanguage.click();
    }

    public void clickChangePass(){
        textChange.click();
    }

    public void clickAbout(){
        textAbout.click();
    }

    private boolean staticUiTranslation(Map<String, String> tranMap){
        String strLanguage = tranMap.get("language");
        boolean btitle = title.getText().equalsIgnoreCase(tranMap.get("load_more"));
        if(!btitle){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "More", title.getText(),
                    tranMap.get("load_more"), "fail");
        }
        boolean btextAccount = textAccount.getText().equalsIgnoreCase(tranMap.get("accounts"));
        if(!btextAccount){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "More", textAccount.getText(),
                    tranMap.get("accounts"), "fail");
        }
        boolean btextChange = textChange.getText().equalsIgnoreCase(tranMap.get("Change_the_password"));
        if(!btextChange){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "More", textChange.getText(),
                    tranMap.get("Change_the_password"), "fail");
        }
        boolean btextLanguage = textLanguage.getText().equalsIgnoreCase(tranMap.get("multi_lingual_a"));
        if(!btextLanguage){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "More", textLanguage.getText(),
                    tranMap.get("multi_lingual_a"), "fail");
        }
        boolean btextAbout = textAbout.getText().equalsIgnoreCase(tranMap.get("about"));
        if(!btextAbout){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "More", textAbout.getText(),
                    tranMap.get("about"), "fail");
        }
        boolean bbtnLogout = btnLogout.getText().equalsIgnoreCase(tranMap.get("Log_out"));
        if(!bbtnLogout){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "More", textAbout.getText(),
                    tranMap.get("Log_out"), "fail");
        }
        return btitle && btextAccount && btextChange && btextLanguage &&
                btextAbout && bbtnLogout;
    }

    private boolean translatePrompt(Map<String, String> tranMap){
        String strLanguage = tranMap.get("language");
        btnLogout.click();
        boolean bpromptMessage = promptMessage.getText().equalsIgnoreCase(tranMap.get("sure_quit"));
        if(!bpromptMessage){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "More", promptMessage.getText(),
                    tranMap.get("sure_quit"), "fail");
        }
        boolean bbtnCancel = btnCancel.getText().equalsIgnoreCase(tranMap.get("cancel"));
        if(!bbtnCancel){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "More", btnCancel.getText(),
                    tranMap.get("cancel"), "fail");
        }
        boolean bbtnConfirm = btnConfirm.getText().equalsIgnoreCase(tranMap.get("confirm"));
        if(!bbtnConfirm){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "More", btnConfirm.getText(),
                    tranMap.get("confirm"), "fail");
        }

        btnCancel.click();
        return  bpromptMessage && bbtnCancel &&
                bbtnConfirm;
    }

    public boolean translate(Map<String, String> tranMap){
        boolean bStatic = staticUiTranslation(tranMap);
        boolean bPrompt = translatePrompt(tranMap);
        return bStatic && bPrompt;
    }
}
