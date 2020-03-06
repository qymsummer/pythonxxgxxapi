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
import java.util.List;

/**
 * @Description ApiAutoTest
 * @Date 2020/3/3 13:03
 * @Author qym
 */
public class FindAffiliatedNavigation {
    @BeforeMethod(
            description = "关联企业查询接口"
    )
    public void beforeMethod() {

        TestConfig.affiliatedNavigationUrl = ConfigFile.getUrl(InterfaceName.AFFILIATEDNAVIGATION);
    }
    @AfterMethod
    public void afterMethod(){
    }
    @Test(groups = "findAffiliatedNavigation",dependsOnGroups = "loginCaseDb",description = "关联企业查询接口")
    public void findAffiliatedNavigation() throws IOException {
            HttpPost httpPost = new HttpPost(TestConfig.affiliatedNavigationUrl);
            JSONObject params = new JSONObject();
            params.put("uniscid", "91330106MA27XMXJ27");
            params.put("executiveName", "高航");
            params.put("tableId", "3");
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
            Assert.assertEquals(0,success);
    }
}
