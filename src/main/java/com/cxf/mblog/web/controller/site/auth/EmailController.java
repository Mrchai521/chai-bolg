package com.cxf.mblog.web.controller.site.auth;

import com.cxf.mblog.base.lang.Consts;
import com.cxf.mblog.base.lang.Result;
import com.cxf.mblog.modules.data.AccountProfile;
import com.cxf.mblog.modules.data.UserVO;
import com.cxf.mblog.modules.service.MailService;
import com.cxf.mblog.modules.service.SecurityCodeService;
import com.cxf.mblog.modules.service.UserService;
import com.cxf.mblog.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xfchai
 * @ClassName EmailController.java
 * @Description EmailController邮件控制器
 * @createTime 2021/09/06 09:38:00
 */
@RestController
@RequestMapping("/email")
public class EmailController extends BaseController {
    @Autowired
    private UserService userService;
    @Autowired
    private MailService mailService;
    @Autowired
    private SecurityCodeService securityCodeService;

    private static final String EMAIL_TITLE = "[{0}]您正在使用邮箱安全验证服务";

    @GetMapping("/send_code")
    public Result sendCode(String email, @RequestParam(name = "type", defaultValue = "1") Integer type) {
        Assert.hasLength(email, "请输入邮箱地址");
        Assert.notNull(type, "缺少必要的参数");

        String key = email;

        switch (type) {
            case Consts.CODE_BIND:
                AccountProfile profile = getProfile();
                Assert.notNull(profile, "请先登录后再进行此操作");
                key = String.valueOf(profile.getId());

                UserVO exist = userService.getByEmail(email);
                Assert.isNull(exist, "该邮箱已被使用");
                break;
            case Consts.CODE_FORGOT:
                UserVO user = userService.getByEmail(email);
                Assert.notNull(user, "账户不存在");
                key = String.valueOf(user.getId());
                break;
            case Consts.CODE_REGISTER:
                key = email;
                break;
        }

        String code = securityCodeService.generateCode(key, type, email);
        Map<String, Object> context = new HashMap<>();
        context.put("code", code);

        String title = MessageFormat.format(EMAIL_TITLE, siteOptions.getValue("site_name"));
        mailService.sendTemplateEmail(email, title, Consts.EMAIL_TEMPLATE_CODE, context);
        return Result.successMessage("邮件发送成功");
    }

}
