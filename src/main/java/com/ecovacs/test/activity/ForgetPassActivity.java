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
 * Created by ecosqa on 16/12/7.
 * forget password activity
 */
public class ForgetPassActivity {
    private static ForgetPassActivity forgetPassActivity = null;
    private static Logger logger = LoggerFactory.getLogger(ForgetPassActivity.class);
    private AndroidDriver androidDriver = null;

    private ForgetPassActivity(){

    }

    @FindBy(id = "com.ecovacs.ecosphere.intl:id/tv_zhongJian")
    private AndroidElement titleForget = null;
    @FindBy(xpath = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[2]/android.widget.TextView[1]")
    private AndroidElement line1Country_Region = null;
    @FindBy(xpath = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[3]/android.widget.TextView[1]")
    private AndroidElement line2Country_Region = null;
    @FindBy(id = "com.ecovacs.ecosphere.intl:id/rll_yuYan")
    private AndroidElement eleCountry = null;
    @FindBy(id = "com.ecovacs.ecosphere.intl:id/edt_email")
    private AndroidElement editEmail = null;
    @FindBy(id = "com.ecovacs.ecosphere.intl:id/btn_send_email")
    private AndroidElement btnSendEmail = null;
    @FindBy(xpath = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[4]/android.widget.TextView[1]")
    private AndroidElement line3Email = null;
    @FindBy(id = "com.ecovacs.ecosphere.intl:id/tv_message")
    private AndroidElement textMessage = null;


    public static ForgetPassActivity getInstance(){
        if(forgetPassActivity == null){
            forgetPassActivity = new ForgetPassActivity();
        }
        return forgetPassActivity;
    }

    public void init(AndroidDriver driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        this.androidDriver = driver;
    }

    public boolean showActivity(){
        return Common.getInstance().showActivity(btnSendEmail);
    }

    public boolean sendEmail(String strCountry, String strEmail){
        eleCountry.click();
        if(!CountrySelectActivity.getInstance().selectCountry(strCountry)){
            logger.info("Select country failed!!!");
            return false;
        }
        editEmail.sendKeys(strEmail);
        Common.getInstance().goBack(androidDriver, 1);
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
        Common.getInstance().goBack(androidDriver, 1);
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

    @FindBy(id = "android:id/alertTitle")
    private AndroidElement propmtTitle = null;
    @FindBy(id = "android:id/message")
    private AndroidElement propmtMessage = null;

    private boolean notExistEmailprompt(Map<String, String> tranMap){
        editEmail.clear();
        editEmail.sendKeys("ecovacstest@hotmail.cim");
        Common.getInstance().goBack(androidDriver, 1);
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
        Common.getInstance().goBack(androidDriver, 2);
        return bstaticUI && bemptyEmail && binvalidEmail;
    }
}
