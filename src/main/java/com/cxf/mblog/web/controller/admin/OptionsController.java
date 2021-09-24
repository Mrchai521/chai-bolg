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

import com.cxf.mblog.base.lang.Result;
import com.cxf.mblog.config.ContextStartup;
import com.cxf.mblog.modules.service.OptionsService;
import com.cxf.mblog.modules.service.PostSearchService;
import com.cxf.mblog.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @author xfchai
 * @ClassName OptionsController.java
 * @Description OptionsController系统配置
 * @createTime 2021/09/06 09:38:00
 */
@Controller
@RequestMapping("/admin/options")
public class OptionsController extends BaseController {
    @Autowired
    private OptionsService optionsService;
    @Autowired
    private PostSearchService postSearchService;
    @Autowired
    private ContextStartup contextStartup;

    @RequestMapping("/index")
    public String index(ModelMap model) {
        return "/admin/options/index";
    }

    @RequestMapping("/update")
    public String update(@RequestParam Map<String, String> body, ModelMap model) {
        optionsService.update(body);
        contextStartup.reloadOptions(false);
        model.put("data", Result.success());
        return "/admin/options/index";
    }

    /**
     * 刷新系统变量
     *
     * @return
     */
    @RequestMapping("/reload_options")
    @ResponseBody
    public Result reloadOptions() {
        contextStartup.reloadOptions(false);
        contextStartup.resetChannels();
        return Result.success();
    }

    @RequestMapping("/reset_indexes")
    @ResponseBody
    public Result resetIndexes() {
        postSearchService.resetIndexes();
        return Result.success();
    }
}
