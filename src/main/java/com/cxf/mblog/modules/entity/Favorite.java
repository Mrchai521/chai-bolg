package com.cxf.mblog.modules.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author xfchai
 * @ClassName Favorite.java
 * @Description 喜欢/收藏
 * @createTime 2020/11/17 10:43:00
 */
@Entity
@Table(name = "mto_favorite", indexes = {
        @Index(name = "IK_USER_ID", columnList = "user_id")
})
@Data
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * 所属用户
     */
    @Column(name = "user_id")
    private long userId;

    /**
     * 内容ID
     */
    @Column(name = "post_id")
    private long postId;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
}
