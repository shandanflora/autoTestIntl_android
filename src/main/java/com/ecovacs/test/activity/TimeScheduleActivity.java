package com.ecovacs.test.activity;

import com.ecovacs.test.common.Common;
import com.ecovacs.test.common.TranslateErrorReport;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import java.util.Map;

/**
 * Created by ecosqa on 17/2/18.
 * time schedule activity
 */
public class TimeScheduleActivity {
    private static TimeScheduleActivity timeScheduleActivity = null;
    private AndroidDriver driver;

    @FindBy(id = "com.ecovacs.ecosphere.intl:id/titleContent")
    private AndroidElement title = null;
    @FindBy(xpath = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[2]/android.widget.TextView[1]")
    private AndroidElement emptyList = null;
    @FindBy(id = "com.ecovacs.ecosphere.intl:id/add_appointment")
    private AndroidElement btnAddTime = null;
    @FindBy(id = "com.ecovacs.ecosphere.intl:id/tvAreas")
    private AndroidElement cleanTask = null;
    @FindBy(id = "com.ecovacs.ecosphere.intl:id/replytime")
    private AndroidElement repeatTime = null;
    @FindBy(xpath = " //android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[2]/android.widget.LinearLayout[1]/android.widget.TextView[1]")
    private AndroidElement deleteTime = null;
    @FindBy(id = "com.ecovacs.ecosphere.intl:id/title")
    private MobileElement delPromptTitle = null;
    @FindBy(id = "com.ecovacs.ecosphere.intl:id/content")
    private MobileElement delPromptContent = null;
    @FindBy(id = "com.ecovacs.ecosphere.intl:id/cancel")
    private MobileElement delPromptCancel = null;
    @FindBy(id = "com.ecovacs.ecosphere.intl:id/sure")
    private MobileElement delPromptSure = null;

    private TimeScheduleActivity(){

    }

    public static TimeScheduleActivity getInstance(){
        if (timeScheduleActivity == null){
            timeScheduleActivity = new TimeScheduleActivity();
        }
        return timeScheduleActivity;
    }

    public void init(AndroidDriver driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        this.driver = driver;
    }

    public boolean showActivity(){
        return Common.getInstance().showActivity(btnAddTime);
    }

    private boolean staticUITranslate(Map<String, String> tranMap){
        String strLanguage = tranMap.get("language");
        boolean bTitle = title.getText().equalsIgnoreCase(tranMap.get("Time_to_make_an_appointment"));
        if (!bTitle){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "TimeSchedule", title.getText(),
                    tranMap.get("Time_to_make_an_appointment"), "fail");
        }
        boolean bemptyList = emptyList.getText().equalsIgnoreCase(tranMap.get("No_list_of_the_current_period"));
        if (!bemptyList){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "TimeSchedule", emptyList.getText(),
                    tranMap.get("No_list_of_the_current_period"), "fail");
        }
        boolean bbtnAddTime = btnAddTime.getText().equalsIgnoreCase(tranMap.get("add_an_appointment"));
        if (!bbtnAddTime){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "TimeSchedule", btnAddTime.getText(),
                    tranMap.get("add_an_appointment"), "fail");
        }
        return bTitle && bemptyList && bbtnAddTime;
    }

    public boolean translate(Map<String, String> tranMap){
        boolean bStatic = staticUITranslate(tranMap);
        return bStatic;
    }

    public void clickAddSchedule(){
        btnAddTime.click();
    }

    public boolean addTime(Map<String, String> tranMap){
        String[] weekDays = {"zhouRi", "zhouYi", "zhouEr", "zhouSan", "zhouSi", "zhouWu", "zhouLiu"};
        String strLanguage = tranMap.get("language");
        Common.getInstance().showActivity(cleanTask);
        boolean bcleanTask = cleanTask.getText().equalsIgnoreCase(tranMap.get("area_all_choose"));
        if(!bcleanTask){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "TimeSchedule", cleanTask.getText(),
                    tranMap.get("area_all_choose"), "fail");
        }
        int iIndex = Common.getInstance().getWeekIndex();
        boolean brepeatTime = repeatTime.getText().trim().equalsIgnoreCase(tranMap.get(weekDays[iIndex]));
        if(!brepeatTime){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "TimeSchedule", repeatTime.getText(),
                    tranMap.get(weekDays[iIndex]), "fail");
        }
        return bcleanTask && brepeatTime;
    }

    private void swipeNewTask(){
        //swipe
        Point point = cleanTask.getLocation();
        Dimension dimension = cleanTask.getSize();
        int iRectX = point.getX();
        int iRectY = point.getY();
        int iWidth = dimension.getWidth();
        int iHeight = dimension.getHeight();

        point.x = iRectX + iWidth/2;
        point.y = iRectY + iHeight/2;

        driver.swipe(point.x + iWidth/4, point.y ,
                point.x - iWidth/4, point.y, 100);
    }

    public boolean translateDel_Swipe(Map<String, String> tranMap){
        swipeNewTask();
        String strLanguage = tranMap.get("language");
        boolean bbtnDelete = deleteTime.getText().equalsIgnoreCase(tranMap.get("delete"));
        if (!bbtnDelete){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "TimeSchedule", deleteTime.getText(),
                    tranMap.get("delete"), "fail");
        }
        deleteTime.click();
        boolean bdelPromptTitle = delPromptTitle.getText().equalsIgnoreCase(tranMap.get("tips"));
        if (!bdelPromptTitle){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "NewSchedule", delPromptTitle.getText(),
                    tranMap.get("tips"), "fail");
        }
        boolean bdelPromptContent = delPromptContent.getText().equalsIgnoreCase(tranMap.get("del_tips"));
        if (!bdelPromptContent){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "NewSchedule", delPromptContent.getText(),
                    tranMap.get("del_tips"), "fail");
        }
        boolean bdelPromptCancel = delPromptCancel.getText().equalsIgnoreCase(tranMap.get("cancel"));
        if (!bdelPromptCancel){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "NewSchedule", delPromptCancel.getText(),
                    tranMap.get("cancel"), "fail");
        }
        boolean bdelPromptSure = delPromptSure.getText().equalsIgnoreCase(tranMap.get("determine"));
        if (!bdelPromptSure){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "NewSchedule", delPromptSure.getText(),
                    tranMap.get("determine"), "fail");
        }
        delPromptSure.click();
        return bbtnDelete;
    }

    public void enterEditSchedule(){
        cleanTask.click();
    }

}
