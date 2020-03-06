package com.auto.utils;



import com.auto.model.InterfaceName;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Description ApiAutoTest
 * Create by qym on 2020/1/9 11:20
 * @author qym
 */

public class ConfigFile {

    private static ResourceBundle bundle = ResourceBundle.getBundle("application", Locale.CHINA);


    public static String getUrl(InterfaceName name) {
        String address = bundle.getString("test.uri");
        String uri = "";
        String testUrl;

        if (name == InterfaceName.LOGIN) {
            uri = bundle.getString("login.uri");
        }
        if (name == InterfaceName.GETTICKET) {
            uri = bundle.getString("ticket.uri");
        }
        if (name == InterfaceName.DATAAPI) {
            uri = bundle.getString("data.uri");
        }
        if (name == InterfaceName.SEARCH) {
            uri = bundle.getString("search.uri");
        }
        if (name == InterfaceName.AFFILIATEDNAVIGATION) {
            uri = bundle.getString("affiliatedNavigation.uri");
        }
        if (name == InterfaceName.AFFILIATEDENTERPRISES) {
            uri = bundle.getString("affiliatedEnterprises.uri");
        }
        if (name == InterfaceName.ANALYSIS) {
            uri = bundle.getString("analysis.uri");
        }
        if (name == InterfaceName.ANALYTICALNAVIGATION) {
            uri = bundle.getString("analyticalNavigation.uri");
        }
        if (name == InterfaceName.LIST) {
            uri = bundle.getString("list.uri");
        }
        if (name == InterfaceName.INFILTRATION) {
            uri = bundle.getString("infiltration.uri");
        }
        testUrl = address + uri;
        return testUrl;
    }
    public static String getParams(){
        String params = bundle.getString("param.uri");
        return params;
    }
}