package com.ecovacs.test.activity;

import com.ecovacs.test.common.TranslateErrorReport;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

import java.util.Map;

/**
 * Created by ecosqa on 17/2/18.
 *
 */
public class RepetitionActivity {
    private static RepetitionActivity repetitionActivity = null;

    @AndroidFindBy(id = "com.ecovacs.ecosphere.intl:id/titleContent")
    private MobileElement title = null;
    @AndroidFindBy(id = "com.ecovacs.ecosphere.intl:id/title_back")
    private MobileElement back = null;
    @AndroidFindBy(xpath = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[2]/android.widget.TextView[1]")
    private MobileElement sunday = null;
    @AndroidFindBy(xpath = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[3]/android.widget.TextView[1]")
    private MobileElement monday = null;
    @AndroidFindBy(xpath = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[4]/android.widget.TextView[1]")
    private MobileElement tuesday = null;
    @AndroidFindBy(xpath = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[5]/android.widget.TextView[1]")
    private MobileElement wednesday = null;
    @AndroidFindBy(xpath = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[6]/android.widget.TextView[1]")
    private MobileElement thursday = null;
    @AndroidFindBy(xpath = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[7]/android.widget.TextView[1]")
    private MobileElement friday = null;
    @AndroidFindBy(xpath = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[8]/android.widget.TextView[1]")
    private MobileElement saturday = null;

    private RepetitionActivity(){

    }

    public static RepetitionActivity getInstance(){
        if (repetitionActivity == null){
            repetitionActivity = new RepetitionActivity();
        }
        return repetitionActivity;
    }

    public void init(AppiumDriver driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    private void clickSun(){
        sunday.click();
    }

    private void clickMon(){
        monday.click();
    }

    private void clickTues(){
        tuesday.click();
    }

    private void clickWed(){
        wednesday.click();
    }

    private void clickThurs(){
        thursday.click();
    }

    private void clickFri(){
        friday.click();
    }

    private void clickSat(){
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
