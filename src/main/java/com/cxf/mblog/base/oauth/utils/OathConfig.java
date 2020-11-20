package com.cxf.mblog.base.oauth.utils;

import com.cxf.mblog.base.oauth.APIConfig;


public class OathConfig {
    public static String getValue(String key) {
        return APIConfig.getInstance().getValue(key);
    }
}
