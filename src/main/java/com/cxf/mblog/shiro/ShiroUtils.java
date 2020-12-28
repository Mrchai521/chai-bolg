package com.cxf.mblog.shiro;

import com.cxf.mblog.modules.data.AccountProfile;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * @author xfchai
 * @ClassName ShiroUtils.java
 * @Description shiro 工具类
 * @createTime 2020/12/28 14:27:00
 */
public class ShiroUtils {
    /**
     * 校验登录用户
     *
     * @return
     */
    public static AccountProfile checkAccount() {
        //获取登录用户
        Subject subject = SecurityUtils.getSubject();
        AccountProfile accountProfile = (AccountProfile) subject.getPrincipal();
        return accountProfile;
    }

}
