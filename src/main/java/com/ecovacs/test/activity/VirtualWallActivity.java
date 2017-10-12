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
public class VirtualWallActivity {
    private static VirtualWallActivity virtualWallActivity = null;

    @AndroidFindBy(id = "com.ecovacs.ecosphere.intl:id/map_tip")
    private MobileElement message = null;
    @AndroidFindBy(id = "com.ecovacs.ecosphere.intl:id/cancle_select")
    private MobileElement btnCancel = null;

    private VirtualWallActivity(){

    }

    public static VirtualWallActivity getInstance(){
        if (virtualWallActivity == null){
            virtualWallActivity = new VirtualWallActivity();
        }
        return virtualWallActivity;
    }

    public void init(AppiumDriver driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void clickCancel(){
        btnCancel.click();
    }

    public boolean staticUITranslation(Map<String, String> tranMap){
        boolean bmessage = message.getText().equalsIgnoreCase(tranMap.get("select_xuNiQiang"));
        if (!bmessage){
            TranslateErrorReport.getInstance().insetNewLine(
                    tranMap.get("language"), "VirtualWall", message.getText(),
                    tranMap.get("select_xuNiQiang"), "fail");
        }
        return bmessage;
    }




}
