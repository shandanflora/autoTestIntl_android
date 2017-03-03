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
 * Created by ecosqa on 17/2/18.
 *
 */
public class FullScreenActivity {
    private static FullScreenActivity fullScreenActivity = null;

    @FindBy(id = "com.ecovacs.ecosphere.intl:id/back_to_small")
    private AndroidElement back = null;
    @FindBy(id = "com.ecovacs.ecosphere.intl:id/deebot_statues_large")
    private AndroidElement currentStatus = null;
    @FindBy(id = "com.ecovacs.ecosphere.intl:id/deebot_return_image")
    private MobileElement btnCharge = null;
    @FindBy(id = "com.ecovacs.ecosphere.intl:id/deebot_return_text")
    private AndroidElement textCharge = null;
    @FindBy(id = "com.ecovacs.ecosphere.intl:id/deebot_auto_image")
    private AndroidElement btnAuto = null;
    @FindBy(id = "com.ecovacs.ecosphere.intl:id/deebot_auto_text")
    private AndroidElement textAuto = null;
    @FindBy(xpath = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.RelativeLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[2]/android.widget.LinearLayout[2]/android.widget.ImageView[1]")
    private AndroidElement btnSpot = null;
    @FindBy(xpath = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.RelativeLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[2]/android.widget.LinearLayout[2]/android.widget.TextView[1]")
    private AndroidElement textSpot = null;
    @FindBy(id = "com.ecovacs.ecosphere.intl:id/deebot_cleanarea_image")
    private MobileElement btnArea = null;
    @FindBy(id = "com.ecovacs.ecosphere.intl:id/deebot_cleanarea_text")
    private AndroidElement textArea = null;
    @FindBy(id = "com.ecovacs.ecosphere.intl:id/deebot_virtualwall_image")
    private MobileElement btnVirtual = null;
    @FindBy(id = "com.ecovacs.ecosphere.intl:id/deebot_virtualwall_text")
    private AndroidElement textVirtual = null;


    private FullScreenActivity(){

    }

    public static FullScreenActivity getInstance(){
        if (fullScreenActivity == null){
            fullScreenActivity = new FullScreenActivity();
        }
        return fullScreenActivity;
    }

    public void init(AndroidDriver driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void clickAuto(){
        btnAuto.click();
    }

    public void clickSpot(){
        btnSpot.click();
    }

    public void clickArea(){
        btnArea.click();
    }

    public void clickVirtual(){
        btnVirtual.click();
    }

    public void clickCharge(){
        btnCharge.click();
    }

    public void clickBack(){
        back.click();
    }

    public boolean staticUITranslation(Map<String, String> tranMap){
        String strLanguage = tranMap.get("language");
        boolean bbtnCharge = textCharge.getText().equalsIgnoreCase(tranMap.get("ibt_return_charge_text"));
        if (!bbtnCharge){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "FullScreen", textCharge.getText(),
                    tranMap.get("ibt_return_charge_text"), "fail");
        }
        boolean bbtnAuto = textAuto.getText().equalsIgnoreCase(tranMap.get("AUTO_CLEAN"));
        if (!bbtnAuto){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "FullScreen", textAuto.getText(),
                    tranMap.get("AUTO_CLEAN"), "fail");
        }
        boolean bbtnSpot = textSpot.getText().equalsIgnoreCase(tranMap.get("SPOT_CLEAN"));
        if (!bbtnSpot){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "FullScreen", textSpot.getText(),
                    tranMap.get("SPOT_CLEAN"), "fail");
        }
        boolean bbtnArea = textArea.getText().equalsIgnoreCase(tranMap.get("quYu_qingSao"));
        if (!bbtnArea){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "FullScreen", textArea.getText(),
                    tranMap.get("quYu_qingSao"), "fail");
        }
        boolean bbtnVirtual = textVirtual.getText().equalsIgnoreCase(tranMap.get("wall"));
        if (!bbtnVirtual){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "FullScreen", textVirtual.getText(),
                    tranMap.get("wall"), "fail");
        }
        return bbtnCharge && bbtnAuto && bbtnSpot && bbtnArea && bbtnVirtual;
    }



}
