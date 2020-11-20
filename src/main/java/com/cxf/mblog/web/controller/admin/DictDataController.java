package com.cxf.mblog.web.controller.admin;

import com.cxf.mblog.modules.entity.DictData;
import com.cxf.mblog.modules.entity.DictType;
import com.cxf.mblog.modules.service.DictDataService;
import com.cxf.mblog.modules.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author xfchai
 * @ClassName DictDataController.java
 * @Description TODO
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
        }
        return "/admin/dictdata/view";
    }
}
