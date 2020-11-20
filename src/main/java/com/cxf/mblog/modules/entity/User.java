package com.cxf.mblog.modules.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author xfchai
 * @ClassName User.java
 * @Description 用户信息
 * @createTime 2020/11/17 10:10:00
 */
@Entity
@Table(name = "mto_user")
@NoArgsConstructor
@Data
public class User implements Serializable {
    private static final long serialVersionUID = -3629784071225214858L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    /**
     * 用户名
     */
    @Column(name = "username", unique = true, nullable = false, length = 64)
    private String username;
    /**
     * 密码
     */
    @Column(name = "password", length = 64)
    private String password;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 性别
     */
    private int gender;
    /**
     * 昵称
     */
    @Column(name = "name",length = 64)
    private String name;
    /**
     * 邮箱
     */
    @Column(name = "email",unique = true,length = 64)
    private String email;
    /**
     * 文章数
     */
    private int posts;
    /**
     * 发布评论数
     */
    private int comments;

    /**
     * 注册时间
     */
    private Date created;

    /**
     * 最后登录时间
     */
    @Column(name = "last_login")
    private Date lastLogin;

    /**
     * 个性签名
     */
    private String signature;

    /**
     * 用户状态
     */
    private int status;
}
