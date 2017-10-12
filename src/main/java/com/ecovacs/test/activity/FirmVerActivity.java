package com.ecovacs.test.activity;

import com.ecovacs.test.common.Common;
import com.ecovacs.test.common.TranslateErrorReport;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

import java.util.Map;

/**
 * Created by ecosqa on 17/2/17.
 * firmware version activity
 */
public class FirmVerActivity {
    private static FirmVerActivity firmVerActivity = null;

    @AndroidFindBy(id = "com.ecovacs.ecosphere.intl:id/titleContent")
    private MobileElement title = null;
    /*@FindBy(xpath = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[2]/android.widget.TextView[1]")
    private AndroidElement gettingVer = null;*/
    @AndroidFindBy(xpath = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.TextView[1]")
    private MobileElement currentFirmVer = null;

    private FirmVerActivity(){

    }

    public static FirmVerActivity getInstance(){
        if (firmVerActivity == null){
            firmVerActivity = new FirmVerActivity();
        }
        return firmVerActivity;
    }

    public void init(AppiumDriver driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public boolean staticUITranslation(Map<String, String> tranMap){
        String strLanguage = tranMap.get("language");
        boolean bTitle = title.getText().equalsIgnoreCase(tranMap.get("The_host_Settings"));
        if(!bTitle){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "FirmwareVer", title.getText(),
                    tranMap.get("The_host_Settings"), "fail");
        }
        //too fast can not catch
        /*boolean bgettingVer = gettingVer.getText().equalsIgnoreCase(tranMap.get("fir_version_get_text"));
        if(!bgettingVer){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "FirmwareVer", gettingVer.getText(),
                    tranMap.get("fir_version_get_text"), "fail");
        }*/
        Common.getInstance().showActivity(currentFirmVer);
        boolean bcurrentFirmVer = currentFirmVer.getText().equalsIgnoreCase(tranMap.get("fir_version_old_text"));
        if(!bcurrentFirmVer){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "FirmwareVer", currentFirmVer.getText(),
                    tranMap.get("fir_version_old_text"), "fail");
        }
        return bTitle /*&& bgettingVer*/ && bcurrentFirmVer;
    }
}
