package com.ecovacs.test.activity;

import com.ecovacs.test.common.Common;
import com.ecovacs.test.common.TranslateErrorReport;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by ecosqa on 17/2/18.
 * Spot clean activity
 */
public class SpotCleanActivity {
    private static SpotCleanActivity spotCleanActivity = null;
    private AndroidDriver driver = null;
    private static Logger logger = LoggerFactory.getLogger(SpotCleanActivity.class);

    @FindBy(id = "com.ecovacs.ecosphere.intl:id/cancle_select")
    private AndroidElement cancelSpot = null;
    @FindBy(id = "com.ecovacs.ecosphere.intl:id/map_tip")
    private AndroidElement message = null;
    @FindBy(id = "android:id/alertTitle")
    private AndroidElement alertTitle = null;
    @FindBy(id = "android:id/button2")
    private AndroidElement alertCancel = null;
    @FindBy(id = "android:id/button1")
    private AndroidElement alertYes = null;
    @FindBy(id = "com.ecovacs.ecosphere.intl:id/confirm_select")
    private AndroidElement btnConfirmSelect = null;

    private SpotCleanActivity(){

    }

    public static SpotCleanActivity getInstance(){
        if (spotCleanActivity == null){
            spotCleanActivity = new SpotCleanActivity();
        }
        return spotCleanActivity;
    }

    public void init(AndroidDriver driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        this.driver = driver;
    }

    public void clickCancel(){
        cancelSpot.click();
    }

    private boolean staticUITranslation(Map<String, String> tranMap){
        boolean bMessage = message.getText().equalsIgnoreCase(tranMap.get("dingDian_qingSao"));
        if (!bMessage){
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "SpotClean", message.getText(),
                    tranMap.get("ibt_return_charge_text"), "fail");
        }
        return bMessage;
    }

    private boolean deleteSpot(Map<String, String> tranMap){
        String strLanguage = tranMap.get("language");
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
        driver.tap(1, width/2, height/2, 200);
        Common.getInstance().waitForSecond(500);
        driver.tap(1, width/2, height/2 - 3, 200);
        Common.getInstance().waitForSecond(500);
        boolean bContent = alertTitle.getText().equalsIgnoreCase(tranMap.get("delete_dingDian_WeiZhi"));
        if (!bContent){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "SpotClean", alertTitle.getText(),
                    tranMap.get("delete_dingDian_WeiZhi"), "fail");
        }
        boolean balertCancel = alertCancel.getText().equalsIgnoreCase(tranMap.get("cancel"));
        if (!balertCancel){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "SpotClean", alertCancel.getText(),
                    tranMap.get("cancel"), "fail");
        }
        boolean balertYes = alertYes.getText().equalsIgnoreCase(tranMap.get("str_yes"));
        if (!balertYes){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "SpotClean", alertYes.getText(),
                    tranMap.get("str_yes"), "fail");
        }
        alertCancel.click();
        return bContent && balertCancel && balertYes;
    }

    public boolean translate(Map<String, String> tranMap){
        boolean bStatic = staticUITranslation(tranMap);
        boolean bDel = deleteSpot(tranMap);
        return bStatic && bDel;
    }


}
