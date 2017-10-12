package com.ecovacs.test.activity;

import com.ecovacs.test.common.TranslateErrorReport;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

import java.util.Map;

/**
 * Created by ecosqa on 17/2/17.
 * reset map activity
 */
public class ResetMapActivity {
    private static ResetMapActivity resetMapActivity = null;

    @AndroidFindBy(xpath = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.RelativeLayout[1]/android.widget.TextView[1]")
    private MobileElement title = null;
    @AndroidFindBy(xpath = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[1]/android.widget.TextView[1]")
    private MobileElement descriptionLine1 = null;
    @AndroidFindBy(xpath = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[1]/android.widget.TextView[2]")
    private MobileElement descriptionLine2 = null;
    @AndroidFindBy(id = "com.ecovacs.ecosphere.intl:id/reset_consumables")
    private MobileElement btnResetMap = null;
    @AndroidFindBy(id = "android:id/alertTitle")
    private MobileElement promptTitle = null;
    @AndroidFindBy(id = "android:id/message")
    private MobileElement promptMessage = null;
    @AndroidFindBy(id = "android:id/button2")
    private MobileElement btnNo = null;
    @AndroidFindBy(id = "android:id/button1")
    private MobileElement btnYes = null;

    private ResetMapActivity(){

    }

    public static ResetMapActivity getInstance(){
        if (resetMapActivity == null){
            resetMapActivity = new ResetMapActivity();
        }
        return resetMapActivity;
    }

    public void init(AppiumDriver driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    private boolean staticUITranslation(Map<String, String> tranMap){
        String strLanguage = tranMap.get("language");
        boolean btitle = title.getText().equalsIgnoreCase(tranMap.get("reset_map"));
        if(!btitle){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "ResetMap", title.getText(),
                    tranMap.get("reset_map"), "fail");
        }
        boolean bdescriptionLine1 = descriptionLine1.getText().equalsIgnoreCase(tranMap.get("CleanMapActivity_clean_hint_text"));
        if(!bdescriptionLine1){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "ResetMap", descriptionLine1.getText(),
                    tranMap.get("CleanMapActivity_clean_hint_text"), "fail");
        }
        boolean bdescriptionLine2 = descriptionLine2.getText().equalsIgnoreCase(tranMap.get("zhuJi_manDian"));
        if(!bdescriptionLine2){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "ResetMap", descriptionLine2.getText(),
                    tranMap.get("zhuJi_manDian"), "fail");
        }
        boolean bbtnResetMap = btnResetMap.getText().equalsIgnoreCase(tranMap.get("reset_map"));
        if(!bbtnResetMap){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "ResetMap", btnResetMap.getText(),
                    tranMap.get("reset_map"), "fail");
        }
        return btitle && bdescriptionLine1 && bdescriptionLine2 && bbtnResetMap;
    }

    private boolean promptTranslate(Map<String, String> tranMap){
        String strLanguage = tranMap.get("language");
        btnResetMap.click();
        boolean bPromptTitle = promptTitle.getText().equalsIgnoreCase(tranMap.get("tips"));
        if (!bPromptTitle){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "ResetMap", promptTitle.getText(),
                    tranMap.get("tips"), "fail");
        }
        boolean bPromptMessage = promptMessage.getText().equalsIgnoreCase(tranMap.get("rebuild_map"));
        if (!bPromptMessage){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "ResetMap", promptMessage.getText(),
                    tranMap.get("rebuild_map"), "fail");
        }
        boolean bbtnNo = btnNo.getText().equalsIgnoreCase(tranMap.get("str_no"));
        if (!bbtnNo){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "ResetMap", btnNo.getText(),
                    tranMap.get("str_no"), "fail");
        }
        boolean bbtnYes = btnYes.getText().equalsIgnoreCase(tranMap.get("str_yes"));
        if (!bbtnYes){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "ResetMap", btnYes.getText(),
                    tranMap.get("str_yes"), "fail");
        }
        btnNo.click();
        return bPromptTitle && bPromptMessage && bbtnNo && bbtnYes;
    }

    public boolean translate(Map<String, String> tranMap){
        boolean bStatic = staticUITranslation(tranMap);
        boolean bPrompt = promptTranslate(tranMap);
        return bStatic && bPrompt;
    }

}
