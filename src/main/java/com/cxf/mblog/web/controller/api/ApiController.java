/*
+--------------------------------------------------------------------------
|   Mblog [#RELEASE_VERSION#]
|   ========================================
|   Copyright (c) 2014, 2015 cxf. All Rights Reserved
|   http://www.cxf.com
|
+---------------------------------------------------------------------------
*/
package com.cxf.mblog.web.controller.api;

import com.cxf.mblog.base.lang.Consts;
import com.cxf.mblog.base.lang.Result;
import com.cxf.mblog.base.utils.BeanMapUtils;
import com.cxf.mblog.modules.data.AccountProfile;
import com.cxf.mblog.modules.data.CommentVO;
import com.cxf.mblog.modules.data.PostVO;
import com.cxf.mblog.modules.service.CommentService;
import com.cxf.mblog.modules.service.PostService;
import com.cxf.mblog.modules.service.UserService;
import com.cxf.mblog.web.controller.BaseController;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 侧边栏数据加载
 *
 * @author langhsu
 */
@RestController
@RequestMapping("/api")
public class ApiController extends BaseController {
    @Autowired
    private PostService postService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;

    @PostMapping(value = "/login")
    public Result login(String username, String password) {
        return executeLogin(username, password, false);
    }

    @RequestMapping("/posts")
    public Page<PostVO> posts(HttpServletRequest request) {
        String order = ServletRequestUtils.getStringParameter(request, "order", Consts.order.NEWEST);
        int channelId = ServletRequestUtils.getIntParameter(request, "channelId", 0);
        return postService.paging(wrapPageable(Sort.by(Sort.Direction.DESC, BeanMapUtils.postOrder(order))), channelId, null);
    }

    @RequestMapping(value = "/latest_comments")
    public List<CommentVO> latestComments(@RequestParam(name = "size", defaultValue = "6") Integer size) {
        return commentService.findLatestComments(size);
    }

    /**
     * 解锁屏幕
     *
     * @param password
     * @return
     */
    @RequestMapping(value = "/unlockscreen", method = RequestMethod.POST)
    public Result unlockscreen(String password) {
        Subject subject = SecurityUtils.getSubject();
        AccountProfile user = (AccountProfile) subject.getPrincipal();
        if (StringUtils.isEmpty(user)) {
            return Result.failure("服务器超时，请重新登陆");
        }
        if (userService.matches(user, password)) {
            SecurityUtils.getSubject().getSession().removeAttribute(Consts.LOCK_SCREEN);
            return Result.success();
        }
        return Result.failure("密码不正确，请重新输入！");
    }
}
