package com.ecovacs.test.activity;

import com.ecovacs.test.common.TranslateErrorReport;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Map;

/**
 * Created by ecosqa on 17/2/17.
 * Continue clean activity
 */
public class ContinueCleanActivity {
    private static ContinueCleanActivity continueCleanActivity = null;

    @FindBy(id = "com.ecovacs.ecosphere.intl:id/tv_zhongJian")
    private AndroidElement title = null;
    @FindBy(xpath = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.TextView[1]")
    private AndroidElement textViewline1 = null;
    @FindBy(id = "com.ecovacs.ecosphere.intl:id/tv_moRenDate")
    private AndroidElement textViewMessage = null;
    @FindBy(id = "com.ecovacs.ecosphere.intl:id/img_select")
    private AndroidElement imageViewSwitch = null;
    @FindBy(xpath = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[2]/android.widget.LinearLayout[1]/android.widget.TextView[1]")
    private AndroidElement textViewLine3 = null;
    @FindBy(xpath = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[2]/android.widget.RelativeLayout[1]/android.widget.TextView[1]")
    private AndroidElement textViewStart = null;
    @FindBy(xpath = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[2]/android.widget.RelativeLayout[2]/android.widget.TextView[1]")
    private AndroidElement textViewEnd = null;
    @FindBy(id = "com.ecovacs.ecosphere.intl:id/tv_is_start_end")
    private AndroidElement textViewTimeTitle = null;
    @FindBy(id = "com.ecovacs.ecosphere.intl:id/tv_yinCang")
    private AndroidElement textViewTimeCancel = null;


    private ContinueCleanActivity(){

    }

    public static ContinueCleanActivity getInstance(){
        if (continueCleanActivity == null){
            continueCleanActivity = new ContinueCleanActivity();
        }
        return continueCleanActivity;
    }

    public void init(AndroidDriver driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    private void clickSwitch(){
        //imageViewSwitch.getSize();
        imageViewSwitch.click();
    }

    public boolean staticUITranslation(Map<String, String> tranMap){
        String strLanguage = tranMap.get("language");
        boolean btitle = title.getText().equalsIgnoreCase(tranMap.get("duanDianXuSao"));
        if (!btitle) {
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "ContinueClean", title.getText(),
                    tranMap.get("duanDianXuSao"), "fail");
        }
        boolean btextViewline1 = textViewline1.getText().equalsIgnoreCase(tranMap.get("duanDianXuSao"));
        if (!btextViewline1) {
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "ContinueClean", textViewline1.getText(),
                    tranMap.get("duanDianXuSao"), "fail");
        }
        boolean btextViewMessage = textViewMessage.getText().equalsIgnoreCase(tranMap.get("power_is_not_enough_to_return"));
        if (!btextViewMessage) {
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "ContinueClean", textViewMessage.getText(),
                    tranMap.get("power_is_not_enough_to_return"), "fail");
        }
        return btitle && btextViewline1 && btextViewMessage;
    }

    private boolean forbidTimeConfig(Map<String, String> tranMap){
        String strLanguage = tranMap.get("language");
        clickSwitch();
        boolean btextViewLine3 = textViewLine3.getText().equalsIgnoreCase(tranMap.get("breakpoint_time_setting"));
        if (!btextViewLine3) {
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "ContinueClean", textViewLine3.getText(),
                    tranMap.get("breakpoint_time_setting"), "fail");
        }
        boolean btextViewStart = textViewStart.getText().equalsIgnoreCase(tranMap.get("time_start"));
        if (!btextViewStart) {
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "ContinueClean", textViewStart.getText(),
                    tranMap.get("time_start"), "fail");
        }
        boolean btextViewEnd = textViewEnd.getText().equalsIgnoreCase(tranMap.get("time_end"));
        if (!btextViewEnd) {
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "ContinueClean", textViewEnd.getText(),
                    tranMap.get("time_end"), "fail");
        }
        textViewStart.click();
        boolean btextViewTimeTitle = textViewTimeTitle.getText().equalsIgnoreCase(tranMap.get("time_start"));
        if (!btextViewTimeTitle) {
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "ContinueClean", textViewTimeTitle.getText(),
                    tranMap.get("time_start"), "fail");
        }
        textViewTimeCancel.click();
        textViewEnd.click();
        boolean btextViewTimeTitleEnd = textViewTimeTitle.getText().equalsIgnoreCase(tranMap.get("time_end"));
        if (!btextViewTimeTitleEnd) {
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "ContinueClean", textViewTimeTitle.getText(),
                    tranMap.get("time_end"), "fail");
        }
        textViewTimeCancel.click();
        clickSwitch();
        return btextViewLine3 && btextViewStart && btextViewEnd &&
                btextViewTimeTitle && btextViewTimeTitleEnd;
    }

    public boolean translate(Map<String, String> tranMap){
        boolean bStatic = staticUITranslation(tranMap);
        boolean bForbid = forbidTimeConfig(tranMap);
        return bStatic && bForbid;
    }

}
