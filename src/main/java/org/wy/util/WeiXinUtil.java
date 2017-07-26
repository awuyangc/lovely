package org.wy.util;


import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.wy.model.AccessToken;
import org.wy.model.JSTicket;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by wuyang on 2017/5/7.
 */
public class WeiXinUtil {
    private static final String APPID="wx4bbcc5569097fa14";
    private static final String APPSECRET="438a63dc3fef34da5681d4b55898555f";
    private static final String ACCESS_TOKEN_URL=" https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    /**
     * 获取AccessToken
     * @return 返回拿到的access_token及有效期
     */
    public static AccessToken setAccessToken() throws ClientProtocolException, IOException{
        String url = ACCESS_TOKEN_URL.replace("APPID", APPID).replace("APPSECRET", APPSECRET);//将URL中的两个参数替换掉
        AccessToken accessToken=AccessToken.getInstance();
        try {
            URL getUrl=new URL(url);
            HttpURLConnection http=(HttpURLConnection)getUrl.openConnection();
            http.setRequestMethod("GET");
            http.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");
            http.setDoOutput(true);
            http.setDoInput(true);
            http.connect();
            InputStream is = http.getInputStream();
            int size = is.available();
            byte[] b = new byte[size];
            is.read(b);
            String message = new String(b, "UTF-8");
            JSONObject json = JSONObject.parseObject(message);
            accessToken.setToken(json.getString("access_token"));
            accessToken.setExpiresIn((json.getString("expires_in")));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return accessToken;
    }

    public static JSTicket setJSTicket() throws ClientProtocolException, IOException {
        AccessToken accessToken=AccessToken.getInstance();
        JSTicket jsTicket=JSTicket.getInstance();
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?";
        String params = "access_token=" + accessToken.getToken() + "&type=jsapi";
        try {
            URL getUrl=new URL(requestUrl+params);
            HttpURLConnection http=(HttpURLConnection)getUrl.openConnection();
            http.setRequestMethod("GET");
            http.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");
            http.setDoOutput(true);
            http.setDoInput(true);
            http.connect();
            InputStream is = http.getInputStream();
            int size = is.available();
            byte[] b = new byte[size];
            is.read(b);
            String message = new String(b, "UTF-8");
            JSONObject json = JSONObject.parseObject(message);
            jsTicket.setTicket(json.getString("ticket"));
            jsTicket.setExpires_in((json.getString("expires_in")));
            jsTicket.setErrcode((json.getInteger("errcode")));
            jsTicket.setErrmsg((json.getString("errmsg")));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsTicket;
    }

    public static String getAPPID() {
        return APPID;
    }

    public static String getAPPSECRET() {
        return APPSECRET;
    }

}
