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
 * Created by ecosqa on 17/2/15.
 * unibot cleaning
 */
public class UnibotCleanActivity {
    private static UnibotCleanActivity unibotCleanActivity = null;
    private static Logger logger = LoggerFactory.getLogger(UnibotCleanActivity.class);

    @FindBy(id = "com.ecovacs.ecosphere.intl:id/deebot_statues")
    private AndroidElement textViewStatusValue = null;
    @FindBy(xpath = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[2]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.TextView[2]")
    private AndroidElement textViewStatus = null;
    /*@FindBy(id = "com.ecovacs.ecosphere.intl:id/deebot_battery_statues")
    private AndroidElement textViewBatteryValue = null;*/
    @FindBy(id = "com.ecovacs.ecosphere.intl:id/tvBatterystatus")
    private AndroidElement textViewDeBattery = null;
    @FindBy(id = "com.ecovacs.ecosphere.intl:id/deebot_auto_image")
    private AndroidElement btnAuto = null;
    @FindBy(id = "com.ecovacs.ecosphere.intl:id/deebot_auto_text")
    private AndroidElement textViewDeAuto = null;
    @FindBy(id = "com.ecovacs.ecosphere.intl:id/deebot_return_text")
    private AndroidElement textViewDeCharge = null;
    @FindBy(id = "com.ecovacs.ecosphere.intl:id/deebot_return_image")
    private AndroidElement btnCharge = null;
    @FindBy(id = "com.ecovacs.ecosphere.intl:id/eco_action_more")
    private AndroidElement imageBtnRight = null;
    @FindBy(id = "com.ecovacs.ecosphere.intl:id/goto_full_screen")
    private AndroidElement imageViewFullScreen = null;
    @FindBy(id ="android:id/alertTitle")
    private AndroidElement promptTitle = null;
    @FindBy(id = "android:id/message")
    private AndroidElement promptMessage = null;
    @FindBy(id = "android:id/button2")
    private AndroidElement promptBtnCancel = null;
    @FindBy(id = "android:id/button1")
    private AndroidElement promptBtnConfirm = null;


    private UnibotCleanActivity() {

    }

    public static UnibotCleanActivity getInstance() {
        if (unibotCleanActivity == null) {
            unibotCleanActivity = new UnibotCleanActivity();
        }
        return unibotCleanActivity;
    }

    public void init(AndroidDriver driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public boolean showHaveMapActivity(){
        return Common.getInstance().showActivity(imageViewFullScreen);
    }

    public boolean showText(String strText) {
        boolean bResult = false;
        int iLoop = 0;
        while (true) {
            if (iLoop > 50) {
                break;
            }
            if (textViewStatusValue.getText().contains(strText)) {
                logger.info(textViewStatusValue.getText());
                Common.getInstance().waitForSecond(500);
            } else {
                logger.info(textViewStatusValue.getText());
                bResult = true;
                break;
            }
            iLoop++;
        }
        return bResult;
    }

    public void clickSetting(){
        imageBtnRight.click();
    }

    public void clickFullScreen(){
        imageViewFullScreen.click();
    }

    private boolean staticUITranslate(Map<String, String> tranMap) {
        Common.getInstance().waitForSecond(2000);
        String strLanguage = tranMap.get("language");
        boolean btextViewStatus = textViewStatus.getText().equalsIgnoreCase(tranMap.get("current_status"));
        if (!btextViewStatus) {
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "UnibotClean", textViewStatus.getText(),
                    tranMap.get("current_status"), "fail");
        }
        boolean btextViewStatusValue = textViewStatusValue.getText().equalsIgnoreCase(tranMap.get("deebot_charging"));
        if (!btextViewStatusValue) {
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "UnibotClean", textViewStatusValue.getText(),
                    tranMap.get("deebot_charging"), "fail");
        }
        boolean btextViewDeBattery = textViewDeBattery.getText().equalsIgnoreCase(tranMap.get("deebot_battery_desc"));
        if (!btextViewDeBattery) {
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "UnibotClean", textViewDeBattery.getText(),
                    tranMap.get("deebot_battery_desc"), "fail");
        }
        boolean btextViewDeAuto = textViewDeAuto.getText().equalsIgnoreCase(tranMap.get("dm88_ziDong"));
        if (!btextViewDeAuto) {
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "UnibotClean", textViewDeAuto.getText(),
                    tranMap.get("dm88_ziDong"), "fail");
        }
        boolean btextViewDeCharge = textViewDeCharge.getText().equalsIgnoreCase(tranMap.get("ibt_return_charge_text"));
        if (!btextViewDeCharge) {
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "UnibotClean", textViewDeCharge.getText(),
                    tranMap.get("ibt_return_charge_text"), "fail");
        }

        return btextViewStatus && btextViewStatusValue && btextViewDeBattery &&
                btextViewDeAuto && btextViewDeCharge;
    }

    public boolean translate(Map<String, String> tranMap){
        boolean bStatic = staticUITranslate(tranMap);
        boolean bcheckStatus = checkStatus(tranMap);
        return bStatic && bcheckStatus;
    }

    private boolean promptTitle_confirm_cancel(Map<String, String> tranMap){
        String strLanguage = tranMap.get("language");
        boolean bpromptTitle = promptTitle.getText().equalsIgnoreCase(tranMap.get("tips"));
        if (!bpromptTitle) {
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "UnibotClean", promptTitle.getText(),
                    tranMap.get("tips"), "fail");
        }
        boolean bpromptBtnCancel = promptBtnCancel.getText().equalsIgnoreCase(tranMap.get("cancel"));
        if (!bpromptBtnCancel) {
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "UnibotClean", promptBtnCancel.getText(),
                    tranMap.get("cancel"), "fail");
        }
        boolean bpromptBtnConfirm = promptBtnConfirm.getText().equalsIgnoreCase(tranMap.get("determine"));
        if (!bpromptBtnConfirm) {
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "UnibotClean", promptBtnConfirm.getText(),
                    tranMap.get("determine"), "fail");
        }
        return bpromptTitle && bpromptBtnCancel && bpromptBtnConfirm;
    }

    private boolean checkStatus(Map<String, String> tranMap){
        String strLanguage = tranMap.get("language");
        btnAuto.click();
        Common.getInstance().waitForSecond(500);
        //preparing to work
        String strValue = textViewStatusValue.getText();
        boolean btextViewStatusValue = textViewStatusValue.getText().equalsIgnoreCase(tranMap.get("zhunBeiZhong"));
        if (!btextViewStatusValue) {
            logger.error("Status preparing to work is not match!!!");
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "UnibotClean", textViewStatusValue.getText(),
                    tranMap.get("zhunBeiZhong"), "fail");
        }
        //wait for cleaning
        showText(strValue);
        boolean btextViewStatusValue1 = textViewStatusValue.getText().equalsIgnoreCase(tranMap.get("auto_qingSao"));
        if (!btextViewStatusValue1) {
            logger.error("Status auto cleaning is not match!!!");
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "UnibotClean", textViewStatusValue.getText(),
                    tranMap.get("auto_qingSao"), "fail");
        }
        //auto clean to charge
        btnCharge.click();
        Common.getInstance().waitForSecond(500);
        //prompt
        boolean bprompt = promptTitle_confirm_cancel(tranMap);
        String strTran = tranMap.get("zhuJi_dangQian_zhengZai");
        String strTranCleanCharge = String.format(strTran, tranMap.get("auto_qingSao"), tranMap.get("fanHui_chongDianZuo"));
        boolean bpromptMessage = promptMessage.getText().equalsIgnoreCase(strTranCleanCharge);
        if (!bpromptMessage) {
            logger.error("Prompt message is not match!!!");
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "UnibotClean", promptMessage.getText(),
                    strTranCleanCharge, "fail");
        }
        promptBtnConfirm.click();
        //wait for s
        Common.getInstance().waitForSecond(2500);
        boolean btextViewStatusValue2 = textViewStatusValue.getText().equalsIgnoreCase(tranMap.get("GO_CHARGING"));
        if (!btextViewStatusValue2) {
            logger.error("Status charging is not match!!!");
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "UnibotClean", textViewStatusValue.getText(),
                    tranMap.get("GO_CHARGING"), "fail");
        }
        //charging to auto clean
        btnAuto.click();
        boolean bprompt1 = promptTitle_confirm_cancel(tranMap);
        String strTranChargeClean = String.format(strTran, tranMap.get("GO_CHARGING"), tranMap.get("auto_qingSao"));
        boolean bpromptMessage1 = promptMessage.getText().equalsIgnoreCase(strTranChargeClean);
        if (!bpromptMessage1) {
            logger.error("Prompt message is not match!!!");
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "UnibotClean", promptMessage.getText(),
                    strTranChargeClean, "fail");
        }
        promptBtnConfirm.click();
        Common.getInstance().waitForSecond(2500);
        boolean btextViewStatusValue3 = textViewStatusValue.getText().equalsIgnoreCase(tranMap.get("auto_qingSao"));
        if (!btextViewStatusValue3) {
            logger.error("Status auto cleaning is not match!!!");
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "UnibotClean", textViewStatusValue.getText(),
                    tranMap.get("auto_qingSao"), "fail");
        }
        btnCharge.click();
        promptBtnConfirm.click();
        showText(textViewStatusValue.getText());
        return btextViewStatusValue && bprompt && bpromptMessage &&
                btextViewStatusValue1 && btextViewStatusValue2 &&
                btextViewStatusValue3 && bprompt1 &&  bpromptMessage1;
    }

}
