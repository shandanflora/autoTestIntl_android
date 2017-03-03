package com.ecovacs.test.activity;

import com.ecovacs.test.common.Common;
import com.ecovacs.test.common.PropertyData;
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
 * login activity
 */
public class LoginActivity {
    private static LoginActivity loginActivity = null;
    private static Logger logger = LoggerFactory.getLogger(LoginActivity.class);
    private AndroidDriver androidDriver = null;

    private LoginActivity(){}

    @FindBy(id = "com.ecovacs.ecosphere.intl:id/tv_zhongJian")
    private AndroidElement titleLogin = null;
    @FindBy(id = "com.ecovacs.ecosphere.intl:id/tv_youBian")
    private AndroidElement headRight = null;
    @FindBy(xpath = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.TextView[1]")
    private AndroidElement line1Country_Region = null;
    @FindBy(xpath = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[2]/android.widget.RelativeLayout[1]/android.widget.TextView[1]")
    private AndroidElement line2Country_Region = null;
    @FindBy(xpath = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[3]/android.widget.RelativeLayout[1]/android.widget.TextView[1]")
    private AndroidElement line3Email_Pass = null;
    @FindBy(id = "com.ecovacs.ecosphere.intl:id/lly_select_country")
    private AndroidElement eleCountry = null;
    @FindBy(id = "com.ecovacs.ecosphere.intl:id/edt_email")
    private AndroidElement editEmail = null;
    @FindBy(id = "com.ecovacs.ecosphere.intl:id/edt_pass")
    private AndroidElement editPass = null;
    @FindBy(id = "com.ecovacs.ecosphere.intl:id/tv_is_show_pass")
    private AndroidElement textViewShowPass = null;
    @FindBy(id = "com.ecovacs.ecosphere.intl:id/btn_login")
    private AndroidElement btnLogin = null;
    @FindBy(id = "com.ecovacs.ecosphere.intl:id/tv_pass_recover")
    private AndroidElement textViewForget = null;
    @FindBy(id = "com.ecovacs.ecosphere.intl:id/tv_message")
    private AndroidElement textViewMessage = null;

    public static LoginActivity getInstance(){
        if(loginActivity == null){
            loginActivity = new LoginActivity();
        }
        return loginActivity;
    }

    public void init(AndroidDriver driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        this.androidDriver = driver;
    }

    public void clickForgetPass(){
        textViewForget.click();
    }

    public boolean showLoginActivity(){
        return Common.getInstance().showActivity(btnLogin);
    }

    public void clickCountry(){
        eleCountry.click();
    }

    public void fillInfoAndClick(String strEmail, String strPass){
        editEmail.sendKeys(strEmail);
        editPass.sendKeys(strPass);
        btnLogin.click();
    }

    private boolean staticUI(Map<String, String> tranMap){
        boolean bTitleLogin = titleLogin.getText().equalsIgnoreCase(tranMap.get("login"));
        if(!bTitleLogin){
            logger.error("login activity--titleBar--login--App--" + titleLogin.getText());
            logger.error("login activity--titleBar--login--Excel--" + tranMap.get("login"));
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "login", titleLogin.getText(),
                    tranMap.get("login"), "fail");
        }
        boolean bheadRight = headRight.getText().equalsIgnoreCase(tranMap.get("register"));
        if(!bTitleLogin){
            logger.error("login activity--titleBar--register--App--" + headRight.getText());
            logger.error("login activity--titleBar--register--Excel--" + tranMap.get("register"));
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "login", headRight.getText(),
                    tranMap.get("register"), "fail");
        }
        String strSource = tranMap.get("guojiadiqu")/*.replace(" ", "")*/;
        boolean bline1Country_Region = line1Country_Region.getText().equalsIgnoreCase(tranMap.get("guojiadiqu"));
        if(!bTitleLogin){
            logger.error("login activity--line1--Country_Region--App--" + line1Country_Region.getText());
            logger.error("login activity--line1--Country_Region--Excel--" + strSource);
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "login", line1Country_Region.getText(),
                    strSource, "fail");
        }
        boolean bline2Country_Region = line2Country_Region.getText().equalsIgnoreCase(strSource);
        if(!bline2Country_Region){
            logger.error("login activity--line2--Country_Region--App--" + line2Country_Region.getText());
            logger.error("login activity--line2--Country_Region--Excel--" + strSource);
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "login", line2Country_Region.getText(),
                    strSource, "fail");
        }
        boolean bline3Email_Pass = line3Email_Pass.getText().equalsIgnoreCase(tranMap.get("email_pass"));
        if(!bline3Email_Pass){
            logger.error("login activity--line3--Email_Pass--App--" + line3Email_Pass.getText());
            logger.error("login activity--line3--Email_Pass--Excel--" + tranMap.get("email_pass"));
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "login", line3Email_Pass.getText(),
                    tranMap.get("email_pass"), "fail");
        }
        boolean beditEmail = editEmail.getText().equalsIgnoreCase(tranMap.get("Email"));
        if(!beditEmail){
            logger.error("login activity--edit--Email--App--" + editEmail.getText());
            logger.error("login activity--edit--Email--Excel--" + tranMap.get("Email"));
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "login", editEmail.getText(),
                    tranMap.get("Email"), "fail");
        }
        editPass.sendKeys("e");
        Common.getInstance().goBack(androidDriver, 1);
        textViewShowPass.click();
        editPass.clear();
        boolean beditPass = editPass.getText().equalsIgnoreCase(tranMap.get("email_pass_8_20"));
        if(!beditPass){
            logger.error("login activity--edit--email_pass_8_20--App--" + editPass.getText());
            logger.error("login activity--edit--email_pass_8_20--Excel--" + tranMap.get("email_pass_8_20"));
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "login", editPass.getText(),
                    tranMap.get("email_pass_8_20"), "fail");
        }
        boolean bbtnLogin = btnLogin.getText().equalsIgnoreCase(tranMap.get("login"));
        if(!bbtnLogin){
            logger.error("login activity--btn--login--App--" + btnLogin.getText());
            logger.error("login activity--btn--login--Excel--" + tranMap.get("login"));
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "login", btnLogin.getText(),
                    tranMap.get("login"), "fail");
        }
        boolean btextViewForget = textViewForget.getText().equalsIgnoreCase(tranMap.get("forget_password"));
        if(!btextViewForget){
            logger.error("login activity--text--forget--App--" + textViewForget.getText());
            logger.error("login activity--text--forget--Excel--" + tranMap.get("forget_password"));
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "login", textViewForget.getText(),
                    tranMap.get("forget_password"), "fail");
        }
        return bTitleLogin && bheadRight && bline1Country_Region &&
                bline2Country_Region && bline3Email_Pass && beditPass &&
                bbtnLogin && btextViewForget;
    }

    private boolean emptyEmail(Map<String, String> tranMap){
        btnLogin.click();
        boolean btextViewMessage = textViewMessage.getText().equalsIgnoreCase(tranMap.get("email_null"));
        if(!btextViewMessage){
            logger.error("login activity--text--emptyEmail--App--" + textViewMessage.getText());
            logger.error("login activity--text--emptyEmail--Excel--" + tranMap.get("email_null"));
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "login", textViewMessage.getText(),
                    tranMap.get("email_null"), "fail");
        }
        return btextViewMessage;
    }

    private boolean invalidEmail(Map<String, String> tranMap){
        editEmail.sendKeys("ecovacs@hotmail");
        btnLogin.click();
        boolean btextViewMessage = textViewMessage.getText().equalsIgnoreCase(tranMap.get("Email"));
        if(!btextViewMessage){
            logger.error("login activity--text--invalidEmail--App--" + textViewMessage.getText());
            logger.error("login activity--text--invalidEmail--Excel--" + tranMap.get("Email"));
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "login", textViewMessage.getText(),
                    tranMap.get("Email"), "fail");
        }
        return btextViewMessage;
    }

    private boolean emptyPassword(Map<String, String> tranMap){
        editEmail.clear();
        editEmail.sendKeys(PropertyData.getProperty("hotmail_email"));
        editPass.clear();
        btnLogin.click();
        boolean btextViewMessage = textViewMessage.getText().equalsIgnoreCase(tranMap.get("pass_null"));
        if(!btextViewMessage){
            logger.error("login activity--text--emptyPassword--App--" + textViewMessage.getText());
            logger.error("login activity--text--emptyPassword--Excel--" + tranMap.get("pass_null"));
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "login", textViewMessage.getText(),
                    tranMap.get("pass_null"), "fail");
        }
        return btextViewMessage;

    }

    private boolean invalidPassword(Map<String, String> tranMap){
        editEmail.clear();
        editEmail.sendKeys(PropertyData.getProperty("hotmail_email"));
        editPass.clear();
        editPass.sendKeys("1234");
        Common.getInstance().goBack(androidDriver, 1);
        btnLogin.click();
        boolean btextViewMessage = textViewMessage.getText().equalsIgnoreCase(tranMap.get("pass_geShi"));
        if(!btextViewMessage){
            logger.error("login activity--text--invalidPassword--App--" + textViewMessage.getText());
            logger.error("login activity--text--invalidPassword--Excel--" + tranMap.get("pass_geShi"));
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "login", textViewMessage.getText(),
                    tranMap.get("pass_geShi"), "fail");
        }
        return btextViewMessage;
    }

    public boolean translate(Map<String, String> tranMap){
        boolean bResult = staticUI(tranMap);
        boolean bResult1 = emptyEmail(tranMap);
        boolean bResult2 = invalidEmail(tranMap);
        boolean bResult3 = emptyPassword(tranMap);
        boolean bResult4 = invalidPassword(tranMap);

        return bResult && bResult1 && bResult2 && bResult3 && bResult4;
    }



}
