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
 * @Date 2020/3/2 16:22
 * @Author qym
 */
public class FindDataApi {
    @BeforeMethod(
    )
    public void beforeMethod() {
        TestConfig.dataUrl = ConfigFile.getUrl(InterfaceName.DATAAPI);
    }
    @AfterMethod
    public void afterMethod(){
    }
    @Test(groups = "FindDataApi",dependsOnGroups = "loginCaseDb",description = "数据data接口查询")
    public void findDataApi() throws IOException {
        List<String> list = getList();
        for(String temp : list) {
            HttpPost httpPost = new HttpPost(TestConfig.dataUrl);
            JSONObject params = new JSONObject();
            params.put("page", "1");
            params.put("tableId",temp);
            params.put("uniscid", "91330106MA27XMXJ27");
            params.put("pageSize", "5");
            params.put("order", "DESC");
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
            System.out.println("tableId:"+temp);
            JSONObject jsonObject = new JSONObject(result);
            int success = (int) jsonObject.get("code");
            //判断
            Assert.assertEquals(0,success);
        }

    }
    private List getList() {
        List<String> list = new ArrayList<String>();
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        list.add("9");
        list.add("10");
        list.add("11");
        list.add("16");
        list.add("19");
        list.add("257");
        list.add("13");
        list.add("259");
        list.add("260");
        list.add("14");
        list.add("18");
        list.add("36");
        list.add("39");
        list.add("44");
        list.add("123");
        list.add("258");
        list.add("17");
        list.add("92");
        return list;
    }
}
