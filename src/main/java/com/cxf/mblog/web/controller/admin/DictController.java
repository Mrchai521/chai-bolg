package com.cxf.mblog.web.controller.admin;

import cn.hutool.core.lang.Dict;
import com.cxf.mblog.base.lang.Result;
import com.cxf.mblog.base.utils.SpringUtils;
import com.cxf.mblog.modules.data.CommentVO;
import com.cxf.mblog.modules.data.DictTypeVO;
import com.cxf.mblog.modules.entity.Channel;
import com.cxf.mblog.modules.entity.DictType;
import com.cxf.mblog.modules.service.DictDataService;
import com.cxf.mblog.modules.service.DictService;
import com.cxf.mblog.web.controller.BaseController;
import io.undertow.util.StatusCodes;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author xfchai
 * @ClassName DictController.java
 * @Description TODO
 * @createTime 2020/11/19 17:22:00
 */
@Controller("adminDictController")
@RequestMapping("/admin/dict")
public class    DictController extends BaseController {
    @Autowired
    private DictService dictService;
    @Autowired
    private DictDataService dictDataService;

    @RequestMapping("/list")
    public String list(ModelMap model, HttpServletRequest request) {
        Pageable pageable = wrapPageable();
        Page<DictTypeVO> page = dictService.paging4Admin(pageable);
        model.put("page", page);
        return "/admin/dict/list";
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Result delete(@RequestParam("id") List<Long> ids) {
        if (!CollectionUtils.isEmpty(ids)) {
            //id不为空，查询该字典分类里面有字典数据项。如果有不能删除
            int i = dictService.deleteDictTypeByIds(ids);
            return new Result(StatusCodes.OK, "删除成功", i);
        }
        return Result.failure("删除失败");
    }

    @RequestMapping("/view")
    public String view(@RequestParam("id") Long id, ModelMap model) {
        if (id != null) {
            DictType view = dictService.findById(id);
            model.put("view", view);
        }
        return "/admin/dict/view";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(DictTypeVO view) {
        if (!StringUtils.isEmpty(view)) {
            dictService.update(view);
        }
        return "redirect:/admin/dict/list";
    }

    @RequestMapping("/addView")
    public String view() {
        return "/admin/dict/add";
    }

    /**
     * 新增字典分类
     *
     * @param view
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(DictTypeVO view) {
        if (!StringUtils.isEmpty(view)) {
            dictService.save(view);
        }
        return "redirect:/admin/dict/list";
    }
}
