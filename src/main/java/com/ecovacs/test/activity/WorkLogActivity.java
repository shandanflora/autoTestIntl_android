package com.ecovacs.test.activity;

import com.ecovacs.test.common.TranslateErrorReport;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

import java.util.Map;

/**
 * Created by ecosqa on 17/2/17.
 * work log activity
 */
public class WorkLogActivity {
    private static WorkLogActivity workLogActivity = null;

    @AndroidFindBy(id = "com.ecovacs.ecosphere.intl:id/titleContent")
    private MobileElement title = null;
    @AndroidFindBy(id = "com.ecovacs.ecosphere.intl:id/tv_counttiem")
    private MobileElement timeTotal = null;
    @AndroidFindBy(id = "com.ecovacs.ecosphere.intl:id/tvTimeTips")
    private MobileElement timeTips = null;
    @AndroidFindBy(id = "com.ecovacs.ecosphere.intl:id/tips")
    private MobileElement cleanHistory = null;

    private WorkLogActivity(){

    }

    public static WorkLogActivity getInstance(){
        if(workLogActivity == null){
            workLogActivity = new WorkLogActivity();
        }
        return workLogActivity;
    }

    public void init(AppiumDriver driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    private boolean staticUITranslation(Map<String, String> tranMap){
        String strLanguage = tranMap.get("language");
        boolean btitle = title.getText().equalsIgnoreCase(tranMap.get("work_log"));
        if (!btitle){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "Work log", title.getText(),
                    tranMap.get("work_log"), "fail");
        }
        String strTotalTime = timeTotal.getText();
        boolean btimeTotal;
        if (strTotalTime.contains(tranMap.get("day"))){
            btimeTotal = strTotalTime.contains(tranMap.get("hour")) && strTotalTime.contains(tranMap.get("minute"));
        }else if (strTotalTime.contains(tranMap.get("hour"))){
            btimeTotal = strTotalTime.contains(tranMap.get("minute"));
        }else {
            btimeTotal = strTotalTime.contains(tranMap.get("minute"));
        }
        if (!btimeTotal){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "Work log", timeTotal.getText(),
                    tranMap.get("minute"), "fail");
        }
        boolean btimeTips = timeTips.getText().equalsIgnoreCase(tranMap.get("worked_time"));
        if (!btimeTips){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "Work log", timeTips.getText(),
                    tranMap.get("worked_time"), "fail");
        }
        boolean bcleanHistory = cleanHistory.getText().equalsIgnoreCase(tranMap.get("work_record"));
        if (!bcleanHistory){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "Work log", cleanHistory.getText(),
                    tranMap.get("work_record"), "fail");
        }
        return btitle && btimeTotal && btimeTips && bcleanHistory;
    }

    public boolean translate(Map<String, String> tranMap){
        return staticUITranslation(tranMap);
    }

}
