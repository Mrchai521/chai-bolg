/**
 *
 */
package com.cxf.mblog.web.controller.site.auth;

import com.cxf.mblog.base.lang.Consts;
import com.cxf.mblog.base.lang.Result;
import com.cxf.mblog.modules.data.AccountProfile;
import com.cxf.mblog.modules.data.UserVO;
import com.cxf.mblog.modules.service.SecurityCodeService;
import com.cxf.mblog.modules.service.UserService;
import com.cxf.mblog.web.controller.BaseController;
import com.cxf.mblog.web.controller.site.Views;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author xfchai
 * @ClassName RegisterController.java
 * @Description RegisterController
 * @createTime 2021/09/06 09:38:00
 */
@Controller
@ConditionalOnProperty(name = "site.controls.register", havingValue = "true", matchIfMissing = true)
public class RegisterController extends BaseController {
    @Autowired
    private UserService userService;
    @Autowired
    private SecurityCodeService securityCodeService;

    @GetMapping("/register")
    public String view() {
        AccountProfile profile = getProfile();
        if (profile != null) {
            return String.format(Views.REDIRECT_USER_HOME, profile.getId());
        }
        return view(Views.REGISTER);
    }

    @PostMapping("/register")
    public String register(UserVO post, HttpServletRequest request, ModelMap model) {
        String view = view(Views.REGISTER);
        try {
            if (siteOptions.getControls().isRegister_email_validate()) {
                String code = request.getParameter("code");
                Assert.state(StringUtils.isNotBlank(post.getEmail()), "请输入邮箱地址");
                Assert.state(StringUtils.isNotBlank(code), "请输入邮箱验证码");
                securityCodeService.verify(post.getEmail(), Consts.CODE_REGISTER, code);
            }
            post.setAvatar(Consts.AVATAR);
            userService.register(post);
            Result<AccountProfile> result = executeLogin(post.getUsername(), post.getPassword(), false);
            view = String.format(Views.REDIRECT_USER_HOME, result.getData().getId());
        } catch (Exception e) {
            model.addAttribute("post", post);
            model.put("data", Result.failure(e.getMessage()));
        }
        return view;
    }

}