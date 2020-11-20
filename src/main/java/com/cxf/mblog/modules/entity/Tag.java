package com.cxf.mblog.modules.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author xfchai
 * @ClassName Tags.java
 * @Description TODO
 * @createTime 2020/11/17 10:36:00
 */
@Entity
@Table(name = "mto_tag")
@Data
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * 标签名称
     */
    @Column(unique = true, nullable = false, updatable = false, length = 32)
    private String name;

    /**
     * 预览图
     */
    @Column(length = 128)
    private String thumbnail;

    /**
     * 描述
     */
    private String description;

    /**
     * 最后发表的文章Id
     */
    private long latestPostId;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date created;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date updated;

    /**
     * 标签下的文章数
     */
    private int posts;
}
