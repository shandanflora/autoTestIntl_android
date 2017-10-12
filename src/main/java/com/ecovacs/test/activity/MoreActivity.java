package com.ecovacs.test.activity;

import com.ecovacs.test.common.Common;
import com.ecovacs.test.common.TranslateErrorReport;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Map;

/**
 * Created by ecosqa on 16/12/8.
 * handle more activity
 */
public class MoreActivity {
    private static MoreActivity moreActivity = null;
    private AppiumDriver driver = null;

    private MoreActivity(){

    }

    @iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIATableView[1]")
    private MobileElement tableView = null;
    @AndroidFindBy(id = "com.ecovacs.ecosphere.intl:id/btn_exit")
    private MobileElement btnLogout = null;
    @AndroidFindBy(id = "com.ecovacs.ecosphere.intl:id/lly_multi_lingual")
    private MobileElement rowLanguage = null;
    @iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAStaticText[1]")
    @AndroidFindBy(id = "com.ecovacs.ecosphere.intl:id/tv_zhongJian")
    private MobileElement title = null;
    @AndroidFindBy(id = "com.ecovacs.ecosphere.intl:id/rll_bark")
    @iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[1]")
    private MobileElement back = null;
    @iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[1]")
    @AndroidFindBy(xpath = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.TextView[1]")
    private MobileElement textAccount = null;
    @iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[2]/UIAStaticText[1]")
    @AndroidFindBy(xpath = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[2]/android.widget.RelativeLayout[1]/android.widget.TextView[1]")
    private MobileElement textChange = null;
    @AndroidFindBy(xpath = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[3]/android.widget.RelativeLayout[1]/android.widget.TextView[1]")
    @iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[3]/UIAStaticText[1]")
    private MobileElement textLanguage = null;
    @iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[4]/UIAStaticText[1]")
    @AndroidFindBy(xpath = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[4]/android.widget.RelativeLayout[1]/android.widget.TextView[1]")
    private MobileElement textAbout = null;
    @AndroidFindBy(id = "android:id/alertTitle")
    @iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[4]/UIAAlert[1]/UIAScrollView[1]/UIAStaticText[2]")
    private MobileElement promptMessage = null;
    @AndroidFindBy(id = "android:id/button1")
    @iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[4]/UIAAlert[1]/UIACollectionView[1]/UIACollectionCell[2]/UIAButton[1]")
    private MobileElement btnConfirm = null;
    @AndroidFindBy(id = "android:id/button2")
    @iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[4]/UIAAlert[1]/UIACollectionView[1]/UIACollectionCell[1]/UIAButton[1]")
    private MobileElement btnCancel = null;

    public static MoreActivity getInstance(){
        if (moreActivity == null){
            moreActivity = new MoreActivity();
        }
        return moreActivity;
    }

    private Point getBtnLogOutPoint(){
        Point point = new Point(0, 0);
        Dimension dimension = new Dimension(0, 0);
        //Rectangle rect = null;
        int iCount = 0;
        List<MobileElement> cellList = null;
        while(iCount == 0) {
            cellList = tableView.findElementsByClassName("UIATableCell");
            iCount = cellList.size();
            //logger.info("The count of row in tableview is:" + Integer.toString(iCount));
        }

        for(int i = 0; i < iCount; i++){
            if(i == 3){
                dimension = cellList.get(i).getSize();
                point = cellList.get(i).getLocation();
            }
        }
        int iRectX = point.getX();
        int iRectY = point.getY();
        int iWidth = dimension.getWidth();
        int iHeight = dimension.getHeight();

        point.x = iRectX + iWidth/2;
        point.y = iRectY + iHeight*2 + 20;

        return point;
    }

    public void clickLogout(){
        if (Common.getInstance().isAndroid()){
            btnLogout.click();
            btnConfirm.click();
        }else {
            Point point = getBtnLogOutPoint();
            //click button Log out
            driver.tap(1, point.getX(), point.getY(), 0);
            //logger.info("Click button--Logout!!!");
            btnConfirm.click();
            //logger.info("Click button--Confirm Logout!!!");
            Common.getInstance().waitForSecond(500);
        }
    }

    public boolean showMoreActivity(){
        return Common.getInstance().showActivity(textAccount);
    }

    public void init(AppiumDriver driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        this.driver = driver;
    }

    public void clickBack(){
        back.click();
    }

    public void clickLanguage(){
        rowLanguage.click();
    }

    public void clickChangePass(){
        textChange.click();
    }

    public void clickAbout(){
        textAbout.click();
    }

    private boolean staticUiTranslation(Map<String, String> tranMap){
        String strLanguage = tranMap.get("language");
        boolean btitle = title.getText().equalsIgnoreCase(tranMap.get("load_more"));
        if(!btitle){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "More", title.getText(),
                    tranMap.get("load_more"), "fail");
        }
        boolean btextAccount = textAccount.getText().equalsIgnoreCase(tranMap.get("accounts"));
        if(!btextAccount){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "More", textAccount.getText(),
                    tranMap.get("accounts"), "fail");
        }
        boolean btextChange = textChange.getText().equalsIgnoreCase(tranMap.get("Change_the_password"));
        if(!btextChange){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "More", textChange.getText(),
                    tranMap.get("Change_the_password"), "fail");
        }
        boolean btextLanguage = textLanguage.getText().equalsIgnoreCase(tranMap.get("multi_lingual_a"));
        if(!btextLanguage){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "More", textLanguage.getText(),
                    tranMap.get("multi_lingual_a"), "fail");
        }
        boolean btextAbout = textAbout.getText().equalsIgnoreCase(tranMap.get("about"));
        if(!btextAbout){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "More", textAbout.getText(),
                    tranMap.get("about"), "fail");
        }
        boolean bbtnLogout = btnLogout.getText().equalsIgnoreCase(tranMap.get("Log_out"));
        if(!bbtnLogout){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "More", textAbout.getText(),
                    tranMap.get("Log_out"), "fail");
        }
        return btitle && btextAccount && btextChange && btextLanguage &&
                btextAbout && bbtnLogout;
    }

    private boolean translatePrompt(Map<String, String> tranMap){
        String strLanguage = tranMap.get("language");
        btnLogout.click();
        boolean bpromptMessage = promptMessage.getText().equalsIgnoreCase(tranMap.get("sure_quit"));
        if(!bpromptMessage){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "More", promptMessage.getText(),
                    tranMap.get("sure_quit"), "fail");
        }
        boolean bbtnCancel = btnCancel.getText().equalsIgnoreCase(tranMap.get("cancel"));
        if(!bbtnCancel){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "More", btnCancel.getText(),
                    tranMap.get("cancel"), "fail");
        }
        boolean bbtnConfirm = btnConfirm.getText().equalsIgnoreCase(tranMap.get("confirm"));
        if(!bbtnConfirm){
            TranslateErrorReport.getInstance().insetNewLine(
                    strLanguage, "More", btnConfirm.getText(),
                    tranMap.get("confirm"), "fail");
        }

        btnCancel.click();
        return  bpromptMessage && bbtnCancel &&
                bbtnConfirm;
    }

    public boolean translate(Map<String, String> tranMap){
        boolean bStatic = staticUiTranslation(tranMap);
        boolean bPrompt = translatePrompt(tranMap);
        return bStatic && bPrompt;
    }
}
