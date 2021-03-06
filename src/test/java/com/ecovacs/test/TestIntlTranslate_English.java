package com.ecovacs.test;

import com.ecovacs.test.common.Common;
import com.ecovacs.test.common.PropertyData;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by ecosqa on 17/2/7.
 * test translate of intl app
 */
public class TestIntlTranslate_English {
    private AppiumDriver driver = null;

    @BeforeClass
    public void setUp(){
        driver = Common.getInstance().getDriver();
        if(driver == null){
            return;
        }
        HandleIntl.getInstance().init(driver);
        HandleIntl.getInstance().translate_init("English");
        HandleIntl.getInstance().translateErrorReport_init();
        //HandleIntl.getInstance().translate_init("French");
        //HandleIntl.getInstance().translate_init("Italian");
        //HandleIntl.getInstance().translate_init("Portuguese");
        //HandleIntl.getInstance().translate_init("Korean");
    }

    @AfterClass
    public void tearDown(){
        HandleIntl.getInstance().changeLanguage(PropertyData.getProperty("French"));
        driver.quit();
    }

    @Test
    public void translateWelcome(){
        Assert.assertTrue(HandleIntl.getInstance().translateWelcome());
    }

    @Test
    public void translateLogin(){
        Assert.assertTrue(HandleIntl.getInstance().translateLogin());
    }

    @Test
    public void translateForget(){
        Assert.assertTrue(HandleIntl.getInstance().translateForget());
    }

    @Test
    public void translateRegister(){
        Assert.assertTrue(HandleIntl.getInstance().translateRegister());
    }

    @Test
    public void translateMain(){
        Assert.assertTrue(HandleIntl.getInstance().translateMain());
    }

    @Test
    public void translateMore(){
        Assert.assertTrue(HandleIntl.getInstance().translateMore());
    }

    @Test
    public void translateChangePass(){
        Assert.assertTrue(HandleIntl.getInstance().translateChangePass());
    }

    @Test
    public void translateAbout(){
        Assert.assertTrue(HandleIntl.getInstance().translateAbout());
    }

    @Test
    public void translateUserAgree(){
        Assert.assertTrue(HandleIntl.getInstance().translateUserAgree());
    }

    @Test
    public void translateLanguage(){
        Assert.assertTrue(HandleIntl.getInstance().translateLanguage());
    }

    @Test
    public void translateUnibotClean(){
        Assert.assertTrue(HandleIntl.getInstance().translateUnibotClean());
    }

    @Test
    public void translateFullScreen(){
        Assert.assertTrue(HandleIntl.getInstance().translateFullScreen());
    }

    @Test
    public void translateArea(){
        Assert.assertTrue(HandleIntl.getInstance().translateArea());
    }

    @Test
    public void translateSpot(){
        Assert.assertTrue(HandleIntl.getInstance().translateSpot());
    }

    @Test
    public void translateVirtual(){
        Assert.assertTrue(HandleIntl.getInstance().translateVirtual());
    }

    @Test
    public void translateUnibotSetting(){
        Assert.assertTrue(HandleIntl.getInstance().translateUnibotSetting());
    }

    @Test
    public void translateWorkLog(){
        Assert.assertTrue(HandleIntl.getInstance().translateWorkLog());
    }

    @Test
    public void translateContinueClean(){
        Assert.assertTrue(HandleIntl.getInstance().translateContiuneClean());
    }

    @Test
    public void translateNoTimeSchedule(){
        Assert.assertTrue(HandleIntl.getInstance().translateTimeSchedule());
    }

    @Test
    public void translateNewSchedule(){
        Assert.assertTrue(HandleIntl.getInstance().translateNewTimeTranslation());
    }

    @Test
    public void translateRepetition(){
        Assert.assertTrue(HandleIntl.getInstance().translateRepetition());
    }

    @Test
    public void translateSelectWeekOfDate(){
        Assert.assertTrue(HandleIntl.getInstance().translateSelectWeekOfDate());
    }

    @Test
    public void translateSelectWeekend(){
        Assert.assertTrue(HandleIntl.getInstance().translateSelectWeekend());
    }

    @Test
    public void translateSelectWorkday(){
        Assert.assertTrue(HandleIntl.getInstance().translateSelectWorkday());
    }

    @Test
    public void translateSelectEveryday(){
        Assert.assertTrue(HandleIntl.getInstance().translateSelectEveryday());
    }

    @Test
    public void translateResetMap(){
        Assert.assertTrue(HandleIntl.getInstance().translateResetMap());
    }
    //
    @Test
    public void translateVoiceReport(){
        Assert.assertTrue(HandleIntl.getInstance().translateVoiceReport());
    }

    @Test
    public void translateFirmwareVer(){
        Assert.assertTrue(HandleIntl.getInstance().translateFirmVer());
    }

    @Test
    public void translateAddTimeSchedule(){
        Assert.assertTrue(HandleIntl.getInstance().translateAddTimeSchedule());
    }

    @Test
    public void translateDelSchedule_Edit(){
        Assert.assertTrue(HandleIntl.getInstance().translateDelSchedule_edit());
    }

    @Test
    public void translateDelSchedule_Swipe(){
        Assert.assertTrue(HandleIntl.getInstance().translateDelSchedule_swipe());
    }

}
