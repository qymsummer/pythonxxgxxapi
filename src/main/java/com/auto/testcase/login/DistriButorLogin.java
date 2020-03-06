package com.auto.testcase.login;

import com.auto.config.TestConfig;
import com.auto.model.InterfaceName;
import com.auto.utils.ConfigFile;
import com.auto.utils.TokenFile;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Description ApiAutoTest
 * Create by qym on 2020/1/9 11:20
 * @author qym
 */

public class DistriButorLogin {

    private static String account = "ybjautoxxgx123";
    private static String password = "ybjautoxxgx123";
    private static String userType = "1";

    @BeforeTest
    public void beforeTest(){
        TestConfig.ticketurl = ConfigFile.getUrl(InterfaceName.GETTICKET);
        TestConfig.loginUrl = ConfigFile.getUrl(InterfaceName.LOGIN);
    }
    @AfterTest
    public void afterTest(){
    }

    public static String getTicket() throws IOException {
        HttpPost httpPost = new HttpPost(TestConfig.ticketurl);
        HttpResponse response = TestConfig.client.execute(httpPost);
        String getTicket;
        getTicket = EntityUtils.toString(response.getEntity(), "utf-8");
        JSONObject resultJson = new JSONObject(getTicket);
        JSONObject ticketList = resultJson.getJSONObject("data");
        String ticket = (String) ticketList.get("ticket");
        return ticket;
    }
    @Test(groups = "loginCaseDb", description = "用户登录成功")
    public void loginCaseDb() throws Exception {
        String token = getToken();
        TokenFile.witerFile(token,"E:\\xxgxdata\\Tokenfile.txt");
    }

    public static String getToken() throws IOException {
        HttpPost httpPost = new HttpPost(TestConfig.loginUrl);
        System.out.println(httpPost);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        String ticket = getTicket();
        params.add(new BasicNameValuePair("account",account));
        params.add(new BasicNameValuePair("password",password));
        params.add(new BasicNameValuePair("userType",userType));
        params.add(new BasicNameValuePair("ticket",ticket));
        httpPost.setHeader("content-type", "application/x-www-form-urlencoded");
        HttpEntity entity = new UrlEncodedFormEntity(params, "utf-8");
        httpPost.setEntity(entity);

        String result;
        //设置Cookies信
        TestConfig.client.setCookieStore(TestConfig.store);
        //执行post方法
        HttpResponse response = TestConfig.client.execute (httpPost);
        //获取响应结果
        result = EntityUtils.toString (response.getEntity(),"utf-8");
        JSONObject resultJson = new JSONObject (result);
        System.out.println(result);
        String success = (String) resultJson.get("msg");
        //判断
        Assert.assertEquals("成功",success);
        JSONObject jsonObject = resultJson.getJSONObject("data");
        String token = (String) jsonObject.get("jwtToken");
        return token;
    }
}
