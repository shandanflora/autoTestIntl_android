package com.ecovacs.test;

import com.ecovacs.test.common.*;
import com.google.gson.JsonObject;
import org.apache.http.NameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;

/**
 * Created by ecosqa on 16/12/12.
 * main
 */
public class Main {

    private static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String args[]) {
        //check repeat row in xlsx
        //TranslateIntl.getInstance().repeatRow();
        //only for debug
//        List<NameValuePair> paras = HttpRequestUtils.getInstance().getParaFromJson("handleUser.json", "getInfoByEmail");
//        JSONObject obj = HttpRequestUtils.getInstance().HttpPost(PropertyData.getProperty("url"), paras);
//        try {
//            if(obj.getString("code").equals("0000")){
//                logger.info("get user information by email successfully!!");
//            }else {
//                logger.info("get user information by email failed!!");
//            }
//        }catch (JSONException e){
//            e.printStackTrace();
//        }
        ////////
        //init
        Common.getInstance().setType(Common.REGISTER_RETURN_TYPE.NOT_REGISTER);
        //delete folder of report
        Common.getInstance().deleteDir(new File(Common.getInstance().getCurPath("/report")));

        List<String> list = JsonParse.getJsonParse().readDataFromJson("country.json", "countries");
        List<MailInfo> mailInfors = JsonParse.getJsonParse().getMailFromJson("country.json", "mail");
        HandleIntl.getInstance().initAppium();
        if (!HandleIntl.getInstance().enterWelcomeActivity()) {
            return;
        }
        int iLoop = 1;
        int iIndex = 0;
        ExcelManage.getInstance().init();
        for (String strCountry : list) {
            logger.info("***********iLoop -- " + iLoop);
            logger.info("***********Ready register country-- " + strCountry + "***********");
            /*if(Common.getInstance().getType() == Common.REGISTER_RETURN_TYPE.SENDED_EMAIL){
                logger.info("***********Wait for 10 minutes!!!***********");
                Common.getInstance().waitForSecond(1000 * 60 * 10);
            }*/
            ExcelRow row = new ExcelRow();
            row.setOrdinal(iLoop);
            //row.setType("Register");
            row.setCountry(strCountry);
//            //String strCountry, String strServer, String strEmail, String strPass
//            if (!HandleIntl.getInstance().registerAndLogin(strCountry,
//                    mailInfors.get(iIndex).getImapServer(),
//                    mailInfors.get(iIndex).getEmail(),
//                    mailInfors.get(iIndex).getPassword())) {
//                row.setResult("Fail");
//                ExcelManage.getInstance().writeRow(iLoop, row, false, Common.getInstance().getType());
//                logger.error("***********Register country--" + strCountry + " failed!!!***********");
//                iLoop++;
//                iIndex++;
//                continue;
//            }
//            row.setResult("Pass");
//            ExcelManage.getInstance().writeRow(iLoop, row, true, Common.getInstance().getType());
//            logger.info("***********Register country -" + strCountry + " successfully!!!***********");
            logger.info("***********Ready to Forget password country-" + strCountry + "***********");
            row.setType("ForgetPassword");
            if (!HandleIntl.getInstance().forgetPassword(strCountry,
                    mailInfors.get(iIndex).getImapServer(),
                    mailInfors.get(iIndex).getEmail(),
                    mailInfors.get(iIndex).getPassword())) {
                row.setResult("Fail");
                ExcelManage.getInstance().writeRow(iLoop + 1, row, false, Common.getInstance().getType());
                logger.error("***********Forget password country--" + strCountry + " failed!!!***********");
                iLoop++;
                iIndex++;
                continue;
            }
            //if pass forget password, set a new row
            row.setResult("Pass");
            ExcelManage.getInstance().writeRow(iLoop + 1, row, true, Common.getInstance().getType());
            iLoop = iLoop + 2;
            iIndex++;
            logger.info("***********Forget password country -" + strCountry + " successfully!!!***********");
        }
    }
}


