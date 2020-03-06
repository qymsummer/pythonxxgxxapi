package com.auto.testcase.api;

import com.auto.config.TestConfig;
import com.auto.model.InterfaceName;
import com.auto.utils.ConfigFile;
import com.auto.utils.TokenFile;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description ApiAutoTest
 * @Date 2020/3/4 12:56
 * @Author qym
 */
public class FindAnalyticalNavigation {
    @BeforeMethod(
            description = "同业分析更多接口"
    )
    public void beforeMethod() {
        TestConfig.analyticalNavigationUrl = ConfigFile.getUrl(InterfaceName.ANALYTICALNAVIGATION);
    }
    @AfterMethod
    public void afterMethod(){

    }
    @Test(groups = "AnalyticalNavigation",description = "同业分析更多接口")
    public void findAnalyticalNavigation() throws IOException {
            HttpPost httpPost = new HttpPost(TestConfig.analyticalNavigationUrl);
            JSONObject params = new JSONObject();
            params.put("tableId", "3");
            params.put("uniscid", "91330106MA27XMXJ27");
            httpPost.setHeader("content-type", "application/json");
            String name = "jwtToken";
            String value = TokenFile.readFile("E:\\xxgxdata\\Tokenfile.txt");
            String newValue = value.replaceAll("[\\t\\n\\r\\s]", "");
            httpPost.setHeader(name, newValue);
            StringEntity entity = new StringEntity(params.toString(), "utf-8");
            httpPost.setEntity(entity);
            String result;
            HttpResponse response = TestConfig.client.execute(httpPost);
            result = EntityUtils.toString(response.getEntity(), "utf-8");
            System.out.println("测试结果:" + result);
            JSONObject jsonObject = new JSONObject(result);
            int success = (int) jsonObject.get("code");
            //判断
            Assert.assertEquals(0, success);

    }


}
