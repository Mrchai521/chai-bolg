/*
+--------------------------------------------------------------------------
|   Mblog [#RELEASE_VERSION#]
|   ========================================
|   Copyright (c) 2014, 2015 cxf. All Rights Reserved
|   http://www.cxf.com
|
+---------------------------------------------------------------------------
*/
package com.cxf.mblog.web.controller.admin;

import com.cxf.mblog.base.lang.Consts;
import com.cxf.mblog.base.lang.Result;
import com.cxf.mblog.modules.data.AccountProfile;
import com.cxf.mblog.modules.entity.User;
import com.cxf.mblog.modules.service.ChannelService;
import com.cxf.mblog.modules.service.CommentService;
import com.cxf.mblog.modules.service.PostService;
import com.cxf.mblog.modules.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * @author langhsu
 */
@Controller
public class AdminController {
    @Autowired
    private ChannelService channelService;
    @Autowired
    private PostService postService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;

    @RequestMapping("/admin")
    public String index(HttpServletRequest request, ModelMap model) {
        pushSystemStatus(request, model);
        model.put("channelCount", channelService.count());
        model.put("postCount", postService.count());
        model.put("commentCount", commentService.count());
        model.put("userCount", userService.count());
        return "/admin/index";
    }

    private void pushSystemStatus(HttpServletRequest request, ModelMap model) {
        float freeMemory = (float) Runtime.getRuntime().freeMemory();
        float totalMemory = (float) Runtime.getRuntime().totalMemory();
        float usedMemory = (totalMemory - freeMemory);
        float memPercent = Math.round(freeMemory / totalMemory * 100);
        String os = System.getProperty("os.name");
        String javaVersion = System.getProperty("java.version");

        model.addAttribute("freeMemory", freeMemory);
        model.addAttribute("totalMemory", totalMemory / 1024 / 1024);
        model.addAttribute("usedMemory", usedMemory / 1024 / 1024);
        model.addAttribute("memPercent", memPercent);
        model.addAttribute("os", os);
        model.addAttribute("javaVersion", javaVersion);
    }

    @GetMapping("/lockScreen")
    public String lockScreen(ModelMap map) {
        Subject subject = SecurityUtils.getSubject();
        AccountProfile user = (AccountProfile) subject.getPrincipal();
        SecurityUtils.getSubject().getSession(true).setAttribute(Consts.LOCK_SCREEN, true);
        map.put("user", user);
        return "/admin/lock";
    }
}
