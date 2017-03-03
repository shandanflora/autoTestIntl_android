package com.ecovacs.test.activity;

import com.ecovacs.test.common.Common;
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
public class RepetitionActivity {
    private static RepetitionActivity repetitionActivity = null;

    @FindBy(id = "com.ecovacs.ecosphere.intl:id/titleContent")
    private AndroidElement title = null;
    @FindBy(id = "com.ecovacs.ecosphere.intl:id/title_back")
    private AndroidElement back = null;
    @FindBy(xpath = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[2]/android.widget.TextView[1]")
    private AndroidElement sunday = null;
    @FindBy(xpath = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[3]/android.widget.TextView[1]")
    private AndroidElement monday = null;
    @FindBy(xpath = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[4]/android.widget.TextView[1]")
    private AndroidElement tuesday = null;
    @FindBy(xpath = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[5]/android.widget.TextView[1]")
    private AndroidElement wednesday = null;
    @FindBy(xpath = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[6]/android.widget.TextView[1]")
    private AndroidElement thursday = null;
    @FindBy(xpath = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[7]/android.widget.TextView[1]")
    private AndroidElement friday = null;
    @FindBy(xpath = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[8]/android.widget.TextView[1]")
    private AndroidElement saturday = null;

    private RepetitionActivity(){

    }

    public static RepetitionActivity getInstance(){
        if (repetitionActivity == null){
            repetitionActivity = new RepetitionActivity();
        }
        return repetitionActivity;
    }

    public void init(AndroidDriver driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void clickSun(){
        sunday.click();
    }

    public void clickMon(){
        monday.click();
    }

    public void clickTues(){
        tuesday.click();
    }

    public void clickWed(){
        wednesday.click();
    }

    public void clickThurs(){
        thursday.click();
    }

    public void clickFri(){
        friday.click();
    }

    public void clickSat(){
        saturday.click();
    }

    public void clickWeekOfDate(int iIndex){
        switch (iIndex){
            case 0:
                clickSun();
                break;
            case 1:
                clickMon();
                break;
            case 2:
                clickTues();
                break;
            case 3:
                clickWed();
                break;
            case 4:
                clickThurs();
                break;
            case 5:
                clickFri();
                break;
            case 6:
                clickSat();
                break;
            default:
                break;
        }
    }

    public void clickBack(){
        back.click();
    }

    public boolean staticUITranslation(Map<String, String> tranMap){
        String strLanguage = tranMap.get("language");
        boolean bTitle = title.getText().equalsIgnoreCase(tranMap.get("repeat"));
        if (!bTitle){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "Repetition", title.getText(),
                    tranMap.get("repeat"), "fail");
        }
        boolean bsunday = sunday.getText().equalsIgnoreCase(tranMap.get("Every_Sunday"));
        if (!bsunday){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "Repetition", sunday.getText(),
                    tranMap.get("Every_Sunday"), "fail");
        }
        boolean bmonday = monday.getText().equalsIgnoreCase(tranMap.get("Every_Monday"));
        if (!bmonday){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "Repetition", monday.getText(),
                    tranMap.get("Every_Monday"), "fail");
        }
        boolean btuesday = tuesday.getText().equalsIgnoreCase(tranMap.get("Every_Tuesday"));
        if (!btuesday){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "Repetition", tuesday.getText(),
                    tranMap.get("Every_Tuesday"), "fail");
        }
        boolean bwednesday = wednesday.getText().equalsIgnoreCase(tranMap.get("Every_Wednesday"));
        if (!bwednesday){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "Repetition", wednesday.getText(),
                    tranMap.get("Every_Wednesday"), "fail");
        }
        boolean bthursday = thursday.getText().equalsIgnoreCase(tranMap.get("Every_Thursday"));
        if (!bthursday){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "Repetition", thursday.getText(),
                    tranMap.get("Every_Thursday"), "fail");
        }
        boolean bfriday = friday.getText().equalsIgnoreCase(tranMap.get("Every_Friday"));
        if (!bfriday){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "Repetition", friday.getText(),
                    tranMap.get("Every_Friday"), "fail");
        }
        boolean bsaturday = saturday.getText().equalsIgnoreCase(tranMap.get("Every_Saturday"));
        if (!bsaturday){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "Repetition", saturday.getText(),
                    tranMap.get("Every_Saturday"), "fail");
        }
        return bTitle && bsunday && bmonday && btuesday &&
                bwednesday && bthursday && bfriday && bsaturday;
    }


}
