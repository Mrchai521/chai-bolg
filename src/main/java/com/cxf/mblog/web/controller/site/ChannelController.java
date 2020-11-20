/*
+--------------------------------------------------------------------------
|   Mblog [#RELEASE_VERSION#]
|   ========================================
|   Copyright (c) 2014, 2015 cxf. All Rights Reserved
|   http://www.cxf.com
|
+---------------------------------------------------------------------------
*/
package com.cxf.mblog.web.controller.site;

import com.cxf.mblog.base.lang.Consts;
import com.cxf.mblog.base.utils.MarkdownUtils;
import com.cxf.mblog.modules.data.PostVO;
import com.cxf.mblog.modules.entity.Channel;
import com.cxf.mblog.modules.service.ChannelService;
import com.cxf.mblog.modules.service.PostService;
import com.cxf.mblog.web.controller.BaseController;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Channel 主页
 *
 * @author langhsu
 */
@Controller
public class ChannelController extends BaseController {
    @Autowired
    private ChannelService channelService;
    @Autowired
    private PostService postService;

    @RequestMapping("/channel/{id}")
    public String channel(@PathVariable Integer id, ModelMap model,
                          HttpServletRequest request) {
        // init params
        String order = ServletRequestUtils.getStringParameter(request, "order", Consts.order.NEWEST);
        int pageNo = ServletRequestUtils.getIntParameter(request, "pageNo", 1);

        Channel channel = channelService.getById(id);
        // callback params
        model.put("channel", channel);
        model.put("order", order);
        model.put("pageNo", pageNo);
        return view(Views.POST_INDEX);
    }

    @RequestMapping("/post/{id:\\d*}")
    public String view(@PathVariable Long id, ModelMap model) {
        PostVO view = postService.get(id);

        Assert.notNull(view, "该文章已被删除");

        if ("markdown".endsWith(view.getEditor())) {
            PostVO post = new PostVO();
            BeanUtils.copyProperties(view, post);
            post.setContent(MarkdownUtils.renderMarkdown(view.getContent()));
            view = post;
        }
        postService.identityViews(id);
        model.put("view", view);
        return view(Views.POST_VIEW);
    }
}
