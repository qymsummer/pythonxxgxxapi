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
 * @Date 2020/3/4 14:21
 * @Author qym
 */
public class FindAnalysis {
    @BeforeMethod(
            description = "同业分析"
    )
    public void beforeMethod() {
        TestConfig.analysisUrl = ConfigFile.getUrl(InterfaceName.ANALYSIS);
    }
    @AfterMethod
    public void afterMethod(){

    }
    @Test(groups = "FindAnalysis",dependsOnGroups = "loginCaseDb",description = "同业分析")
    public void findAnalysis() throws IOException, InterruptedException {
        List<String> list = getList();
        for(String temp : list) {
            HttpPost httpPost = new HttpPost(TestConfig.analysisUrl);
            JSONObject params = new JSONObject();
            params.put("uniscid", "91330106MA27XMXJ27");
            params.put("fieldName", temp);
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
            Assert.assertEquals(0, success);
        }
    }
    private List getList(){
        List<String> list = new ArrayList<String>();
        list.add("opfrom");
        list.add("regcap");
        list.add("oploc");
        return list;
    }
}
