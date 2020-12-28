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
    public List<MenuVO> findMenuList(MenuVO menuVO, long id) {
        //判断是否为管理员用户
        List<Menu> menuList = menuRepository.findAll();
        List<MenuVO> menuVOList = new ArrayList<>();
        for (Menu menu : menuList) {
            MenuVO menuVO1 = new MenuVO();
            BeanUtils.copyProperties(menuVO1, menu);
            menuVOList.add(menuVO1);
        }
        return menuVOList;
    }

    @Override
    public List<ZTree> findAllList() {
        return null;
    }

    @Override
    public List<ZTree> findAllListByUserId(long userId) {
        return null;
    }
}
