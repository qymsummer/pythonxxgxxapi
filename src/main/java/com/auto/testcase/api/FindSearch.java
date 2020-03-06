package com.auto.testcase.api;

import com.auto.config.TestConfig;
import com.auto.model.InterfaceName;
import com.auto.utils.ConfigFile;
import com.auto.utils.TokenFile;
import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * @Description ApiAutoTest
 * @Date 2020/3/3 10:31
 * @Author qym
 */
public class FindSearch {
    @BeforeMethod(
            description = "页面接口查询"
    )
    public void beforeMethod() {
        TestConfig.searchUrl = ConfigFile.getUrl(InterfaceName.SEARCH);
    }
    @AfterMethod
    public void afterMethod(){
    }
    @Test(groups = "FindSearch",description = "页面接口查询")
    public void findSearch() throws IOException, URISyntaxException {
        URIBuilder builder = new URIBuilder(TestConfig.searchUrl);
        System.out.println(builder);
        builder.addParameter("keywords","91330106MA27XMXJ27");
        builder.addParameter("page","1");
        builder.addParameter("count","5");
        HttpGet httpGet = new HttpGet(builder.build());
        String name="jwtToken";
        String value = TokenFile.readFile("E:\\xxgxdata\\Tokenfile.txt");
        String newValue = value.replaceAll("[\\t\\n\\r\\s]","");
        httpGet.setHeader(name,newValue);
        HttpResponse response = TestConfig.client.execute (httpGet);
        System.out.println(response);
        String result;
        result = EntityUtils.toString (response.getEntity(),"utf-8");
        System.out.println("结果"+result);
        JSONObject jsonObject = new JSONObject(result);
        int success = (int) jsonObject.get("code");
        //判断
        Assert.assertEquals(0,success);
    }
}
