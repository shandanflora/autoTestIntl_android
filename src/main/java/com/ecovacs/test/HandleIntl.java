package com.ecovacs.test;

import com.ecovacs.test.activity.*;
import com.ecovacs.test.common.*;
import io.appium.java_client.android.AndroidDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Created by ecosqa on 16/11/30.
 * handle international app
 */
class HandleIntl {
    private static Logger logger = LoggerFactory.getLogger(HandleIntl.class);
    private static HandleIntl handleIntl = null;
    private AndroidDriver androidDriver = null;
    private Map<String, String> languageMap = null;

    private HandleIntl(){

    }

    public static HandleIntl getInstance(){
        if(handleIntl == null){
            handleIntl = new HandleIntl();
        }
        return handleIntl;
    }

    void initAppium(){
        AndroidDriver driver = Common.getInstance().getDriver();
        if(driver == null){
            return;
        }
        HandleIntl.getInstance().init(driver);
    }

    public void init(AndroidDriver driver){
        androidDriver = driver;
        AboutActivity.getInstance().init(androidDriver);
        AreaCleanActivity.getInstance().init(androidDriver);
        ChangePassActivity.getInstance().init(androidDriver);
        ContinueCleanActivity.getInstance().init(androidDriver);
        CountrySelectActivity.getInstance().init(androidDriver);
        FirmVerActivity.getInstance().init(androidDriver);
        ForgetPassActivity.getInstance().init(androidDriver);
        FullScreenActivity.getInstance().init(androidDriver);
        LanguageActivity.getInstance().init(androidDriver);
        LoginActivity.getInstance().init(androidDriver);
        MainActivity.getInstance().init(androidDriver);
        MoreActivity.getInstance().init(androidDriver);
        NewScheduleActivity.getInstance().init(androidDriver);
        RegisterActivity.getInstance().init(androidDriver);
        RepetitionActivity.getInstance().init(androidDriver);
        ResetMapActivity.getInstance().init(androidDriver);
        RetrievePassActivity.getInstance().init(androidDriver);
        SettingActivity.getInstance().init(androidDriver);
        SpotCleanActivity.getInstance().init(androidDriver);
        TimeScheduleActivity.getInstance().init(androidDriver);
        UnibotCleanActivity.getInstance().init(androidDriver);
        UserAgreeActivity.getInstance().init(androidDriver);
        VirtualWallActivity.getInstance().init(androidDriver);
        VoiceReportActivity.getInstance().init(androidDriver);
        WelcomeActivity.getInstance().init(androidDriver);
        WorkLogActivity.getInstance().init(androidDriver);
    }

    boolean enterWelcomeActivity(){
        if (!WelcomeActivity.getInstance().showWelcomeActivity()) {
            logger.error("Can not show welcome activity!!!");
            return false;
        }
        return true;
    }

    private boolean enterRegisterActivity(){
        WelcomeActivity.getInstance().clickRegister();
        if (!RegisterActivity.getInstance().showRegisterActivity()) {
            logger.error("Can not show register activity!!!");
            return false;
        }
        logger.info("Show register activity!!!");
        return true;
    }

    /**
     * verify email
     * @param strJar jar file
     * @param strCountry country
     * @param strMail email
     * @param strType handle type, ex:Register
     */

    private void verifyEmail(String strJar, String strCountry, String strMail, String strType){
        String strPath = RegisterActivity.class.getResource("/").getPath();
        strPath = strPath + "../../" + strJar;
        File fileApp = new File(strPath);
        logger.info(strPath);
        Common.getInstance().executeCommand("java -jar " + fileApp.getName() + " " + strCountry + " " + strMail + " " + strType);
        logger.info("********exec command finished!!!");
    }

    /*public void goBack(int iNum){
        Common.getInstance().goBack(androidDriver, iNum);
    }*/

    boolean registerAndLogin(String strCountry, String strEmailType, String strEmail, String strPass){
        //register
        if(!enterRegisterActivity()){
            return false;
        }
        if (!RegisterActivity.getInstance().fill_Screenshot_Click(strCountry, strEmail, strPass)) {
            logger.error("Register failed!!! country--" + strCountry);
            return false;
        }
        if(!RetrievePassActivity.getInstance().ShowResisterConfirmActivity()){
            Common.getInstance().setFailType(Common.FailType.ALREADY_REGISTER);
            Common.getInstance().goBack(androidDriver, 1);
            logger.error("Not show Retrieve confirm activity!!!");
            return false;
        }
        logger.info("Show active email activity!!!");
        //verify email
        verifyEmail("VerifyEmail.one-jar.jar", strCountry, strEmailType, "Register");
        //check--login with new password
        RetrievePassActivity.getInstance().clickLogin();
        if (!loginWithoutWelcome(strCountry, strEmail, strPass)) {
            logger.info("Login failed after forget password country- " + strCountry);
            return false;
        }
        if (!logout()) {
            logger.info("Logout failed after forget password country- " + strCountry);
            Common.getInstance().goBack(androidDriver, 1);
            return false;
        }
        return true;
    }

    private boolean enterLoginAcivity(){
        WelcomeActivity.getInstance().clickLogin();
        if (!LoginActivity.getInstance().showLoginActivity()) {
            logger.info("Not show login activity!!!");
            return false;
        }
        logger.info("Show login activity!!!");
        return true;
    }

    boolean forgetPassword(String strCountry, String strEmailType, String strEmail, String strPass){
        if(!enterLoginAcivity()){
            return false;
        }
        LoginActivity.getInstance().clickForgetPass();
        logger.info("Click forget password!!!");
        if (!ForgetPassActivity.getInstance().showActivity()) {
            Common.getInstance().goBack(androidDriver, 1);
            logger.error("Not show forget password activity!!!");
            return false;
        }
        if (!ForgetPassActivity.getInstance().sendEmail(strCountry, strEmail)) {
            Common.getInstance().goBack(androidDriver, 2);
            Common.getInstance().setFailType(Common.FailType.NOT_REGISTER);
            logger.error("Not show retrieve password activity!!!");
            return false;
        }
        logger.info("Click send verify email!!!");
        if (!RetrievePassActivity.getInstance().showRetrieveConfirmActivity()) {
            logger.error("Not show retrieve password activity!!!");
            Common.getInstance().goBack(androidDriver, 2);
            return false;
            //invalid email
        }
        logger.info("Show retrieve password activity!!!");
        verifyEmail("VerifyEmail.one-jar.jar", strCountry, strEmailType, "DoFindPass");
        //check--login with new password
        RetrievePassActivity.getInstance().clickLogin();
        if (!loginWithoutWelcome(strCountry, strEmail, strPass)) {
            logger.info("Login failed after forget password country- " + strCountry);
            Common.getInstance().goBack(androidDriver, 1);
            return false;
        }
        if (!logout()) {
            logger.info("Logout failed after forget password country- " + strCountry);
            return false;
        }
        return true;
    }

    private boolean loginWithoutWelcome(String strCountry, String strEmail, String strPass){
        if(!LoginActivity.getInstance().showLoginActivity()){
            logger.error("Can not show login activity!!!");
            return false;
        }
        logger.info("Show login activity!!!");
        LoginActivity.getInstance().clickCountry();
        if(!CountrySelectActivity.getInstance().selectCountry(strCountry)){
            return false;
        }
        logger.info("Finished to select country!!!");
        LoginActivity.getInstance().fillInfoAndClick(strEmail, strPass);
        logger.info("Finished to click login!!!");
        if(!MainActivity.getInstance().showActivity()){
            logger.info("Can not show robot list activity!!!");
            return false;
        }
        logger.info("login successfully country- " + strCountry);
        return true;
    }

    private boolean login(String strCountry, String strEmail, String strPass){
        if(!WelcomeActivity.getInstance().showWelcomeActivity()){
            logger.error("Can not show welcome activity!!!");
            return false;
        }
        WelcomeActivity.getInstance().clickLogin();
        logger.info("Click login in welcome activity!!!");
        return loginWithoutWelcome(strCountry, strEmail, strPass);
    }

    private boolean logout(){
        MainActivity.getInstance().clickMore();
        if(!MoreActivity.getInstance().showMoreActivity()){
            logger.info("Can not show more activity!!!");
            return false;
        }
        MoreActivity.getInstance().clickLogout();
        MoreActivity.getInstance().clickConfirm();
        return WelcomeActivity.getInstance().showWelcomeActivity();
    }

    /*public boolean loginAndLogout(String strCountry, String strEmail, String strPass){
        if(!login(strCountry, strEmail, strPass)){
            logger.info("login failed country- " + strCountry);
            return false;
        }
        if(!logout()){
            logger.info("logout successfully country- " + strCountry);
            return false;
        }
        return true;
    }*/

    public void changeLanguage(String strLanguage){
        //return deebot clean
        Common.getInstance().goBack(androidDriver, 1);
        //return main
        Common.getInstance().goBack(androidDriver, 1);
        /*if(!login("Japan", PropertyData.getProperty("hotmail_email"), PropertyData.getProperty("login_pass"))){
            logger.error("login failed!!!");
            return;
        }*/
        MainActivity.getInstance().showActivity();
        MainActivity.getInstance().clickMore();
        MoreActivity.getInstance().clickLanguage();
        LanguageActivity.getInstance().selectLanguage(strLanguage);
        //return main
        Common.getInstance().goBack(androidDriver, 1);
        if(!logout()){
            logger.info("logout failed!!!");
        }
    }

    void translateErrorReport_init(){
        List<String> list = JsonParse.getJsonParse().readDataFromJson("country.json", "sheets");
        TranslateErrorReport.getInstance().init(list);
    }

    void translate_init(String strColName){
        Map<String, String> tranMap = TranslateIntl.getInstance().readExcel("Translate.xlsx", strColName);
        if(tranMap.isEmpty()){
            logger.error("The language map is empty!!!");
            return;
        }
        languageMap = tranMap;
    }

    boolean translateWelcome(){
        //changeLanguage("English");
        return WelcomeActivity.getInstance().translate(languageMap);
    }

    boolean translateLogin(){
        WelcomeActivity.getInstance().clickLogin();
        LoginActivity.getInstance().showLoginActivity();
        return LoginActivity.getInstance().translate(languageMap);
    }

    boolean translateRegister(){
        WelcomeActivity.getInstance().clickRegister();
        RegisterActivity.getInstance().showRegisterActivity();
        return RegisterActivity.getInstance().translate(languageMap);
    }

    boolean translateForget(){
        //*****del********
        //WelcomeActivity.getInstance().clickLogin();
        //LoginActivity.getInstance().showLoginActivity();
        //**************
        LoginActivity.getInstance().clickForgetPass();
        ForgetPassActivity.getInstance().showActivity();
        return ForgetPassActivity.getInstance().translate(languageMap);
    }

    boolean translateMain(){
        login("Japan", PropertyData.getProperty("hotmail_email"), PropertyData.getProperty("login_pass"));
        //
        MainActivity.getInstance().showActivity();
        //
        return MainActivity.getInstance().translate(languageMap);
    }

    boolean translateMore(){
        MainActivity.getInstance().clickMore();
        boolean bTranlate = MoreActivity.getInstance().translate(languageMap);
        return bTranlate;
    }

    boolean translateChangePass(){
        //MainActivity.getInstance().clickMore();
        MoreActivity.getInstance().clickChangePass();
        ChangePassActivity.getInstance().showActivity();
        boolean bTrans =  ChangePassActivity.getInstance().translate(languageMap);
        Common.getInstance().goBack(androidDriver, 2);
        return bTrans;
    }

    boolean translateAbout(){
        //MainActivity.getInstance().clickMore();
        MoreActivity.getInstance().clickAbout();
        boolean bTrans =  AboutActivity.getInstance().staticUITranslate(languageMap);
        Common.getInstance().goBack(androidDriver, 1);
        return bTrans;
    }

    boolean translateUserAgree(){
        //MainActivity.getInstance().clickMore();
        MoreActivity.getInstance().clickAbout();
        AboutActivity.getInstance().clickUserAgree();
        boolean bTrans =  UserAgreeActivity.getInstance().staticUITranslate(languageMap);
        //back to more
        Common.getInstance().goBack(androidDriver, 2);
        return bTrans;
    }

    boolean translateLanguage(){
        //MainActivity.getInstance().clickMore();
        MoreActivity.getInstance().clickLanguage();
        boolean bLang = LanguageActivity.getInstance().staticUITranslation(languageMap);
        LanguageActivity.getInstance().clickBack();
        return bLang;

    }

    boolean translateUnibotClean(){
        Common.getInstance().waitForSecond(1500);
        MainActivity.getInstance().clickDR95();
        UnibotCleanActivity.getInstance().showHaveMapActivity();
        //UnibotCleanActivity.getInstance().showText("-");
        return UnibotCleanActivity.getInstance().translate(languageMap);
    }

    boolean translateUnibotSetting(){
        //will delete
        //MainActivity.getInstance().clickDR95();
        //
        UnibotCleanActivity.getInstance().clickSetting();
        return SettingActivity.getInstance().staticUiTranslate(languageMap);
    }

    boolean translateWorkLog(){
        //after check return to setting
        SettingActivity.getInstance().clickWorkLog();
        boolean bResult = WorkLogActivity.getInstance().translate(languageMap);
        Common.getInstance().goBack(androidDriver, 1);
        return bResult;
    }

    boolean translateContiuneClean(){
        //after check return to setting
        SettingActivity.getInstance().clickContinuedClean();
        boolean bResult = ContinueCleanActivity.getInstance().translate(languageMap);
        Common.getInstance().goBack(androidDriver, 1);
        return bResult;

    }

    boolean translateVoiceReport(){
        //after check return to setting
        SettingActivity.getInstance().clickLanguage();
        boolean bResult = VoiceReportActivity.getInstance().staticUITranslation(languageMap);
        Common.getInstance().goBack(androidDriver, 1);
        return bResult;
    }

    boolean translateFirmVer(){
        SettingActivity.getInstance().clickFirmware();
        boolean bResult = FirmVerActivity.getInstance().staticUITranslation(languageMap);
        Common.getInstance().goBack(androidDriver, 1);
        return bResult;
    }

    boolean translateResetMap(){
        SettingActivity.getInstance().clickReset();
        boolean bResult = ResetMapActivity.getInstance().translate(languageMap);
        Common.getInstance().goBack(androidDriver, 1);
        return bResult;
    }

    boolean translateNewTimeTranslation(){
        SettingActivity.getInstance().clickTimeSchedule();
        TimeScheduleActivity.getInstance().showActivity();
        TimeScheduleActivity.getInstance().clickAddSchedule();
        boolean bResult = NewScheduleActivity.getInstance().translate(languageMap);
        Common.getInstance().goBack(androidDriver, 1);
        return bResult;
    }

    boolean translateAddTimeSchedule(){
        TimeScheduleActivity.getInstance().clickAddSchedule();
        NewScheduleActivity.getInstance().addTime();
        return TimeScheduleActivity.getInstance().addTime(languageMap);
    }

    boolean translateDelSchedule_edit(){
        TimeScheduleActivity.getInstance().enterEditSchedule();
        boolean bRes = NewScheduleActivity.getInstance().delSchedule(languageMap);
        NewScheduleActivity.getInstance().clickCancel();
        return bRes;
    }

    boolean translateDelSchedule_swipe(){
        boolean bRes = TimeScheduleActivity.getInstance().translateDel_Swipe(languageMap);
        //return settings
        Common.getInstance().goBack(androidDriver, 1);
        return bRes;
    }


    boolean translateRepetition(){
        TimeScheduleActivity.getInstance().clickAddSchedule();
        NewScheduleActivity.getInstance().clickRepeat();
        boolean bRes = RepetitionActivity.getInstance().staticUITranslation(languageMap);
        RepetitionActivity.getInstance().clickBack();
        NewScheduleActivity.getInstance().clickCancel();
        return bRes;
    }

    boolean translateSelectWeekOfDate(){
        TimeScheduleActivity.getInstance().clickAddSchedule();
        boolean bResult = NewScheduleActivity.getInstance().translateSelectWeekOfDate(languageMap);
        NewScheduleActivity.getInstance().clickCancel();
        return bResult;
    }

    boolean translateSelectWeekend(){
        TimeScheduleActivity.getInstance().clickAddSchedule();
        NewScheduleActivity.getInstance().clickRepeat();
        boolean bResult = NewScheduleActivity.getInstance().translateWeekend(languageMap);
        NewScheduleActivity.getInstance().clickCancel();
        return bResult;
    }

    boolean translateSelectWorkday(){
        TimeScheduleActivity.getInstance().clickAddSchedule();
        NewScheduleActivity.getInstance().clickRepeat();
        boolean bResult = NewScheduleActivity.getInstance().translateWorkday(languageMap);
        NewScheduleActivity.getInstance().clickCancel();
        return bResult;
    }

    boolean translateSelectEveryday(){
        TimeScheduleActivity.getInstance().clickAddSchedule();
        NewScheduleActivity.getInstance().clickRepeat();
        boolean bResult = NewScheduleActivity.getInstance().translateEveryday(languageMap);
        NewScheduleActivity.getInstance().clickCancel();
        return bResult;
    }

    boolean translateTimeSchedule(){
        SettingActivity.getInstance().clickTimeSchedule();
        TimeScheduleActivity.getInstance().showActivity();
        boolean bResult = TimeScheduleActivity.getInstance().translate(languageMap);
        Common.getInstance().goBack(androidDriver, 1);
        return bResult;
    }

    boolean translateFullScreen(){
        UnibotCleanActivity.getInstance().clickFullScreen();
        boolean bRes = FullScreenActivity.getInstance().staticUITranslation(languageMap);
        //Common.getInstance().goBack(androidDriver, 1);
        return bRes;
    }

    boolean translateSpot(){
        FullScreenActivity.getInstance().clickSpot();
        boolean bRes = SpotCleanActivity.getInstance().translate(languageMap);
        SpotCleanActivity.getInstance().clickCancel();
        return bRes;
    }

    boolean translateArea(){
        FullScreenActivity.getInstance().clickArea();
        boolean bRes = AreaCleanActivity.getInstance().translate(languageMap);
        AreaCleanActivity.getInstance().clickCancel();
        return bRes;
    }

    boolean translateVirtual(){
        FullScreenActivity.getInstance().clickVirtual();
        boolean bRes = VirtualWallActivity.getInstance().staticUITranslation(languageMap);
        VirtualWallActivity.getInstance().clickCancel();
        //return unibot clean
        FullScreenActivity.getInstance().clickBack();
        return bRes;
    }





}
