package com.cxf.mblog.modules.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author xfchai
 * @ClassName Menu.java
 * @Description TODO
 * @createTime 2020/12/25 11:38:00
 */
@Entity
@Table(name = "menu")
@Data
public class Menu {
    private static final long serialVersionUID = 1L;
    /**
     * 菜单ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 父菜单名称
     */
    private String parentName;

    /**
     * 父菜单ID
     */
    private Long parentId;

    /**
     * 显示顺序
     */
    private String orderNum;

    /**
     * 菜单URL
     */
    private String url;

    /**
     * 打开方式（menuItem页签 menuBlank新窗口）
     */
    private String target;

    /**
     * 类型（M目录 C菜单 F按钮）
     */
    private String menuType;

    /**
     * 菜单状态（0显示 1隐藏）
     */
    private String visible;

    /**
     * 是否刷新（0刷新 1不刷新）
     */
    private String isRefresh;

    /**
     * 权限字符串
     */
    private String perms;

    /**
     * 菜单图标
     */
    private String icon;
}
