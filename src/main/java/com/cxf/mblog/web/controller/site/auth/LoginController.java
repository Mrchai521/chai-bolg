/*
+--------------------------------------------------------------------------
|   Mblog [#RELEASE_VERSION#]
|   ========================================
|   Copyright (c) 2014, 2015 cxf. All Rights Reserved
|   http://www.cxf.com
|
+---------------------------------------------------------------------------
*/
package com.cxf.mblog.web.controller.site.auth;

import com.cxf.mblog.base.lang.Result;
import com.cxf.mblog.modules.data.AccountProfile;
import com.cxf.mblog.web.controller.BaseController;
import com.cxf.mblog.web.controller.site.Views;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author xfchai
 * @ClassName LoginController.java
 * @Description LoginController登录
 * @createTime 2021/09/06 09:38:00
 */
@Controller
public class LoginController extends BaseController {

    /**
     * 跳转登录页
     *
     * @return
     */
    @GetMapping(value = "/login")
    public String view() {
        return view(Views.LOGIN);
    }

    /**
     * 提交登录
     *
     * @param username
     * @param password
     * @param model
     * @return
     */
    @PostMapping(value = "/login")
    public String login(String username,
                        String password,
                        @RequestParam(value = "rememberMe", defaultValue = "0") Boolean rememberMe,
                        ModelMap model) {
        String view = view(Views.LOGIN);

        Result<AccountProfile> result = executeLogin(username, password, rememberMe);

        if (result.isOk()) {
            view = String.format(Views.REDIRECT_USER_HOME, result.getData().getId());
        } else {
            model.put("message", result.getMessage());
        }
        return view;
    }

}
