package com.ecovacs.test.activity;

import com.ecovacs.test.common.TranslateErrorReport;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Map;

/**
 * Created by ecosqa on 17/2/16.
 *
 */
public class SettingActivity {
    private static SettingActivity settingActivity = null;

    @FindBy(xpath = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.TextView[1]")
    private AndroidElement title = null;
    @FindBy(id = "com.ecovacs.ecosphere.intl:id/Job_log")
    private AndroidElement textViewWorkLog = null;
    @FindBy(id = "com.ecovacs.ecosphere.intl:id/tv_fangDaRao")
    private AndroidElement textViewContinuedClean = null;
    @FindBy(id = "com.ecovacs.ecosphere.intl:id/appointment_time")
    private AndroidElement textViewTimeSchedule = null;
    @FindBy(id = "com.ecovacs.ecosphere.intl:id/resetMapText")
    private AndroidElement textViewReset = null;
    @FindBy(id = "com.ecovacs.ecosphere.intl:id/tv_zhuJiDuoYuYan")
    private AndroidElement textViewLanguage = null;
    @FindBy(xpath = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.TextView[1]")
    private AndroidElement textViewFirmware = null;

    private SettingActivity(){

    }

    public static SettingActivity getInstance(){
        if (settingActivity == null){
            settingActivity = new SettingActivity();
        }
        return settingActivity;
    }

    public void init(AndroidDriver driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void clickWorkLog(){
        textViewWorkLog.click();
    }

    public void clickContinuedClean(){
        textViewContinuedClean.click();
    }

    public void clickTimeSchedule(){
        textViewTimeSchedule.click();
    }

    public void clickReset(){
        textViewReset.click();
    }

    public void clickLanguage(){
        textViewLanguage.click();
    }

    public void clickFirmware(){
        textViewFirmware.click();
    }

    public boolean staticUiTranslate(Map<String, String> tranMap){
        String strLanguage = tranMap.get("language");
        boolean bTitle = title.getText().equalsIgnoreCase(tranMap.get("setting"));
        if (!bTitle) {
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "Settting", title.getText(),
                    tranMap.get("setting"), "fail");
        }
        boolean btextViewWorkLog = textViewWorkLog.getText().equalsIgnoreCase(tranMap.get("work_log"));
        if (!btextViewWorkLog) {
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "Settting", textViewWorkLog.getText(),
                    tranMap.get("work_log"), "fail");
        }
        boolean btextViewContinuedClean = textViewContinuedClean.getText().equalsIgnoreCase(tranMap.get("duanDianXuSao"));
        if (!btextViewContinuedClean) {
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "Settting", textViewContinuedClean.getText(),
                    tranMap.get("duanDianXuSao"), "fail");
        }
        boolean btextViewTimeSchedule = textViewTimeSchedule.getText().equalsIgnoreCase(tranMap.get("Time_to_make_an_appointment"));
        if (!btextViewTimeSchedule) {
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "Settting", textViewTimeSchedule.getText(),
                    tranMap.get("Time_to_make_an_appointment"), "fail");
        }
        boolean btextViewReset = textViewReset.getText().equalsIgnoreCase(tranMap.get("reset_map"));
        if (!btextViewReset) {
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "Settting", textViewReset.getText(),
                    tranMap.get("reset_map"), "fail");
        }
        boolean btextViewLanguage = textViewLanguage.getText().equalsIgnoreCase(tranMap.get("zhuJiDuoYuYan"));
        if (!btextViewLanguage) {
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "Settting", textViewLanguage.getText(),
                    tranMap.get("zhuJiDuoYuYan"), "fail");
        }
        boolean btextViewFirmware = textViewFirmware.getText().equalsIgnoreCase(tranMap.get("The_host_Settings"));
        if (!btextViewFirmware) {
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "Settting", textViewFirmware.getText(),
                    tranMap.get("The_host_Settings"), "fail");
        }
        return bTitle && btextViewWorkLog && btextViewContinuedClean &&
                btextViewTimeSchedule && btextViewReset &&
                btextViewLanguage && btextViewFirmware;
    }


}
