package com.ecovacs.test.activity;

import com.ecovacs.test.common.TranslateErrorReport;
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
public class AreaCleanActivity {
    private static AreaCleanActivity areaCleanActivity = null;

    @FindBy(id = "com.ecovacs.ecosphere.intl:id/map_tip")
    private AndroidElement message = null;
    @FindBy(id = "com.ecovacs.ecosphere.intl:id/rll_bianJi")
    private AndroidElement btnEdit = null;
    @FindBy(id = "com.ecovacs.ecosphere.intl:id/cancle_select")
    private AndroidElement btnCancel = null;
    @FindBy(id = "com.ecovacs.ecosphere.intl:id/confirm_select")
    private AndroidElement btnConfirmSelect = null;

    private AreaCleanActivity(){

    }

    public static AreaCleanActivity getInstance(){
        if (areaCleanActivity == null){
            areaCleanActivity = new AreaCleanActivity();
        }
        return areaCleanActivity;
    }

    public void init(AndroidDriver driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    private boolean staticUITranslation(Map<String, String> tranMap){
        boolean bmessage = message.getText().equalsIgnoreCase(tranMap.get("select_quYu_qingSao"));
        if (!bmessage){
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "AreaClean", message.getText(),
                    tranMap.get("select_quYu_qingSao"), "fail");
        }
        return bmessage;
    }

    private boolean edit(Map<String, String> tranMap){
        btnEdit.click();
        boolean bmessage = message.getText().equalsIgnoreCase(tranMap.get("map_quYu_rename"));
        if (!bmessage){
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "AreaClean", message.getText(),
                    tranMap.get("map_quYu_rename"), "fail");
        }
        btnEdit.click();
        return bmessage;
    }

    public boolean translate(Map<String, String> tranMap){
        boolean bStatic = staticUITranslation(tranMap);
        boolean bEdit = edit(tranMap);
        return bStatic && bEdit;
    }

    public void clickCancel(){
        btnCancel.click();
    }

    public void clickConfirm(){
        btnConfirmSelect.click();
    }
}
