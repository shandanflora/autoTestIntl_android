package com.ecovacs.test.activity;

import com.ecovacs.test.common.Common;
import com.ecovacs.test.common.PropertyData;
import com.ecovacs.test.common.TranslateErrorReport;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by ecosqa on 16/11/30.
 *
 */
public class RegisterActivity {
    private static RegisterActivity registerActivity = new RegisterActivity();
    private static Logger logger = LoggerFactory.getLogger(RegisterActivity.class);
    private AndroidDriver androidDriver = null;

    @FindBy(id = "com.ecovacs.ecosphere.intl:id/tv_zhongJian")
    private AndroidElement titleBarTitle = null;
    @FindBy(id = "com.ecovacs.ecosphere.intl:id/tv_youBian")
    private AndroidElement titleBarRight = null;
    @FindBy(xpath = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[2]/android.widget.TextView[1]")
    private AndroidElement line1Country_Region = null;
    @FindBy(xpath = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[3]/android.widget.TextView[1]")
    private AndroidElement line2Country_Region = null;
    @FindBy(xpath = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[4]/android.widget.TextView[1]")
    private AndroidElement line3Email = null;
    @FindBy(xpath = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[6]/android.widget.TextView[1]")
    private AndroidElement line5Password = null;
    @FindBy(id = "com.ecovacs.ecosphere.intl:id/passwordvisible")
    private AndroidElement passVisible = null;
    @FindBy(id = "com.ecovacs.ecosphere.intl:id/passwordrepeatvisible")
    private AndroidElement passRepeatVisible = null;
    @AndroidFindBy(id = "com.ecovacs.ecosphere.intl:id/btn_register")
    private AndroidElement btnRegister = null;
    @AndroidFindBy(id = "com.ecovacs.ecosphere.intl:id/rll_yuYan")
    private AndroidElement layOutCountry = null;
    @AndroidFindBy(id = "com.ecovacs.ecosphere.intl:id/edt_email")
    private AndroidElement editEmail = null;
    @AndroidFindBy(id = "com.ecovacs.ecosphere.intl:id/edt_pass")
    private AndroidElement editPassword = null;
    @AndroidFindBy(id = "com.ecovacs.ecosphere.intl:id/edt_pass_repeat")
    private AndroidElement editRePassword = null;
    @FindBy(id = "com.ecovacs.ecosphere.intl:id/tv_a")
    private AndroidElement textViewAccept = null;
    @AndroidFindBy(id = "com.ecovacs.ecosphere.intl:id/tv_xieYi")
    private AndroidElement textViewUserAgree = null;

    private RegisterActivity(){

    }

    public static RegisterActivity getInstance(){
        if(registerActivity == null){
            registerActivity = new RegisterActivity();
        }
        return registerActivity;
    }

    public void init(AndroidDriver driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        androidDriver = driver;
    }

    public boolean showRegisterActivity(){
        return Common.getInstance().showActivity(btnRegister);
    }

    public boolean fill_Screenshot_Click(String strCountry, String strEmail, String strPass){
        layOutCountry.click();
        logger.info("Click 'Country/Region'!!!'");
        Common.getInstance().waitForSecond(1000);
        if(!CountrySelectActivity.getInstance().selectCountry(strCountry)){
            return false;
        }
        if(!RegisterActivity.getInstance().showRegisterActivity()){
            logger.error("Can not find country--" + strCountry);
            Common.getInstance().goBack(androidDriver, 2);
            return false;
        }
        editEmail.sendKeys(strEmail);
        editPassword.sendKeys(strPass);
        editRePassword.sendKeys(strPass);
        logger.info("Filled all information of user");
        //hide keyboard
        Common.getInstance().goBack(androidDriver, 1);
        //screen shot user agreement
        clickAgreement();
        Common.getInstance().screenShot("UserAgree_" + strCountry + ".png", androidDriver);
        Common.getInstance().goBack(androidDriver, 1);
        logger.info("Finished to screen shot user agreement!!!");
        btnRegister.click();
        return true;
    }

    private void clickAgreement(){
        textViewUserAgree.click();
        Common.getInstance().waitForSecond(2500);
    }

    private boolean staticUITranslation(Map<String, String> tranMap){
        String strLanguage = tranMap.get("language");
        boolean bTitle = titleBarTitle.getText().equalsIgnoreCase(tranMap.get("register"));
        if(!bTitle){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "Register", titleBarTitle.getText(),
                    tranMap.get("register"), "fail");
        }
        boolean bRight = titleBarRight.getText().equalsIgnoreCase(tranMap.get("login"));
        if(!bRight){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "Register", titleBarRight.getText(),
                    tranMap.get("login"), "fail");
        }
        String strSource = tranMap.get("guojiadiqu")/*.replace(" ", "")*/;
        boolean bline1Country_Region = line1Country_Region.getText().equalsIgnoreCase(strSource);
        if(!bline1Country_Region){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "Register", line1Country_Region.getText(),
                    strSource, "fail");
        }
        boolean bline2Country_Region = line2Country_Region.getText().equalsIgnoreCase(strSource);
        if(!bline2Country_Region){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "Register", line2Country_Region.getText(),
                    strSource, "fail");
        }
        boolean bline3Email = line3Email.getText().equalsIgnoreCase(tranMap.get("email"));
        if(!bline3Email){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "Register", line3Email.getText(),
                    tranMap.get("email"), "fail");
        }
        editPassword.sendKeys("e");
        Common.getInstance().goBack(androidDriver, 1);
        passVisible.click();
        editPassword.clear();

        editRePassword.sendKeys("e");
        Common.getInstance().goBack(androidDriver, 1);
        passRepeatVisible.click();
        editRePassword.clear();
        Common.getInstance().goBack(androidDriver, 1);

        boolean beditEmail = editEmail.getText().equalsIgnoreCase(tranMap.get("Email"));
        if(!beditEmail){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "Register", editEmail.getText(),
                    tranMap.get("Email"), "fail");
        }
        boolean bline5Password = line5Password.getText().equalsIgnoreCase(tranMap.get("password"));
        if(!bline5Password){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "Register", line5Password.getText(),
                    tranMap.get("password"), "fail");
        }
        boolean beditPassword = editPassword.getText().equalsIgnoreCase(tranMap.get("email_pass_8_20"));
        logger.info("**register**" + editPassword.getText());
        if(!beditPassword){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "register", editPassword.getText(),
                    tranMap.get("email_pass_8_20"), "fail");
        }
        boolean beditRePassword = editRePassword.getText().equalsIgnoreCase(tranMap.get("zai_ci_shu_ru_pass"));
        if(!beditRePassword){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "Register", editRePassword.getText(),
                    tranMap.get("zai_ci_shu_ru_pass"), "fail");
        }
        boolean bbtnRegister = btnRegister.getText().equalsIgnoreCase(tranMap.get("register"));
        if(!bbtnRegister){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "Register", editRePassword.getText(),
                    tranMap.get("register"), "fail");
        }
        boolean btextViewAccept = textViewAccept.getText().equalsIgnoreCase(tranMap.get("my_read"));
        if(!btextViewAccept){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "Register", textViewAccept.getText(),
                    tranMap.get("my_read"), "fail");
        }
        boolean btextViewUserAgree = textViewUserAgree.getText().equalsIgnoreCase(tranMap.get("vip_register_http"));
        if(!btextViewUserAgree){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "Register", textViewUserAgree.getText(),
                    tranMap.get("vip_register_http"), "fail");
        }

        return bTitle && bRight && bline1Country_Region && bline2Country_Region &&
                bline3Email && beditEmail && bline5Password && beditPassword &&
                beditRePassword && bbtnRegister && btextViewAccept && btextViewUserAgree;
    }

    @FindBy(id = "com.ecovacs.ecosphere.intl:id/tv_message")
    private AndroidElement textViewMessage = null;

    private boolean emptyEmail(Map<String, String> tranMap){
        btnRegister.click();
        boolean btextViewMessage = textViewMessage.getText().equalsIgnoreCase(tranMap.get("email_null"));
        if(!btextViewMessage){
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "Register", textViewMessage.getText(),
                    tranMap.get("email_null"), "fail");
        }
        return btextViewMessage;
    }

    private boolean invalidEmail(Map<String, String> tranMap){
        editEmail.sendKeys("e");
        Common.getInstance().goBack(androidDriver, 1);
        btnRegister.click();
        boolean btextViewMessage = textViewMessage.getText().equalsIgnoreCase(tranMap.get("Email"));
        if(!btextViewMessage){
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "Register", textViewMessage.getText(),
                    tranMap.get("Email"), "fail");
        }
        return btextViewMessage;
    }

    private boolean emptyPassword(Map<String, String> tranMap){
        editEmail.clear();
        editEmail.sendKeys(PropertyData.getProperty("hotmail_email"));
        Common.getInstance().goBack(androidDriver, 1);
        btnRegister.click();
        boolean btextViewMessage = textViewMessage.getText().equalsIgnoreCase(tranMap.get("pass_null"));
        if(!btextViewMessage){
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "Register", textViewMessage.getText(),
                    tranMap.get("pass_null"), "fail");
        }
        return btextViewMessage;
    }

    private boolean invalidPassword(Map<String, String> tranMap){
        editPassword.sendKeys("1");
        Common.getInstance().goBack(androidDriver, 1);
        btnRegister.click();
        boolean btextViewMessage = textViewMessage.getText().equalsIgnoreCase(tranMap.get("pass_geShi"));
        if(!btextViewMessage){
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "Register", textViewMessage.getText(),
                    tranMap.get("pass_geShi"), "fail");
        }
        return btextViewMessage;
    }

    private boolean emptyRePassword(Map<String, String> tranMap){
        editPassword.clear();
        editPassword.sendKeys(PropertyData.getProperty("login_pass"));
        Common.getInstance().goBack(androidDriver, 1);
        btnRegister.click();
        boolean btextViewMessage = textViewMessage.getText().equalsIgnoreCase(tranMap.get("pass_null"));
        if(!btextViewMessage){
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "Register", textViewMessage.getText(),
                    tranMap.get("pass_null"), "fail");
        }
        return btextViewMessage;
    }

    private boolean invalidRePassword(Map<String, String> tranMap){
        editRePassword.sendKeys("12");
        Common.getInstance().goBack(androidDriver, 1);
        btnRegister.click();
        boolean btextViewMessage = textViewMessage.getText().equalsIgnoreCase(tranMap.get("pass_geShi"));
        if(!btextViewMessage){
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "Register", textViewMessage.getText(),
                    tranMap.get("pass_geShi"), "fail");
        }
        return btextViewMessage;
    }

    private boolean passwordNotMatch(Map<String, String> tranMap){
        editRePassword.clear();
        editRePassword.sendKeys("123456cd");
        Common.getInstance().goBack(androidDriver, 1);
        btnRegister.click();
        boolean btextViewMessage = textViewMessage.getText().equalsIgnoreCase(tranMap.get("password_two_no"));
        if(!btextViewMessage){
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "Register", textViewMessage.getText(),
                    tranMap.get("password_two_no"), "fail");
        }
        return btextViewMessage;
    }

    public boolean translate(Map<String, String> tranMap){
        //**must do case in order
        boolean bStaticUI = staticUITranslation(tranMap);
        boolean bEmptyEmail = emptyEmail(tranMap);
        boolean bInvalidEmail = invalidEmail(tranMap);
        boolean bbemptyPassword = emptyPassword(tranMap);
        boolean binvalidPassword = invalidPassword(tranMap);
        boolean bemptyRePassword = emptyRePassword(tranMap);
        boolean binvalidRePassword = invalidRePassword(tranMap);
        boolean bMatch = passwordNotMatch(tranMap);
        Common.getInstance().goBack(androidDriver, 1);
        return bStaticUI && bEmptyEmail && bInvalidEmail &&
                bbemptyPassword && binvalidPassword &&
                bemptyRePassword && binvalidRePassword && bMatch;
    }
}
