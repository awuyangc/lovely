package org.wy.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.wy.model.*;

import javax.annotation.Resource;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.*;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by Administrator on 2017/5/5.
 */
@Service
public class TestService {
    @Resource
    private JdbcTemplate jdbcTemplate;
    public int insertTestData(){
        StringBuffer sqlParam=new StringBuffer();
        String[] answerArray=new String[]{"A","B","C","D","E","F"};
        for(int i=0;i<18017;i++){
            int rand=(int)(Math.random()*(5+1));
            if(i==0) {
                sqlParam.append("('" + i + ".jpg','正常','炎症','低级别病变LSIL','高级别病变HSIL','癌症','不确定','" + answerArray[rand] + "',now(),0,0)");
            }
            else{
                sqlParam.append(",('" + i + ".jpg','正常','炎症','低级别病变LSIL','高级别病变HSIL','癌症','不确定','" + answerArray[rand] + "',now(),0,0)");
            }
        }
        String sql="insert into question(imgUrl,chkA,chkB,chkC,chkD,chkE,chkF,answer,createtime,isDelete,flag) VALUES"+
                "('1.jpg','正常','炎症','低级别病变','高级别病变','癌症','不确定','A',now(),0,0),"+
                "('2.jpg','正常','炎症','低级别病变','高级别病变','癌症','不确定','A',now(),0,0),"+
                "('3.jpg','正常','炎症','低级别病变','高级别病变','癌症','不确定','A',now(),0,0),"+
                "('4.jpg','正常','炎症','低级别病变','高级别病变','癌症','不确定','A',now(),0,0),"+
                "('5.jpg','正常','炎症','低级别病变','高级别病变','癌症','不确定','B',now(),0,0),"+
                "('6.jpg','正常','炎症','低级别病变','高级别病变','癌症','不确定','B',now(),0,0),"+
                "('7.jpg','正常','炎症','低级别病变','高级别病变','癌症','不确定','B',now(),0,0),"+
                "('8.jpg','正常','炎症','低级别病变','高级别病变','癌症','不确定','B',now(),0,0),"+
                "('9.jpg','正常','炎症','低级别病变','高级别病变','癌症','不确定','B',now(),0,0),"+
                "('10.jpg','正常','炎症','低级别病变','高级别病变','癌症','不确定','B',now(),0,0),"+
                "('11.jpg','正常','炎症','低级别病变','高级别病变','癌症','不确定','B',now(),0,0),"+
                "('12.jpg','正常','炎症','低级别病变','高级别病变','癌症','不确定','B',now(),0,0),"+
                "('13.jpg','正常','炎症','低级别病变','高级别病变','癌症','不确定','B',now(),0,0),"+
                "('14.jpg','正常','炎症','低级别病变','高级别病变','癌症','不确定','B',now(),0,0),"+
                "('15.jpg','正常','炎症','低级别病变','高级别病变','癌症','不确定','C',now(),0,0),"+
                "('16.jpg','正常','炎症','低级别病变','高级别病变','癌症','不确定','D',now(),0,0),"+
                "('17.jpg','正常','炎症','低级别病变','高级别病变','癌症','不确定','D',now(),0,0),"+
                "('18.jpg','正常','炎症','低级别病变','高级别病变','癌症','不确定','D',now(),0,0),"+
                "('19.jpg','正常','炎症','低级别病变','高级别病变','癌症','不确定','D',now(),0,0),"+
                "('20.jpg','正常','炎症','低级别病变','高级别病变','癌症','不确定','D',now(),0,0),"+
                "('21.jpg','正常','炎症','低级别病变','高级别病变','癌症','不确定','D',now(),0,0),"+
                "('22.jpg','正常','炎症','低级别病变','高级别病变','癌症','不确定','D',now(),0,0),"+
                "('23.jpg','正常','炎症','低级别病变','高级别病变','癌症','不确定','D',now(),0,0),"+
                "('24.jpg','正常','炎症','低级别病变','高级别病变','癌症','不确定','D',now(),0,0),"+
                "('25.jpg','正常','炎症','低级别病变','高级别病变','癌症','不确定','D',now(),0,0),"+
                "('26.jpg','正常','炎症','低级别病变','高级别病变','癌症','不确定','D',now(),0,0),"+
                "('27.jpg','正常','炎症','低级别病变','高级别病变','癌症','不确定','D',now(),0,0),"+
                "('28.jpg','正常','炎症','低级别病变','高级别病变','癌症','不确定','D',now(),0,0),"+
                "('29.jpg','正常','炎症','低级别病变','高级别病变','癌症','不确定','D',now(),0,0),"+
                "('30.jpg','正常','炎症','低级别病变','高级别病变','癌症','不确定','D',now(),0,0),"+
                "('31.jpg','正常','炎症','低级别病变','高级别病变','癌症','不确定','D',now(),0,0),"+
                "('32.jpg','正常','炎症','低级别病变','高级别病变','癌症','不确定','D',now(),0,0),"+
                "('33.jpg','正常','炎症','低级别病变','高级别病变','癌症','不确定','D',now(),0,0),"+
                "('34.jpg','正常','炎症','低级别病变','高级别病变','癌症','不确定','E',now(),0,0),"+
                "('35.jpg','正常','炎症','低级别病变','高级别病变','癌症','不确定','E',now(),0,0),"+
                "('36.jpg','正常','炎症','低级别病变','高级别病变','癌症','不确定','E',now(),0,0),"+
                "('37.jpg','正常','炎症','低级别病变','高级别病变','癌症','不确定','E',now(),0,0),"+
                "('38.jpg','正常','炎症','低级别病变','高级别病变','癌症','不确定','E',now(),0,0),"+
                "('39.jpg','正常','炎症','低级别病变','高级别病变','癌症','不确定','E',now(),0,0)";

        int result= jdbcTemplate.update(sql);
        return result;
        /*
        return jdbcTemplate.queryForList(sql, new RowMapper<Question>(){
            public Question mapRow(ResultSet rs, int rowNum) throws SQLException {
                Question question = new Question();
                question.setId(rs.getString("id"));
                question.setImgUrl(rs.getString("imgUrl"));
                question.setChkA(rs.getString("chkA"));
                question.setChkB(rs.getString("chkB"));
                question.setChkC(rs.getString("chkC"));
                question.setChkD(rs.getString("chkD"));
                question.setChkE(rs.getString("chkE"));
                question.setChkF(rs.getString("chkF"));
                question.setAnswer(rs.getString("answer"));
                question.setIsDelete(rs.getString("isDelete"));
                return question;
            }
        }, total);
        */
    }
    // 菜单创建（POST） 限100（次/天）
    public  String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

    /**
     * 创建菜单
     *
     * @param menu 菜单实例
     * @param accessToken 有效的access_token
     * @return 0表示成功，其他值表示失败
     */
    public  int createMenu(String accessToken) {
        ViewButton btn11 = new ViewButton();
        btn11.setName("答题");
        btn11.setType("view");
        btn11.setUrl("http://awuyangc.iask.in");

        ViewButton btn12 = new ViewButton();
        btn12.setName("投票");
        btn12.setType("view");
        btn12.setUrl("http://awuyangc.iask.in/forward/vote.action");

        ViewButton btn13 = new ViewButton();
        btn13.setName("帐号绑定");
        btn13.setType("view");
        btn13.setUrl("http://awuyangc.iask.in/forward/bind.action");

        ComplexButton mainBtn1 = new ComplexButton();
        mainBtn1.setName("菜单");
        mainBtn1.setSub_button(new Button[] { btn11, btn12, btn13});
        Menu menu = new Menu();
        //menu.setButton(new Button[] { mainBtn1, mainBtn2});
        menu.setButton(new Button[] { mainBtn1});

        int result = 0;
        // 拼装创建菜单的url
        String url = menu_create_url.replace("ACCESS_TOKEN", accessToken);
        // 将菜单对象转换成json字符串
        String jsonMenu = JSONObject.toJSON(menu).toString();
        System.out.println("菜单json："+jsonMenu);
        // 调用接口创建菜单
        JSONObject jsonObject = httpRequest(url, "POST", jsonMenu);

        if (null != jsonObject) {
            if (0 != jsonObject.getInteger("errcode")) {
                result = jsonObject.getInteger("errcode");
                System.out.println("创建菜单失败 errcode:{"+jsonObject.getInteger("errcode")+"} errmsg:{"+jsonObject.getString("errmsg")+"}");
            }
        }

        return result;
    }

    public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
        JSONObject jsonObject = null;
        StringBuffer buffer = new StringBuffer();
        try {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = { new MyX509TrustManager() };
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL url = new URL(requestUrl);
            HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
            httpUrlConn.setSSLSocketFactory(ssf);

            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            httpUrlConn.setRequestMethod(requestMethod);

            if ("GET".equalsIgnoreCase(requestMethod))
                httpUrlConn.connect();

            // 当有数据需要提交时
            if (null != outputStr) {
                OutputStream outputStream = httpUrlConn.getOutputStream();
                // 注意编码格式，防止中文乱码
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }

            // 将返回的输入流转换成字符串
            InputStream inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            // 释放资源
            inputStream.close();
            inputStream = null;
            httpUrlConn.disconnect();
            jsonObject = JSONObject.parseObject(buffer.toString());
        } catch (ConnectException ce) {
            System.out.println("微信服务器链接超时.");
        } catch (Exception e) {
           System.out.println("https 请求错误："+e);
        }
        return jsonObject;
    }
}
