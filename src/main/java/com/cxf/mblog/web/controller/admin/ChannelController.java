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
import com.cxf.mblog.config.ContextStartup;
import com.cxf.mblog.modules.entity.Channel;
import com.cxf.mblog.modules.service.ChannelService;
import com.cxf.mblog.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author xfchai
 * @ClassName ChannelController.java
 * @Description ChannelController
 * @createTime 2021/09/06 09:38:00
 */
@Controller("adminChannelController")
@RequestMapping("/admin/channel")
public class ChannelController extends BaseController {
	@Autowired
	private ChannelService channelService;
	@Autowired
	private ContextStartup contextStartup;
	
	@RequestMapping("/list")
//	@RequiresPermissions("channel:list")
	public String list(ModelMap model) {
		model.put("list", channelService.findAll(Consts.IGNORE));
		return "/admin/channel/list";
	}
	
	@RequestMapping("/view")
	public String view(Integer id, ModelMap model) {
		if (id != null) {
			Channel view = channelService.getById(id);
			model.put("view", view);
		}
		return "/admin/channel/view";
	}
	
	@RequestMapping("/update") 
//	@RequiresPermissions("channel:update")
	public String update(Channel view) {
		if (view != null) {
			channelService.update(view);

			contextStartup.resetChannels();
		}
		return "redirect:/admin/channel/list";
	}

	@RequestMapping("/weight")
	@ResponseBody
	public Result weight(@RequestParam Integer id, HttpServletRequest request) {
		int weight = ServletRequestUtils.getIntParameter(request, "weight", Consts.FEATURED_ACTIVE);
		channelService.updateWeight(id, weight);
		contextStartup.resetChannels();
		return Result.success();
	}
	
	@RequestMapping("/delete")
	@ResponseBody
//	@RequiresPermissions("channel:delete")
	public Result delete(Integer id) {
		Result data = Result.failure("操作失败");
		if (id != null) {
			try {
				channelService.delete(id);
				data = Result.success();

				contextStartup.resetChannels();
			} catch (Exception e) {
				data = Result.failure(e.getMessage());
			}
		}
		return data;
	}
	
}
