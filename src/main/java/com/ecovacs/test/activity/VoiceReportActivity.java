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
 * voice report activity
 */
public class VoiceReportActivity {
    private static VoiceReportActivity voiceReportActivity = null;

    @AndroidFindBy(id = "com.ecovacs.ecosphere.intl:id/tv_zhongJian")
    private MobileElement title = null;

    private VoiceReportActivity(){

    }

    public static VoiceReportActivity getInstance(){
        if (voiceReportActivity == null){
            voiceReportActivity = new VoiceReportActivity();
        }
        return voiceReportActivity;
    }

    public void init(AppiumDriver driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public boolean staticUITranslation(Map<String, String> tranMap){
        boolean bTitle = title.getText().equalsIgnoreCase(tranMap.get("zhuJiDuoYuYan"));
        if (!bTitle) {
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "VoiceReport", title.getText(),
                    tranMap.get("zhuJiDuoYuYan"), "fail");
        }
        return bTitle;
    }


}
