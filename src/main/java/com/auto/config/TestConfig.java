package com.auto.config;


import org.apache.http.client.CookieStore;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * Description ApiAutoTest
 * Create by qym on 2020/1/9 11:20
 * @author qym
 */

public class TestConfig {

    /**
     *  获取TICKET URL
     */
    public static String ticketurl;
    /**
     *  登录URL
     */
    public static String loginUrl;
    /**
     * 页面查询
     */
    public static String searchUrl;
    /**
     * 查询data
     */
    public static String dataUrl;
    /**
     * 查询关联企业
     */
    public static String affiliatedNavigationUrl;
    /**
     * 查询关联企业-对外投资，在外任职，担任法定代表人
     */
    public static String affiliatedEnterprisesUrl;
    /**
     * 同业分析接口
     */
    public static String analysisUrl;
    /**
     * 同业分析查看更多接口
     */
    public static String analyticalNavigationUrl;
    /**
     * 负面清单接口
     */
    public static String listUrl;
    /**
     * 企业图谱
     */
    public static String infiltrationUrl;

    public static CookieStore store;
    public static DefaultHttpClient client = new DefaultHttpClient();
}
