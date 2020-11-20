package com.cxf.mblog.modules.entity;

import lombok.Data;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.Filters;
import org.hibernate.search.annotations.*;

import javax.persistence.*;
import javax.persistence.Index;
import java.io.Serializable;
import java.util.Date;

/**
 * @author xfchai
 * @ClassName Post.java
 * @Description 内容表
 * @createTime 2020/11/17 10:51:00
 */
@Entity
@Table(name = "mto_post", indexes = {
        @Index(name = "IK_CHANNEL_ID", columnList = "channel_id")
})
@FilterDefs({
        @FilterDef(name = "POST_STATUS_FILTER", defaultCondition = "status = 0")})
@Filters({@Filter(name = "POST_STATUS_FILTER")})
@Indexed(index = "post")
@Analyzer(impl = SmartChineseAnalyzer.class)
@Data
public class Post implements Serializable {
    private static final long serialVersionUID = 7144425803920583495L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SortableField
    @NumericField
    private long id;

    /**
     * 分组/模块ID
     */
    @Field
    @NumericField
    @Column(name = "channel_id", length = 5)
    private int channelId;

    /**
     * 标题
     */
    @Field
    @Column(name = "title", length = 64)
    private String title;

    /**
     * 摘要
     */
    @Field
    @Column(length = 140)
    private String summary;

    /**
     * 预览图
     */
    @Column(length = 128)
    private String thumbnail;

    /**
     * 标签, 多个逗号隔开
     */
    @Field
    @Column(length = 64)
    private String tags;

    /**
     * 作者Id
     */
    @Field
    @NumericField
    @Column(name = "author_id")
    private long authorId;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date created;

    /**
     * 收藏数
     */
    private int favors;

    /**
     * 评论数
     */
    private int comments;

    /**
     * 阅读数
     */
    private int views;

    /**
     * 文章状态
     */
    private int status;

    /**
     * 推荐状态
     */
    private int featured;

    /**
     * 排序值
     */
    private int weight;
}
