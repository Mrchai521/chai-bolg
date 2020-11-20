package com.cxf.mblog.web.controller.site.user;

import com.cxf.mblog.modules.data.PostResult;
import com.cxf.mblog.modules.data.PostVO;
import com.cxf.mblog.modules.service.PostService;
import com.cxf.mblog.modules.service.UserService;
import com.cxf.mblog.web.controller.BaseController;
import com.cxf.mblog.web.controller.site.Views;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 归档模块控制器
 * @author:柴新峰
 * @create:2020/10/29
 */
@Controller
//@RestController
public class ArchiveController extends BaseController {
    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;

    /**
     * 获取归档信息
     *
     * @return
     */
    @RequestMapping(value = "/yearjson", method = RequestMethod.GET)
    public String searchArchive(String year, ModelMap model) {
        Long userId = this.getProfile().getId();
        if (StringUtils.isEmpty(year)) {
            Object[] object = null;
            List<Object[]> list = postService.findByAuthorIdAndCreated(userId);
            List<PostResult> data = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                PostResult postResult = new PostResult();
                object = list.get(i);
                postResult.setCount(Integer.parseInt(object[0].toString()));
                postResult.setYear(Integer.parseInt(object[1].toString()));
                data.add(postResult);
            }
            // 通过userId和时间进行归档
            model.put("data", data);
            return view(Views.ARCHIVE_INDEX);
//            return Result.success("查询归档成功！", data);
        } else {
            //查询该年份归档的数据
            List<PostVO> list = postService.findByCreated(year);
            //获取时间
            model.put("yearData", list);
//            return Result.success("查询归档成功！", list);
            return view(Views.ARCHIVE_VIEW);
        }
    }
}
