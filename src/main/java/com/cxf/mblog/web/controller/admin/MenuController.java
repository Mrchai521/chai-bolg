package com.cxf.mblog.web.controller.admin;

import com.cxf.mblog.modules.data.AccountProfile;
import com.cxf.mblog.modules.data.MenuVO;
import com.cxf.mblog.modules.entity.DictData;
import com.cxf.mblog.modules.entity.Menu;
import com.cxf.mblog.modules.service.DictDataService;
import com.cxf.mblog.modules.service.MenuService;
import com.cxf.mblog.shiro.ShiroUtils;
import com.cxf.mblog.web.menu.ZTree;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author xfchai
 * @ClassName MenuController.java
 * @Description TODO
 * @createTime 2020/12/24 16:25:00
 */
@Controller
@RequestMapping("/admin/menu")
public class MenuController {
    private static final String prefix ="admin/menu";

    @Autowired
    private DictDataService dataService;
    @Autowired
    private MenuService menuService;

    @RequestMapping("/index")
    public String index(ModelMap modelMap) {
        List<DictData> dictData = dataService.findAllByDictType("sys_show_hide");
        modelMap.put("dict", dictData);
        return prefix+"/index";
    }

    @RequestMapping("/add")
    public String add(ModelMap modelMap) {
        return prefix+"/add";
    }
    @PostMapping("/list")
    @ResponseBody
    public List<MenuVO> list(MenuVO menuVO){
        //获取登录用户
        Subject subject = SecurityUtils.getSubject();
        AccountProfile user = (AccountProfile) subject.getPrincipal();
        List<MenuVO> list = menuService.findMenuList(menuVO,user.getId());
        return list;
    }
    /**
     * 选择菜单树
     */
    @GetMapping("/selectMenuTree/{menuId}")
    public String selectMenuTree(@PathVariable("menuId") Long menuId, ModelMap mmap) {
        mmap.put("menu", menuService.selectMenuById(menuId));
        return prefix + "/tree";
    }

    @GetMapping("/treeList")
    @ResponseBody
    public List<ZTree> treeList(ModelMap modelMap){
        AccountProfile accountProfile = ShiroUtils.checkAccount();
        List<ZTree> list = null;

        if(StringUtils.isEmpty(accountProfile.getId())){
            list = menuService.findAllList();
        }
        list = menuService.findAllListByUserId(accountProfile.getId());
        return list;
    }
}
