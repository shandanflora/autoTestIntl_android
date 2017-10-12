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
 * Created by ecosqa on 17/2/14.
 *
 */
public class ChangePassActivity {
    private static ChangePassActivity changePassActivity = null;
    private AppiumDriver driver = null;

    @AndroidFindBy(id = "com.ecovacs.ecosphere.intl:id/tv_message")
    private MobileElement textMessage = null;
    @AndroidFindBy(id = "com.ecovacs.ecosphere.intl:id/tv_zhongJian")
    private MobileElement title = null;
    @AndroidFindBy(xpath = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[2]/android.widget.TextView[1]")
    private MobileElement line1_Old = null;
    @AndroidFindBy(id = "com.ecovacs.ecosphere.intl:id/edt_pass_old")
    private MobileElement editOldPass = null;
    @AndroidFindBy(xpath = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[4]/android.widget.TextView[1]")
    private MobileElement line3_new = null;
    @AndroidFindBy(id = "com.ecovacs.ecosphere.intl:id/edt_pass_new")
    private MobileElement editNewPass = null;
    @AndroidFindBy(id = "com.ecovacs.ecosphere.intl:id/tv_is_show_pass1")
    private MobileElement showPass = null;
    @AndroidFindBy(id = "com.ecovacs.ecosphere.intl:id/edt_pass_new_queRen")
    private MobileElement editRePass = null;
    @AndroidFindBy(id = "com.ecovacs.ecosphere.intl:id/tv_is_show_pass2")
    private MobileElement showRePass = null;
    @AndroidFindBy(id = "com.ecovacs.ecosphere.intl:id/btn_pass_update")
    private MobileElement btnSavePass = null;

    private ChangePassActivity(){

    }

    public static ChangePassActivity getInstance(){
        if (changePassActivity == null){
            changePassActivity = new ChangePassActivity();
        }
        return changePassActivity;
    }

    public void init(AppiumDriver driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        this.driver = driver;
    }

    public boolean showActivity(){
        return Common.getInstance().showActivity(title);
    }

    private boolean staticUITranslate(Map<String, String> tranMap){
        String strLanguage = tranMap.get("language");
        boolean btitle = title.getText().equalsIgnoreCase(tranMap.get("Change_the_password"));
        if(!btitle){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "ChangePassword", title.getText(),
                    tranMap.get("Change_the_password"), "fail");
        }
        boolean bline1_Old = line1_Old.getText().equalsIgnoreCase(tranMap.get("The_old_password"));
        if(!bline1_Old){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "ChangePassword", line1_Old.getText(),
                    tranMap.get("The_old_password"), "fail");
        }
        //can not catch text
        /*boolean beditOldPass = editOldPass.getText().equalsIgnoreCase(tranMap.get("input_pass_old"));
        if(!beditOldPass){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "ChangePassword", editOldPass.getText(),
                    tranMap.get("input_pass_old"), "fail");
        }*/
        boolean bline3_new = line3_new.getText().equalsIgnoreCase(tranMap.get("The_new_password"));
        if(!bline3_new){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "ChangePassword", line3_new.getText(),
                    tranMap.get("The_new_password"), "fail");
        }
        editNewPass.sendKeys("1");
        showPass.click();
        editNewPass.clear();
        //only for google piyi
        Common.getInstance().goBack(driver, 1);
        boolean beditNewPass = editNewPass.getText().equalsIgnoreCase(tranMap.get("email_pass_new_8_20"));
        if(!beditNewPass){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "ChangePassword", editNewPass.getText(),
                    tranMap.get("email_pass_new_8_20"), "fail");
        }
        editRePass.sendKeys("1");
        showRePass.click();
        editRePass.clear();
        //only for google piyi
        Common.getInstance().goBack(driver, 1);
        boolean beditRePass = editRePass.getText().equalsIgnoreCase(tranMap.get("Please_enter_a_new_password_again"));
        if(!beditRePass){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "ChangePassword", editRePass.getText(),
                    tranMap.get("Please_enter_a_new_password_again"), "fail");
        }
        boolean bbtnSavePass = btnSavePass.getText().equalsIgnoreCase(tranMap.get("Save_the_password"));
        if(!bbtnSavePass){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "ChangePassword", btnSavePass.getText(),
                    tranMap.get("Save_the_password"), "fail");
        }
        return btitle && bline1_Old && /*beditOldPass &&*/
                bline3_new && beditNewPass && beditRePass &&
                bbtnSavePass;
    }

    private boolean emptyOldPass(Map<String, String> tranMap){
        btnSavePass.click();
        boolean btextMessage = textMessage.getText().equalsIgnoreCase(tranMap.get("pass_old_null"));
        if (!btextMessage){
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "ChangePassword", textMessage.getText(),
                    tranMap.get("pass_old_null"), "fail");
        }
        return btextMessage;
    }

    private boolean invalidOldPass(Map<String, String> tranMap){
        editOldPass.sendKeys("1234");
        //only for google pinyi
        Common.getInstance().goBack(driver, 1);
        btnSavePass.click();
        boolean btextMessage = textMessage.getText().equalsIgnoreCase(tranMap.get("pass_old_null_repeat_geShi"));
        if (!btextMessage){
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "ChangePassword", textMessage.getText(),
                    tranMap.get("pass_old_null_repeat_geShi"), "fail");
        }
        return btextMessage;
    }

    private boolean emptyNewPass(Map<String, String> tranMap){
        editOldPass.clear();
        editOldPass.sendKeys("123456cd");
        Common.getInstance().goBack(driver, 1);
        btnSavePass.click();
        boolean btextMessage = textMessage.getText().equalsIgnoreCase(tranMap.get("pass_new_null"));
        if (!btextMessage){
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "ChangePassword", textMessage.getText(),
                    tranMap.get("pass_new_null"), "fail");
        }
        return btextMessage;
    }

    private boolean invalidNewPass(Map<String, String> tranMap){
        editNewPass.sendKeys("1234");
        //only for google piyi
        Common.getInstance().goBack(driver, 1);
        btnSavePass.click();
        boolean btextMessage = textMessage.getText().equalsIgnoreCase(tranMap.get("pass_geShi"));
        if (!btextMessage){
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "ChangePassword", textMessage.getText(),
                    tranMap.get("pass_geShi"), "fail");
        }
        return btextMessage;
    }

    private boolean emptyRePass(Map<String, String> tranMap){
        editNewPass.clear();
        editNewPass.sendKeys("123456fg");
        Common.getInstance().goBack(driver, 1);
        btnSavePass.click();
        boolean btextMessage = textMessage.getText().equalsIgnoreCase(tranMap.get("Password_cannot_be_empty_login"));
        if (!btextMessage){
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "ChangePassword", textMessage.getText(),
                    tranMap.get("Password_cannot_be_empty_login"), "fail");
        }
        return btextMessage;
    }

    private boolean invalidRePass(Map<String, String> tranMap){
        editRePass.sendKeys("1234");
        //google piyi
        Common.getInstance().goBack(driver, 1);
        btnSavePass.click();
        boolean btextMessage = textMessage.getText().equalsIgnoreCase(tranMap.get("pass_new_queRen_null_repeat_geShi"));
        if (!btextMessage){
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "ChangePassword", textMessage.getText(),
                    tranMap.get("pass_new_queRen_null_repeat_geShi"), "fail");
        }
        return btextMessage;
    }

    private boolean passNotMatch(Map<String, String> tranMap){
        editRePass.clear();
        editRePass.sendKeys("123456cd");
        Common.getInstance().goBack(driver, 1);
        btnSavePass.click();
        boolean btextMessage = textMessage.getText().equalsIgnoreCase(tranMap.get("pass_old_new_no"));
        if (!btextMessage){
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "ChangePassword", textMessage.getText(),
                    tranMap.get("pass_old_new_no"), "fail");
        }
        return btextMessage;
    }

    public boolean translate(Map<String, String> tranMap){
        boolean bstatic = staticUITranslate(tranMap);
        boolean bemptyOldPass = emptyOldPass(tranMap);
        boolean binvalidOldPass =  invalidOldPass(tranMap);
        boolean bemptyNewPass = emptyNewPass(tranMap);
        boolean bInvalidNewPass = invalidNewPass(tranMap);
        boolean bemptyRePass = emptyRePass(tranMap);
        boolean binvalidRePass = invalidRePass(tranMap);
        boolean bpassNotMatch = passNotMatch(tranMap);
        return bstatic && bemptyOldPass && binvalidOldPass &&
                bemptyNewPass && bInvalidNewPass && bemptyRePass &&
                binvalidRePass && bpassNotMatch;
    }

}
