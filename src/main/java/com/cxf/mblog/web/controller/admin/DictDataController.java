package com.cxf.mblog.web.controller.admin;

import com.cxf.mblog.base.lang.Result;
import com.cxf.mblog.modules.data.DictDataVO;
import com.cxf.mblog.modules.entity.DictData;
import com.cxf.mblog.modules.entity.DictType;
import com.cxf.mblog.modules.service.DictDataService;
import com.cxf.mblog.modules.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author xfchai
 * @ClassName DictDataController.java
 * @Description 字典数据项控制器
 * @createTime 2020/11/20 16:09:00
 */
@Controller("adminDictDataController")
@RequestMapping("/admin/dictdata")
public class DictDataController {
    @Autowired
    private DictService dictService;
    @Autowired
    private DictDataService dictDataService;

    @RequestMapping("/view")
    public String view(@RequestParam("id") Long id, ModelMap model) {
        if (id != null) {
            DictType dictType = dictService.findById(id);
            List<DictData> view = dictDataService.findAllByDictType(dictType.getDictType());
            model.put("view", view);
            model.put("id", id);
        }
        return "/admin/dictdata/view";
    }

    @RequestMapping("/addView")
    public String addView(@RequestParam("id") Long id, ModelMap model) {
        DictType dictType = dictService.findById(id);
        model.put("dictType", dictType);
        return "/admin/dictdata/add";
    }

    /**
     * 新增字典数据项
     *
     * @param view
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(DictDataVO view, ModelMap model) {
        if (!StringUtils.isEmpty(view)) {
            dictDataService.save(view);
        }
        model.put("id", view.getDictId());
        return "redirect:/admin/dictdata/view?id=" + view.getDictId();
    }

    /**
     * 通过ids批量删除
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Result delete(@RequestParam("id") List<Long> ids, Long dictId, ModelMap modelMap) {
        if (CollectionUtils.isEmpty(ids)) {
            Assert.state(false, "请选择需要删除的项目！");
            return Result.failure("删除失败");
        }
        //批量删除
        int i = dictDataService.delete(ids);
        this.view(dictId, modelMap);
        return Result.success("删除成功！", i);
    }

    @RequestMapping("/updateView")
    public String updateView(DictDataVO dictDataVO, ModelMap model) {
        if (dictDataVO != null) {
            DictType dictType = dictService.findById(dictDataVO.getDictId());
            DictData dictData = dictDataService.findById(dictDataVO.getId());
            model.put("dictType", dictType);
            model.put("dictData", dictData);
        }
        return "/admin/dictdata/update";
    }

    /**
     * 修改
     *
     * @param dictDataVO
     * @return
     */
    @RequestMapping("/update")
    public String update(DictDataVO dictDataVO) {
        if (!StringUtils.isEmpty(dictDataVO)) {
            //修改
            dictDataService.update(dictDataVO);
        }
        return "redirect:/admin/dictdata/view?id=" + dictDataVO.getDictId();
    }
}
