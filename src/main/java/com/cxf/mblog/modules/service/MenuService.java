package com.cxf.mblog.modules.service;

import com.cxf.mblog.modules.data.MenuVO;
import com.cxf.mblog.web.menu.ZTree;

import java.util.List;

/**
 * @author xfchai
 * @ClassName MenuService.java
 * @Description TODO
 * @createTime 2020/12/25 15:44:00
 */
public interface MenuService {
    /**
     * 通过menuid获取菜单
     *
     * @param menuId
     * @return
     */
    MenuVO selectMenuById(Long menuId);

    /**
     * 通过菜单vo和userId获取菜单
     *
     * @param menuVO
     * @param id
     * @return
     */
    List<MenuVO> findMenuList( long id);

    /**
     * 所有的树形结构
     *
     * @return
     */
    List<ZTree> findAllList();

    /**
     * 不同的用户获取不同的菜单
     *
     * @param userId
     * @return
     */
    List<ZTree> findAllListByUserId(long userId);
}
