package com.cxf.mblog.modules.data;

import com.cxf.mblog.modules.entity.Menu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xfchai
 * @ClassName MenuVO.java
 * @Description TODO
 * @createTime 2020/12/25 11:41:00
 */
public class MenuVO extends Menu implements Serializable {
    /**
     * 子菜单
     */
    private List<Menu> children = new ArrayList<Menu>();
}
