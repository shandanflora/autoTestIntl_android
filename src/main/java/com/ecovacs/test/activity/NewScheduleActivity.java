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
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import java.util.Map;

/**
 * Created by ecosqa on 17/2/18.
 * new schedule activity
 */
public class NewScheduleActivity {
    private static NewScheduleActivity newScheduleActivity = null;
    private AndroidDriver driver = null;
    //private static Logger logger = LoggerFactory.getLogger(NewScheduleActivity.class);

    @FindBy(id = "com.ecovacs.ecosphere.intl:id/titleContent")
    private AndroidElement title = null;
    @FindBy(id = "com.ecovacs.ecosphere.intl:id/right")
    private AndroidElement confirmAdd = null;
    @FindBy(id = "com.ecovacs.ecosphere.intl:id/title_back")
    private AndroidElement cancel = null;
    @FindBy(xpath = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.TextView[1]")
    private AndroidElement scheduleTask = null;
    @FindBy(id = "com.ecovacs.ecosphere.intl:id/tvSelectArea")
    private AndroidElement noSelected = null;
    @FindBy(id = "com.ecovacs.ecosphere.intl:id/tvAreaAll")
    private AndroidElement areaAll = null;
    @FindBy(id = "com.ecovacs.ecosphere.intl:id/tvAreaSingle")
    private AndroidElement areaSingle = null;
    @FindBy(xpath = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.TextView[3]")
    private AndroidElement setTime = null;
    @FindBy(xpath = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[2]/android.widget.TextView[1]")
    private AndroidElement startTime = null;
    @FindBy(xpath = " //android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.TextView[1]")
    private AndroidElement promptStartTitle = null;
    @FindBy(id = "com.ecovacs.ecosphere.intl:id/cancel_btn")
    private AndroidElement promptCancel = null;
    @FindBy(id = "com.ecovacs.ecosphere.intl:id/ok_btn")
    private AndroidElement promptOK = null;
    @FindBy(xpath = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[3]/android.widget.TextView[1]")
    private AndroidElement repeat = null;
    @FindBy(id = "com.ecovacs.ecosphere.intl:id/repeatText")
    private AndroidElement repeatValue = null;
    @FindBy(id = "com.ecovacs.ecosphere.intl:id/delete_appointment_tv")
    private MobileElement delSchedule = null;
    @FindBy(id = "com.ecovacs.ecosphere.intl:id/title")
    private MobileElement delPromptTitle = null;
    @FindBy(id = "com.ecovacs.ecosphere.intl:id/content")
    private MobileElement delPromptContent = null;
    @FindBy(id = "com.ecovacs.ecosphere.intl:id/cancel")
    private MobileElement delPromptCancel = null;
    @FindBy(id = "com.ecovacs.ecosphere.intl:id/sure")
    private MobileElement delPromptSure = null;
    @FindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.TimePicker[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.NumberPicker[1]")
    private MobileElement picker = null;

    private NewScheduleActivity(){

    }

    public static NewScheduleActivity getInstance(){
        if (newScheduleActivity == null){
            newScheduleActivity = new NewScheduleActivity();
        }
        return newScheduleActivity;
    }

    public void init(AndroidDriver driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        this.driver = driver;
    }

    private boolean staticUITranslation(Map<String, String> tranMap){
        String strLanguage = tranMap.get("language");
        boolean bTitle = title.getText().equalsIgnoreCase(tranMap.get("add_an_appointment"));
        if (!bTitle){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "NewSchedule", title.getText(),
                    tranMap.get("add_an_appointment"), "fail");
        }
        boolean bscheduleTask = scheduleTask.getText().equalsIgnoreCase(tranMap.get("appointment_task"));
        if (!bscheduleTask){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "NewSchedule", scheduleTask.getText(),
                    tranMap.get("appointment_task"), "fail");
        }
        boolean bnoSelected = noSelected.getText().equalsIgnoreCase(tranMap.get("choose_no_area"));
        if (!bnoSelected){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "NewSchedule", noSelected.getText(),
                    tranMap.get("choose_no_area"), "fail");
        }
        boolean bsetTime = setTime.getText().equalsIgnoreCase(tranMap.get("set_time"));
        if (!bsetTime){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "NewSchedule", setTime.getText(),
                    tranMap.get("set_time"), "fail");
        }
        boolean bstartTime = startTime.getText().equalsIgnoreCase(tranMap.get("start_time"));
        if (!bstartTime){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "NewSchedule", startTime.getText(),
                    tranMap.get("start_time"), "fail");
        }
        boolean brepeat = repeat.getText().equalsIgnoreCase(tranMap.get("appointment_frequency"));
        if (!brepeat){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "NewSchedule", repeat.getText(),
                    tranMap.get("appointment_frequency"), "fail");
        }

        String[] weekDays = {"zhouRi", "zhouYi", "zhouEr", "zhouSan", "zhouSi", "zhouWu", "zhouLiu"};
        int iIndex = Common.getInstance().getWeekIndex();
        boolean brepeatValue = repeatValue.getText().equalsIgnoreCase(tranMap.get(weekDays[iIndex]));
        if (!brepeatValue){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "NewSchedule", repeatValue.getText(),
                    tranMap.get(weekDays[iIndex]), "fail");
        }
        return bTitle && bscheduleTask && bnoSelected &&
                bsetTime && bstartTime && brepeat && brepeatValue;
    }

    private boolean appointTask(Map<String, String> tranMap){
        String strLanguage = tranMap.get("language");
        noSelected.click();
        boolean bareaAll= areaAll.getText().equalsIgnoreCase(tranMap.get("area_all_choose"));
        if (!bareaAll){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "NewSchedule", areaAll.getText(),
                    tranMap.get("area_all_choose"), "fail");
        }
        boolean bareaSingle= areaSingle.getText().equalsIgnoreCase(tranMap.get("area_single_choose"));
        if (!bareaSingle){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "NewSchedule", areaSingle.getText(),
                    tranMap.get("area_single_choose"), "fail");
        }
        noSelected.click();
        return bareaAll && bareaSingle;
    }

    private boolean startTimeTranslation(Map<String, String> tranMap){
        startTime.click();
        boolean bpromptStartTitle= promptStartTitle.getText().equalsIgnoreCase(tranMap.get("time_start"));
        if (!bpromptStartTitle){
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "NewSchedule", promptStartTitle.getText(),
                    tranMap.get(tranMap.get("time_start")), "fail");
        }
        promptCancel.click();
        return true;
    }

    public void clickRepeat(){
        repeat.click();
    }

    public void clickCancel(){
        cancel.click();
    }

    public boolean translateWeekend(Map<String, String> tranMap){
        RepetitionActivity.getInstance().clickWeekOfDate(Common.getInstance().getWeekIndex());
        RepetitionActivity.getInstance().clickWeekOfDate(0);
        RepetitionActivity.getInstance().clickWeekOfDate(6);
        RepetitionActivity.getInstance().clickBack();
        boolean brepeatValue= repeatValue.getText().equalsIgnoreCase(tranMap.get("weekday"));
        if (!brepeatValue){
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "NewSchedule", repeatValue.getText(),
                    tranMap.get("weekday"), "fail");
        }
        return brepeatValue;
    }

    public boolean translateWorkday(Map<String, String> tranMap){
        RepetitionActivity.getInstance().clickWeekOfDate(Common.getInstance().getWeekIndex());
        for(int i = 1; i < 6; i++){
            RepetitionActivity.getInstance().clickWeekOfDate(i);
        }
        RepetitionActivity.getInstance().clickBack();
        boolean brepeatValue = repeatValue.getText().equalsIgnoreCase(tranMap.get("workday"));
        if (!brepeatValue){
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "NewSchedule", repeatValue.getText(),
                    tranMap.get("workday"), "fail");
        }
        return brepeatValue;
    }

    public boolean translateEveryday(Map<String, String> tranMap){
        RepetitionActivity.getInstance().clickWeekOfDate(Common.getInstance().getWeekIndex());
        for(int i = 0; i < 7; i++){
            RepetitionActivity.getInstance().clickWeekOfDate(i);
        }
        RepetitionActivity.getInstance().clickBack();
        boolean brepeatValue= repeatValue.getText().equalsIgnoreCase(tranMap.get("everyday"));
        if (!brepeatValue){
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "NewSchedule", repeatValue.getText(),
                    tranMap.get("everyday"), "fail");
        }
        return brepeatValue;
    }

    public boolean translateSelectWeekOfDate(Map<String, String> tranMap){
        String[] weekDays = {"zhouRi", "zhouYi", "zhouEr", "zhouSan", "zhouSi", "zhouWu", "zhouLiu"};
        boolean brepeatValue[] = new boolean[7];
        //click week of date
        int iOldIndex = Common.getInstance().getWeekIndex();
        for(int i = 0; i < 7; i++){
            //click repeat
            repeat.click();
            //click pre date
            RepetitionActivity.getInstance().clickWeekOfDate(iOldIndex);
            RepetitionActivity.getInstance().clickWeekOfDate(i);
            RepetitionActivity.getInstance().clickBack();
            brepeatValue[i]= repeatValue.getText().equalsIgnoreCase(tranMap.get(weekDays[i]));
            if (!brepeatValue[i]){
                TranslateErrorReport.getInstance().insetNewLine(
                        tranMap.get("language"), "NewSchedule", repeatValue.getText(),
                        tranMap.get(weekDays[i]), "fail");
            }
            //recovery
            iOldIndex = i;
        }

        return brepeatValue[0] && brepeatValue[1] && brepeatValue[2] && brepeatValue[3] &&
                brepeatValue[4] && brepeatValue[5] && brepeatValue[6];
    }

    private void setStartTime(){
        Point point = picker.getLocation();
        Dimension dimension = picker.getSize();
        int iRectX = point.getX();
        int iRectY = point.getY();
        int iWidth = dimension.getWidth();
        int iHeight = dimension.getHeight();

        point.x = iRectX + iWidth/2;
        point.y = iRectY + iHeight/2;

        driver.swipe(point.x, point.y ,
                point.x, point.y - iHeight/3 - 1, 50);
    }

    public void addTime(){
        noSelected.click();
        areaAll.click();
        startTime.click();
        setStartTime();
        promptOK.click();
        confirmAdd.click();
    }

    public boolean delSchedule(Map<String, String> tranMap){
        String strLanguage = tranMap.get("language");
        boolean bdelSchedule = delSchedule.getText().equalsIgnoreCase(tranMap.get("delete_an_appointment"));
        if (!bdelSchedule){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "NewSchedule", delSchedule.getText(),
                    tranMap.get("delete_an_appointment"), "fail");
        }
        delSchedule.click();
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
        delPromptCancel.click();
        return bdelSchedule && bdelPromptTitle && bdelPromptContent &&
                bdelPromptCancel && bdelPromptSure;
    }

    public boolean translate(Map<String, String> tranMap){
        boolean bStatic = staticUITranslation(tranMap);
        boolean bappointTask = appointTask(tranMap);
        boolean bstartTimeTranslation = startTimeTranslation(tranMap);
        return bStatic && bappointTask && bstartTimeTranslation;
    }



}
