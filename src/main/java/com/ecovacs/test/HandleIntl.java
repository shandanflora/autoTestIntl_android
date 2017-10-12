package com.ecovacs.test;

import com.ecovacs.test.activity.*;
import com.ecovacs.test.common.*;
import com.ecovacs.test.verifyEmail.ImapMailBox;
import io.appium.java_client.AppiumDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by ecosqa on 16/11/30.
 * handle international app
 */
public class HandleIntl {
    private static Logger logger = LoggerFactory.getLogger(HandleIntl.class);
    private static HandleIntl handleIntl = null;
    private AppiumDriver appiumDriver = null;
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
        AppiumDriver driver = Common.getInstance().getDriver();
        if(driver == null){
            return;
        }
        HandleIntl.getInstance().init(driver);
    }

    public void init(AppiumDriver driver){
        appiumDriver = driver;
        AboutActivity.getInstance().init(appiumDriver);
        AreaCleanActivity.getInstance().init(appiumDriver);
        ChangePassActivity.getInstance().init(appiumDriver);
        ContinueCleanActivity.getInstance().init(appiumDriver);
        CountrySelectActivity.getInstance().init(appiumDriver);
        FirmVerActivity.getInstance().init(appiumDriver);
        ForgetPassActivity.getInstance().init(appiumDriver);
        FullScreenActivity.getInstance().init(appiumDriver);
        LanguageActivity.getInstance().init(appiumDriver);
        LoginActivity.getInstance().init(appiumDriver);
        MainActivity.getInstance().init(appiumDriver);
        MoreActivity.getInstance().init(appiumDriver);
        NewScheduleActivity.getInstance().init(appiumDriver);
        RegisterActivity.getInstance().init(appiumDriver);
        RepetitionActivity.getInstance().init(appiumDriver);
        ResetMapActivity.getInstance().init(appiumDriver);
        RetrievePassActivity.getInstance().init(appiumDriver);
        SettingActivity.getInstance().init(appiumDriver);
        SpotCleanActivity.getInstance().init(appiumDriver);
        TimeScheduleActivity.getInstance().init(appiumDriver);
        UnibotCleanActivity.getInstance().init(appiumDriver);
        UserAgreeActivity.getInstance().init(appiumDriver);
        VirtualWallActivity.getInstance().init(appiumDriver);
        VoiceReportActivity.getInstance().init(appiumDriver);
        WelcomeActivity.getInstance().init(appiumDriver);
        WorkLogActivity.getInstance().init(appiumDriver);
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
     *
     @param args:
      *            [0:country]
      *            [1:type]
      *            [2:imap_server]
      *            [3:e-mail]
      *            [4:password]
      *            [5:time(click send email)]
     */
    private int verifyEmail(String args[]){
//        String strPath = RegisterActivity.class.getResource("/").getPath();
//        strPath = strPath + "../../" + strJar;
//        File fileApp = new File(strPath);
//        logger.info(strPath);
        //[0:country][1:type][2:imap_server][3:e-mail][4:password][5:time(click send email)]
        logger.info("[0:country]--" + args[0]);
        logger.info("[1:type]--" + args[1]);
        logger.info("[2:imap_server]--" + args[2]);
        logger.info("[3:e-mail]--" + args[3]);
        logger.info("[4:password]--" + args[4]);
        logger.info("[5:time]--" + args[5]);
        if(args[0].contains(" ")){
            logger.info(args[0]);
            args[0] = args[0].replaceAll(" ", "_");
            logger.info(args[0]);
        }
        return ImapMailBox.getInstance().verifyEmail(args);
        //Common.getInstance().executeCommand("java -jar " + fileApp.getName() + " " + strCountry + " " + strType + " " + strServer + " " + strMail + " " + strPassword);
    }

    private String getCurTime(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);

    }

    boolean registerAndLogin(String strCountry, String strServer, String strEmail, String strPass){
        //register
        if(!enterRegisterActivity()){
            return false;
        }
        if (!RegisterActivity.getInstance().fill_Screenshot_Click(strCountry, strEmail, PropertyData.getProperty("register_pass"))) {
            logger.error("Register failed!!! country--" + strCountry);
            Common.getInstance().setType(Common.REGISTER_RETURN_TYPE.ALREADY_REGISTER);
            RegisterActivity.getInstance().clickBack();
            return false;
        }
        if(!RetrievePassActivity.getInstance().ShowResisterConfirmActivity()){
            Common.getInstance().setType(Common.REGISTER_RETURN_TYPE.ALREADY_REGISTER);
            RegisterActivity.getInstance().clickBack();
            logger.error("Not show Retrieve confirm email activity!!!");
            return false;
        }
        String strTime = getCurTime();
        logger.info("Show active email activity!!!");
        Common.getInstance().setType(Common.REGISTER_RETURN_TYPE.SENDED_EMAIL);
        //verify email
        //[0:country][1:type][2:imap_server][3:e-mail][4:password][5:time(click send email)]
        String args[] = new String[6];
        args[0] = strCountry;
        args[1] = "Register";
        args[2] = strServer;
        args[3] = strEmail;
        args[4] = strPass;
        args[5] = strTime;
        if(1 != verifyEmail(args)){
            return false;
        }
        if (Common.getInstance().isAndroid()){
            //check--login with new password
            RetrievePassActivity.getInstance().clickLogin();
        }else {
            //check--login with new password
            //return register activity
            RetrievePassActivity.getInstance().clickBack();
            //return welcome activity
            RegisterActivity.getInstance().clickBack();
            WelcomeActivity.getInstance().clickLogin();
        }
        if (!loginWithoutWelcome(strCountry, strEmail, PropertyData.getProperty("register_pass"))) {
            logger.info("Login failed country- " + strCountry);
            Common.getInstance().setType(Common.REGISTER_RETURN_TYPE.REGISTER_FAILED);
            LoginActivity.getInstance().clickBack();
            return false;
        }
        if (!logout()) {
            logger.info("Logout failed country- " + strCountry);
            //Common.getInstance().goBack(androidDriver, 1);
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

    boolean forgetPassword(String strCountry, String strServer, String strEmail, String strPass){
        if(!enterLoginAcivity()){
            return false;
        }
        LoginActivity.getInstance().clickForgetPass();
        logger.info("Click forget password!!!");
//        if (!ForgetPassActivity.getInstance().showActivity()) {
//            LoginActivity.getInstance().clickBack();
//            logger.error("Not show forget password activity!!!");
//            return false;
//        }
        Common.getInstance().waitForSecond(300);
        if (!ForgetPassActivity.getInstance().sendEmail(strCountry, strEmail)) {
            ForgetPassActivity.getInstance().clickBack();
            LoginActivity.getInstance().clickBack();
            Common.getInstance().setType(Common.REGISTER_RETURN_TYPE.NOT_REGISTER);
            logger.error("Not show retrieve password activity!!!");
            return false;
        }
        logger.info("Click send verify email!!!");
        if (!RetrievePassActivity.getInstance().showRetrieveConfirmActivity()) {
            logger.error("Not show retrieve password activity!!!");
            RetrievePassActivity.getInstance().clickBack();
            LoginActivity.getInstance().clickBack();
            return false;
            //invalid email
        }
        String strTime = getCurTime();
        logger.info("Show retrieve password activity!!!");
        //verify email
        //[0:country][1:type][2:imap_server][3:e-mail][4:password][5:time(click send email)]
        String args[] = new String[6];
        args[0] = strCountry;
        args[1] = "DoFindPass";
        args[2] = strServer;
        args[3] = strEmail;
        args[4] = strPass;
        args[5] = strTime;
        if(1 != verifyEmail(args)){
            return false;
        }
        //check--login with new password
        RetrievePassActivity.getInstance().clickLogin();
        if (!loginWithoutWelcome(strCountry, strEmail, PropertyData.getProperty("login_pass"))) {
            logger.info("Login failed after forget password country- " + strCountry);
            LoginActivity.getInstance().clickBack();
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
        /*LoginActivity.getInstance().clickCountry();
        if(!CountrySelectActivity.getInstance().selectCountry(strCountry)){
            return false;
        }*/
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
        //MoreActivity.getInstance().clickConfirm();
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

    void changeLanguage(String strLanguage){
        //return deebot clean
        SettingActivity.getInstance().clickBack();
        //return main
        UnibotCleanActivity.getInstance().clickBack();
        /*if(!login("Japan", PropertyData.getProperty("hotmail_email"), PropertyData.getProperty("login_pass"))){
            logger.error("login failed!!!");
            return;
        }*/
        MainActivity.getInstance().showActivity();
        MainActivity.getInstance().clickMore();
        MoreActivity.getInstance().clickLanguage();
        LanguageActivity.getInstance().selectLanguage(strLanguage);
        //return main
        MoreActivity.getInstance().clickBack();
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
        return MoreActivity.getInstance().translate(languageMap);
    }

    boolean translateChangePass(){
        //MainActivity.getInstance().clickMore();
        MoreActivity.getInstance().clickChangePass();
        ChangePassActivity.getInstance().showActivity();
        boolean bTrans =  ChangePassActivity.getInstance().translate(languageMap);
        //Common.getInstance().goBack(androidDriver, 2);
        return bTrans;
    }

    boolean translateAbout(){
        //MainActivity.getInstance().clickMore();
        MoreActivity.getInstance().clickAbout();
        boolean bTrans =  AboutActivity.getInstance().staticUITranslate(languageMap);
        //Common.getInstance().goBack(androidDriver, 1);
        return bTrans;
    }

    boolean translateUserAgree(){
        //MainActivity.getInstance().clickMore();
        MoreActivity.getInstance().clickAbout();
        AboutActivity.getInstance().clickUserAgree();
        boolean bTrans =  UserAgreeActivity.getInstance().staticUITranslate(languageMap);
        //back to more
        //Common.getInstance().goBack(androidDriver, 2);
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
        //Common.getInstance().goBack(androidDriver, 1);
        return bResult;
    }

    boolean translateContiuneClean(){
        //after check return to setting
        SettingActivity.getInstance().clickContinuedClean();
        boolean bResult = ContinueCleanActivity.getInstance().translate(languageMap);
        //Common.getInstance().goBack(androidDriver, 1);
        return bResult;

    }

    boolean translateVoiceReport(){
        //after check return to setting
        SettingActivity.getInstance().clickLanguage();
        boolean bResult = VoiceReportActivity.getInstance().staticUITranslation(languageMap);
        //Common.getInstance().goBack(androidDriver, 1);
        return bResult;
    }

    boolean translateFirmVer(){
        SettingActivity.getInstance().clickFirmware();
        boolean bResult = FirmVerActivity.getInstance().staticUITranslation(languageMap);
        //Common.getInstance().goBack(androidDriver, 1);
        return bResult;
    }

    boolean translateResetMap(){
        SettingActivity.getInstance().clickReset();
        boolean bResult = ResetMapActivity.getInstance().translate(languageMap);
        //Common.getInstance().goBack(androidDriver, 1);
        return bResult;
    }

    boolean translateNewTimeTranslation(){
        SettingActivity.getInstance().clickTimeSchedule();
        TimeScheduleActivity.getInstance().showActivity();
        TimeScheduleActivity.getInstance().clickAddSchedule();
        boolean bResult = NewScheduleActivity.getInstance().translate(languageMap);
        //Common.getInstance().goBack(androidDriver, 1);
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
        //Common.getInstance().goBack(androidDriver, 1);
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
        //Common.getInstance().goBack(androidDriver, 1);
        return bResult;
    }

    boolean translateFullScreen(){
        UnibotCleanActivity.getInstance().clickFullScreen();
        return FullScreenActivity.getInstance().staticUITranslation(languageMap);
        //Common.getInstance().goBack(androidDriver, 1);
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
