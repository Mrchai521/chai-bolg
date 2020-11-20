package com.cxf.mblog.modules.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author xfchai
 * @ClassName UserRole.java
 * @Description 用户角色映射表
 * @createTime 2020/11/17 10:34:00
 */
@Entity
@Table(name = "shiro_user_role")
@Data
public class UserRole implements Serializable {
    private static final long serialVersionUID = -2908144287976184011L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "role_id")
    private Long roleId;
}
