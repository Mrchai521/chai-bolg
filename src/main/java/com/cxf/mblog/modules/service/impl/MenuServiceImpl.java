package com.cxf.mblog.modules.service.impl;

import com.cxf.mblog.modules.data.MenuVO;
import com.cxf.mblog.modules.entity.Menu;
import com.cxf.mblog.modules.repository.MenuRepository;
import com.cxf.mblog.modules.service.MenuService;
import com.cxf.mblog.modules.service.UserEventService;
import com.cxf.mblog.web.menu.ZTree;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xfchai
 * @ClassName MenuServiceImpl.java
 * @Description 菜单实现类
 * @createTime 2020/12/25 15:44:00
 */
@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuRepository menuRepository;

    @Override
    public MenuVO selectMenuById(Long menuId) {
        Menu menu = menuRepository.getOne(menuId);
        MenuVO menuVO = new MenuVO();
        BeanUtils.copyProperties(menuVO, menu);
        return menuVO;
    }

    @Override
    public List<MenuVO> findMenuList(long id) {
        //判断是否为管理员用户
        List<Menu> menuList = menuRepository.findAll();
        List<MenuVO> menuVOList = new ArrayList<>();
        for (Menu menu : menuList) {
            MenuVO menuVO1 = new MenuVO();
            menuVO1.setId(menu.getId());
            menuVO1.setMenuName(menu.getMenuName());
            menuVO1.setUrl(menu.getUrl());
            menuVO1.setOrderNum(menu.getOrderNum());
            menuVOList.add(menuVO1);
        }
        return menuVOList;
    }

    @Override
    public List<ZTree> findAllList() {
        List<Menu> menuList = menuRepository.findAll();
        List<ZTree> zTreeList = initMenuTree(menuList);
        return zTreeList;
    }

    /**
     * 对象转菜单树
     *
     * @param menuList
     * @return
     */
    private List<ZTree> initMenuTree(List<Menu> menuList) {
        List<ZTree> zTreeList = new ArrayList<>();
        for (Menu menu : menuList) {
            ZTree zTree = new ZTree();
            zTree.setId(menu.getId());
            zTree.setPid(menu.getParentId());
            zTree.setName(transMenuName(menu));
            zTree.setTitle(menu.getMenuName());
            zTreeList.add(zTree);
        }
        return zTreeList;
    }

    /**
     * 转换菜单名称
     *
     * @param menu
     * @return
     */
    public String transMenuName(Menu menu) {
        StringBuffer sb = new StringBuffer();
        sb.append(menu.getMenuName());
        if (false) {
            sb.append("<font color=\"#888\">&nbsp;&nbsp;&nbsp;" + menu.getMenuName() + "</font>");
        }
        return sb.toString();
    }

    @Override
    public List<ZTree> findAllListByUserId(long userId) {
        return null;
    }
}
