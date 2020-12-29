package com.cxf.mblog.web.menu;

import lombok.Data;

import java.io.Serializable;

/**
 * @author xfchai
 * @ClassName ZTree.java
 * @Description Ztree树结构实体类
 * @createTime 2020/12/28 14:13:00
 */
@Data
public class ZTree implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 节点ID
     */
    private Long id;

    /**
     * 节点父ID
     */
    private Long pid;

    /**
     * 节点名称
     */
    private String name;

    /**
     * 节点标题
     */
    private String title;

    /**
     * 是否勾选
     */
    private boolean checked = false;

    /**
     * 是否展开
     */
    private boolean open = false;

    /**
     * 是否能勾选
     */
    private boolean nocheck = false;
}
