package com.ecovacs.test.activity;

import com.ecovacs.test.common.Common;
import com.ecovacs.test.common.PropertyData;
import com.ecovacs.test.common.TranslateErrorReport;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
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
    private AppiumDriver driver = null;

    @AndroidFindBy(id = "com.ecovacs.ecosphere.intl:id/tv_zhongJian")
    private MobileElement titleBarTitle = null;
    @AndroidFindBy(id = "com.ecovacs.ecosphere.intl:id/tv_youBian")
    private MobileElement titleBarRight = null;
    @iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[1]")
    @AndroidFindBy(id = "com.ecovacs.ecosphere.intl:id/rll_bark")
    private MobileElement back = null;
    @AndroidFindBy(xpath = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[2]/android.widget.TextView[1]")
    private MobileElement line1Country_Region = null;
    @AndroidFindBy(xpath = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[3]/android.widget.TextView[1]")
    private MobileElement line2Country_Region = null;
    @AndroidFindBy(xpath = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[4]/android.widget.TextView[1]")
    @iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAStaticText[3]")
    private MobileElement line3Email = null;
    @AndroidFindBy(xpath = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[6]/android.widget.TextView[1]")
    private MobileElement line5Password = null;
    @AndroidFindBy(id = "com.ecovacs.ecosphere.intl:id/passwordvisible")
    private MobileElement passVisible = null;
    @AndroidFindBy(id = "com.ecovacs.ecosphere.intl:id/passwordrepeatvisible")
    private MobileElement passRepeatVisible = null;
    @AndroidFindBy(id = "com.ecovacs.ecosphere.intl:id/btn_register")
    @iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAButton[4]")
    private MobileElement btnRegister = null;
    @AndroidFindBy(id = "com.ecovacs.ecosphere.intl:id/rll_yuYan")
    @iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAButton[1]")
    private MobileElement layOutCountry = null;
    @AndroidFindBy(id = "com.ecovacs.ecosphere.intl:id/edt_email")
    @iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIATextField[2]")
    private MobileElement editEmail = null;
    @AndroidFindBy(id = "com.ecovacs.ecosphere.intl:id/edt_pass")
    @iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]")
    private MobileElement editPassword = null;
    @AndroidFindBy(id = "com.ecovacs.ecosphere.intl:id/edt_pass_repeat")
    @iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[2]")
    private MobileElement editRePassword = null;
    @AndroidFindBy(id = "com.ecovacs.ecosphere.intl:id/tv_a")
    private MobileElement textViewAccept = null;
    @AndroidFindBy(id = "com.ecovacs.ecosphere.intl:id/tv_xieYi")
    private MobileElement textViewUserAgree = null;
    @AndroidFindBy(id = "com.ecovacs.ecosphere.intl:id/tv_message")
    private MobileElement textViewMessage = null;

    private RegisterActivity(){

    }

    public static RegisterActivity getInstance(){
        if(registerActivity == null){
            registerActivity = new RegisterActivity();
        }
        return registerActivity;
    }

    public void init(AppiumDriver driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        this.driver = driver;
    }

    public boolean showRegisterActivity(){
        return Common.getInstance().showActivity(btnRegister);
    }

    public void clickBack(){
        back.click();
    }

    public boolean fill_Screenshot_Click(String strCountry, String strEmail, String strPass){
        layOutCountry.click();
        logger.info("Click 'Country/Region'!!!'");
        Common.getInstance().waitForSecond(1000);
        if(!CountrySelectActivity.getInstance().selectCountry(strCountry)){
            return false;
        }
        //Can not find country
        if(!RegisterActivity.getInstance().showRegisterActivity()){
            logger.error("Can not find country--" + strCountry);
            return false;
        }
        editEmail.sendKeys(strEmail);
        if(!Common.getInstance().isAndroid()){
            //hide keyboard
            line3Email.click();
        }
        editPassword.sendKeys(strPass);
        if(!Common.getInstance().isAndroid()){
            //hide keyboard
            line3Email.click();
        }
        editRePassword.sendKeys(strPass);
        logger.info("Filled all information of user");
        //hide keyboard
        if (Common.getInstance().isAndroid()){
            Common.getInstance().goBack(driver, 1);
            //screen shot user agreement
            clickAgreement();
            if(strCountry.contains(" ")){
                logger.info(strCountry);
                strCountry = strCountry.replaceAll(" ", "_");
                logger.info(strCountry);
            }
            Common.getInstance().screenShot("UserAgree_" + strCountry + ".png", driver);
            Common.getInstance().goBack(driver, 1);
            logger.info("Finished to screen shot user agreement!!!");
        }else {
            //hide keyboard
            line3Email.click();
        }
        //click register
        btnRegister.click();
        //wait for end prompt;
        Common.getInstance().waitForSecond(500);
        try {
            if(textViewMessage.isDisplayed()){
                logger.error("error message--" + textViewMessage.getText());
                return false;
            }
        }catch (Exception e){
            return true;
        }
        return true;
    }

    private void clickAgreement(){
        textViewUserAgree.click();
        UserAgreeActivity.getInstance().showActivity();
        Common.getInstance().waitForSecond(2000);
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
        if (Common.getInstance().isAndroid()){
            Common.getInstance().goBack(driver, 1);
        }
        passVisible.click();
        editPassword.clear();

        editRePassword.sendKeys("e");
        if (Common.getInstance().isAndroid()){
            Common.getInstance().goBack(driver, 1);
        }
        passRepeatVisible.click();
        editRePassword.clear();
        if (Common.getInstance().isAndroid()){
            Common.getInstance().goBack(driver, 1);
        }

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
        if (Common.getInstance().isAndroid()){
            Common.getInstance().goBack(driver, 1);
        }
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
        if (Common.getInstance().isAndroid()){
            Common.getInstance().goBack(driver, 1);
        }
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
        if (Common.getInstance().isAndroid()){
            Common.getInstance().goBack(driver, 1);
        }
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
        if (Common.getInstance().isAndroid()){
            Common.getInstance().goBack(driver, 1);
        }
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
        if (Common.getInstance().isAndroid()){
            Common.getInstance().goBack(driver, 1);
        }
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
        if (Common.getInstance().isAndroid()){
            Common.getInstance().goBack(driver, 1);
        }
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
        if (Common.getInstance().isAndroid()){
            Common.getInstance().goBack(driver, 1);
        }
        return bStaticUI && bEmptyEmail && bInvalidEmail &&
                bbemptyPassword && binvalidPassword &&
                bemptyRePassword && binvalidRePassword && bMatch;
    }
}
