/*
+--------------------------------------------------------------------------
|   Mblog [#RELEASE_VERSION#]
|   ========================================
|   Copyright (c) 2014, 2015 mtons. All Rights Reserved
|   http://www.mtons.com
|
+---------------------------------------------------------------------------
*/
package com.cxf.mblog.web.controller.site.auth;

import com.cxf.mblog.web.controller.BaseController;
import com.cxf.mblog.web.controller.site.Views;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

/**
 * @author xfchai
 * @ClassName LogoutController.java
 * @Description LogoutController
 * @createTime 2021/09/06 09:38:00
 */
@Controller
public class LogoutController extends BaseController {

    /**
     * 退出登录
     *
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpServletResponse response) {
        SecurityUtils.getSubject().logout();
        // HTTP 1.1.
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        // HTTP 1.0.
        response.setHeader("Pragma", "no-cache");
        // Proxies.
        response.setDateHeader("Expires", 0);
        return Views.REDIRECT_INDEX;
    }

}
