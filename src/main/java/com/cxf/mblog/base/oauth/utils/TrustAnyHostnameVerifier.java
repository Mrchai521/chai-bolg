package com.cxf.mblog.base.oauth.utils;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

class TrustAnyHostnameVerifier implements HostnameVerifier {
    public boolean verify(String hostname, SSLSession session) {
        return true;
    }
}
