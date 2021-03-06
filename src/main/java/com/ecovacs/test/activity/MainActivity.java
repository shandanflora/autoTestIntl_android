package com.ecovacs.test.activity;

import com.ecovacs.test.common.Common;
import com.ecovacs.test.common.TranslateErrorReport;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * robot list activity
 * Created by ecosqa on 16/12/8.
 */
public class MainActivity {
    private static MainActivity mainActivity = null;
    private static Logger logger = LoggerFactory.getLogger(RegisterActivity.class);
    private AppiumDriver driver = null;

    private MainActivity(){

    }

    @iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[3]")
    @AndroidFindBy(id = "com.ecovacs.ecosphere.intl:id/right")
    private MobileElement textViewMore = null;
    @AndroidFindBy(xpath = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.TextView[1]")
    @iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAStaticText[1]")
    private MobileElement textViewFooter = null;
    @AndroidFindBy(id = "com.ecovacs.ecosphere.intl:id/head_tipsTextView")
    private MobileElement textViewHeader = null;
    @AndroidFindBy(id = "com.ecovacs.ecosphere.intl:id/listView_device")
    @iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIATableView[1]")
    private MobileElement listView = null;

    public static MainActivity getInstance(){
        if (mainActivity == null){
            mainActivity = new MainActivity();
        }
        return mainActivity;
    }

    public void init(AppiumDriver driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        this.driver = driver;
    }

    public boolean showActivity(){
       return Common.getInstance().showActivity(textViewFooter);
    }

    public void clickMore(){
        textViewMore.click();
    }

    public void clickDR95(){
        List<MobileElement> rlList = listView.findElements(By.className("android.widget.LinearLayout"));
        for (MobileElement rl:rlList){
            List<MobileElement> textList = rl.findElements(By.className("android.widget.TextView"));
            for (MobileElement text:textList){
                logger.info("child elment--" + text.getText());
                if (text.getText().contains("DR95")){
                    text.click();
                    break;
                }
            }
        }
    }

    private void pullDown(){
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;

        for(int i = 0; i < 10; i++)
        {
            driver.swipe(width/2, height/2, width/2, height * 3/4, 200);
        }
    }

    private boolean staticUITranslation(Map<String,String> tranMap){
        String strLanguage = tranMap.get("language");
        boolean btextViewFooter = textViewFooter.getText().equalsIgnoreCase(tranMap.get("pull_to_refresh_tap_label"));
        if(!btextViewFooter){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "Main", textViewFooter.getText(),
                    tranMap.get("pull_to_refresh_tap_label"), "fail");
        }
        boolean bOnline = true;
        boolean bOffline = true;
        List<MobileElement> rlList = listView.findElements(By.className("android.widget.LinearLayout"));
        for (MobileElement rl:rlList){
            List<MobileElement> lineLayouts = rl.findElements(By.className("android.widget.LinearLayout"));
            if (lineLayouts.size() == 0){
                continue;
            }
            MobileElement textView = lineLayouts.get(0).findElement(By.className("android.widget.TextView"));
            String strTextView = textView.getText();
            logger.info(strTextView);
            MobileElement textCheck = lineLayouts.get(0).findElement(By.className("android.widget.CheckBox"));
            String strCheck = textCheck.getText();
            logger.info(strCheck);
            if (strTextView.contains("DR95")){
                if (!strCheck.equalsIgnoreCase(tranMap.get("online"))){
                    TranslateErrorReport.getInstance().insetNewLine(
                            strLanguage, "Main", strCheck,
                            tranMap.get("online"), "fail");
                    bOnline = false;
                }
            }else if(strTextView.contains("A650")){
                if (!strCheck.equalsIgnoreCase(tranMap.get("offline"))){
                    TranslateErrorReport.getInstance().insetNewLine(
                            strLanguage, "Main", strCheck,
                            tranMap.get("offline"), "fail");
                    bOffline = false;
                }
            }
        }
        /*pullDown();
        boolean bHeadTips = textViewHeader.getText().equalsIgnoreCase(tranMap.get("pull_to_refresh_tap_label"));
        if (!bHeadTips){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "main", textViewHeader.getText(),
                    tranMap.get("pull_to_refresh_tap_label"), "fail");
        }*/
        return btextViewFooter && bOffline && bOnline;
    }

    public boolean translate(Map<String,String> tranMap){
        return staticUITranslation(tranMap);
    }
}
