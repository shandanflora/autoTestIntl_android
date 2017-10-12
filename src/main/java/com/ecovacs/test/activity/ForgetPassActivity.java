package com.ecovacs.test.activity;

import com.ecovacs.test.common.Common;
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
 * Created by ecosqa on 16/12/7.
 * forget password activity
 */
public class ForgetPassActivity {
    private static ForgetPassActivity forgetPassActivity = null;
    private static Logger logger = LoggerFactory.getLogger(ForgetPassActivity.class);
    private AppiumDriver driver = null;

    private ForgetPassActivity(){

    }

    @AndroidFindBy(id = "com.ecovacs.ecosphere.intl:id/tv_zhongJian")
    private MobileElement titleForget = null;
    @AndroidFindBy(xpath = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[2]/android.widget.TextView[1]")
    @iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAStaticText[1]")
    private MobileElement line1Country_Region = null;
    @AndroidFindBy(xpath = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[3]/android.widget.TextView[1]")
    private MobileElement line2Country_Region = null;
    @iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[1]")
    @AndroidFindBy(id = "com.ecovacs.ecosphere.intl:id/rll_bark")
    private MobileElement back = null;
    @AndroidFindBy(id = "com.ecovacs.ecosphere.intl:id/rll_yuYan")
    @iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAButton[1]")
    private MobileElement eleCountry = null;
    @AndroidFindBy(id = "com.ecovacs.ecosphere.intl:id/edt_email")
    @iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIATextField[2]")
    private MobileElement editEmail = null;
    @AndroidFindBy(id = "com.ecovacs.ecosphere.intl:id/btn_send_email")
    @iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAButton[2]")
    private MobileElement btnSendEmail = null;
    @AndroidFindBy(xpath = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[4]/android.widget.TextView[1]")
    @iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAStaticText[3]")
    private MobileElement line3Email = null;
    @AndroidFindBy(id = "com.ecovacs.ecosphere.intl:id/tv_message")
    private MobileElement textMessage = null;


    public static ForgetPassActivity getInstance(){
        if(forgetPassActivity == null){
            forgetPassActivity = new ForgetPassActivity();
        }
        return forgetPassActivity;
    }

    public void init(AppiumDriver driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        this.driver = driver;
    }

    public boolean showActivity(){
        return Common.getInstance().showActivity(btnSendEmail);
    }

    public void clickBack(){
        back.click();
    }

    public boolean sendEmail(String strCountry, String strEmail){
        /*eleCountry.click();
        if(!CountrySelectActivity.getInstance().selectCountry(strCountry)){
            logger.info("Select country failed!!!");
            return false;
        }*/
        editEmail.sendKeys(strEmail);
        //hide keyboard
        if (Common.getInstance().isAndroid()){
            Common.getInstance().goBack(driver, 1);
        }else {
            line3Email.click();
        }
        btnSendEmail.click();
        logger.info("Finished to send verify email!!!");
        return true;
    }

    private boolean staticUI(Map<String, String> tranMap){
        boolean btitleForget = titleForget.getText().equalsIgnoreCase(tranMap.get("forget_password"));
        if(!btitleForget){
            logger.error("forget password activity--titleBar--forget--App--" + titleForget.getText());
            logger.error("forget password activity--titleBar--forget--Excel--" + tranMap.get("forget_password"));
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "Forgot password", titleForget.getText(),
                    tranMap.get("forget_password"), "fail");
        }
        String strSource = tranMap.get("guojiadiqu");
        boolean bline1Country_Region = line1Country_Region.getText().equalsIgnoreCase(strSource);
        if(!bline1Country_Region){
            logger.error("forget password activity--line1--Country_Region--App--" + line1Country_Region.getText());
            logger.error("forget password activity--line1--Country_Region--Excel--" + strSource);
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "Forgot password", line1Country_Region.getText(),
                    strSource, "fail");
        }
        boolean bline2Country_Region = line2Country_Region.getText().equalsIgnoreCase(strSource);
        if(!bline2Country_Region){
            logger.error("forget password activity--line2--Country_Region--App--" + line2Country_Region.getText());
            logger.error("forget password activity--line2--Country_Region--Excel--" + strSource);
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "Forgot password", line2Country_Region.getText(),
                    strSource, "fail");
        }
        boolean bline3Email = line3Email.getText().equalsIgnoreCase(tranMap.get("email"));
        if(!bline3Email){
            logger.error("forget password activity--line3--Email--App--" + line3Email.getText());
            logger.error("forget password activity--line3--Email--Excel--" + tranMap.get("email"));
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "Forgot password", line3Email.getText(),
                    tranMap.get("email"), "fail");
        }
        boolean beditEmail = editEmail.getText().equalsIgnoreCase(tranMap.get("Email"));
        if(!beditEmail){
            logger.error("forget password activity--edit--Email--App--" + editEmail.getText());
            logger.error("forget password activity--edit--Email--Excel--" + tranMap.get("Email"));
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "Forgot password", editEmail.getText(),
                    tranMap.get("Email"), "fail");
        }
        boolean bbtnSendEmail = btnSendEmail.getText().equalsIgnoreCase(tranMap.get("verification_email"));
        if(!bbtnSendEmail){
            logger.error("forget password activity--btn--SendEmail--App--" + btnSendEmail.getText());
            logger.error("forget password activity--btn--SendEmail--Excel--" + tranMap.get("verification_email"));
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "Forgot password", btnSendEmail.getText(),
                    tranMap.get("verification_email"), "fail");
        }

        return btitleForget && bline1Country_Region && bline2Country_Region
                && bline3Email && beditEmail && bbtnSendEmail;
    }

    private boolean emptyEmail(Map<String, String> tranMap){
        btnSendEmail.click();
        boolean btextMessage = textMessage.getText().equalsIgnoreCase(tranMap.get("email_null"));
        if(!btextMessage){
            logger.error("forget password activity--btn--SendEmail--App--" + textMessage.getText());
            logger.error("forget password activity--btn--SendEmail--Excel--" + tranMap.get("email_null"));
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "Forgot password", textMessage.getText(),
                    tranMap.get("email_null"), "fail");
        }
        return btextMessage;
    }

    private boolean invalidEmail(Map<String, String> tranMap){
        editEmail.clear();
        editEmail.sendKeys("e");
        if (Common.getInstance().isAndroid()){
            Common.getInstance().goBack(driver, 1);
        }
        btnSendEmail.click();
        boolean btextMessage = textMessage.getText().equalsIgnoreCase(tranMap.get("enter_email"));
        if(!btextMessage){
            logger.error("forget password activity--btn--SendEmail--App--" + textMessage.getText());
            logger.error("forget password activity--btn--SendEmail--Excel--" + tranMap.get("enter_email"));
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "Forgot password", textMessage.getText(),
                    tranMap.get("enter_email"), "fail");
        }
        return btextMessage;
    }

    @AndroidFindBy(id = "android:id/alertTitle")
    private MobileElement propmtTitle = null;
    @AndroidFindBy(id = "android:id/message")
    private MobileElement propmtMessage = null;

    private boolean notExistEmailprompt(Map<String, String> tranMap){
        editEmail.clear();
        editEmail.sendKeys("ecovacstest@hotmail.cim");
        if (Common.getInstance().isAndroid()){
            Common.getInstance().goBack(driver, 1);
        }
        btnSendEmail.click();
        String strTitle = propmtTitle.getText();
        boolean bpropmtTitle = strTitle.equalsIgnoreCase(tranMap.get("tips"));
        if(!bpropmtTitle){
            logger.error("forget password activity--prompt--tips--App--" + strTitle);
            logger.error("forget password activity--prompt--tips--Excel--" + tranMap.get("tips"));
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "Forgot password", strTitle,
                    tranMap.get("tips"), "fail");
        }
        String strMessage = propmtMessage.getText();
        boolean bpropmtMessage = strMessage.equalsIgnoreCase(tranMap.get("qingShaoDeng"));
        if(!bpropmtMessage){
            logger.error("forget password activity--promptMessage--wait--App--" + strMessage);
            logger.error("forget password activity--promptMessage--Excel--" + tranMap.get("qingShaoDeng"));
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "Forgot password", strMessage,
                    tranMap.get("qingShaoDeng"), "fail");
        }
        return bpropmtTitle && bpropmtMessage;
    }

    public boolean translate(Map<String, String> tranMap){
        boolean bstaticUI = staticUI(tranMap);
        boolean bemptyEmail = emptyEmail(tranMap);
        boolean binvalidEmail = invalidEmail(tranMap);
        //too fast show, can not catch element
        //boolean bnotExistEmailprompt = notExistEmailprompt(tranMap);
        if (Common.getInstance().isAndroid()){
            Common.getInstance().goBack(driver, 2);
        }
        return bstaticUI && bemptyEmail && binvalidEmail;
    }
}
