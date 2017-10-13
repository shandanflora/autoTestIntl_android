package com.ecovacs.test.common;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;*/

//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.entity.FileEntity;
//import java.io.FileOutputStream;
//import java.io.InputStream;

/**
 * Created by Lily on 2015/11/17.
 *
 */
public class HttpRequestUtils {

    private static Logger logger = LoggerFactory.getLogger(HttpRequestUtils.class);
    private static HttpRequestUtils httpRequestUtils = null;

    private HttpRequestUtils(){

    }

    public static HttpRequestUtils getInstance(){
        if (httpRequestUtils == null){
            httpRequestUtils = new HttpRequestUtils();
        }
        return httpRequestUtils;
    }

    public JSONObject HttpPost(String strUrl, List<NameValuePair> params) {
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        HttpPost httppost = new HttpPost(strUrl);
        httppost.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        JSONObject obj = null;
        try {
            httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
            CloseableHttpResponse response = httpclient.execute(httppost);
            try {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    //System.out.println("--------------------------------------");
                    //System.out.println("Response content: " + EntityUtils.toString(entity, "UTF-8"));
                    //System.out.println("--------------------------------------");
                    obj = new JSONObject(EntityUtils.toString(entity));
                } else {
                    logger.error("No respose from" + strUrl);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return obj;
    }

    public List<NameValuePair> getParaFromJson(String strFile, String strKey) {
        List<NameValuePair> paras = new ArrayList<NameValuePair>();
        String strPath = "";
        try {
            strPath = this.getClass().getClassLoader().getResource(strFile).getPath();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        if (strPath == null) {
            return null;
        }
        String data;
        try {
            data = FileUtils.readFileToString(new File(strPath), "UTF-8");
            try {
                JSONObject jsonObj = new JSONObject(data);
                JSONArray arrays = jsonObj.getJSONArray(strKey);
                //paras.add(new BasicNameValuePair("ctl", objErr.getString("ctl")));
                //strTemp = jsonObjList.get("sign").toString();
                JSONObject paraObj = new JSONObject(arrays.get(0).toString());
                Iterator iterator = paraObj.keys();
                while (iterator.hasNext()){
                    String key = iterator.next().toString();
                    paras.add(new BasicNameValuePair(key, paraObj.getString(key)));
                }
            } catch (JSONException e) {
                logger.error("Could not find the value matched key!!!");
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return paras;
    }
}