/*
+--------------------------------------------------------------------------
|   Mblog [#RELEASE_VERSION#]
|   ========================================
|   Copyright (c) 2014, 2015 mtons. All Rights Reserved
|   http://www.mtons.com
|
+---------------------------------------------------------------------------
*/
package com.cxf.mblog.modules.repository;

import com.cxf.mblog.modules.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author langhsu
 */
public interface PostRepository extends JpaRepository<Post, Long>, JpaSpecificationExecutor<Post> {
    /**
     * 查询指定用户
     *
     * @param pageable
     * @param authorId
     * @return
     */
    Page<Post> findAllByAuthorId(Pageable pageable, long authorId);

    @Query("select coalesce(max(weight), 0) from Post")
    int maxWeight();

    @Modifying
    @Query("update Post set views = views + :increment where id = :id")
    void updateViews(@Param("id") long id, @Param("increment") int increment);

    @Modifying
    @Query("update Post set favors = favors + :increment where id = :id")
    void updateFavors(@Param("id") long id, @Param("increment") int increment);

    @Modifying
    @Query("update Post set comments = comments + :increment where id = :id")
    void updateComments(@Param("id") long id, @Param("increment") int increment);

    @Modifying
    @Query(value = "SELECT count(mto_post.created) as count,DATE_FORMAT(created,'%Y') as `year`  FROM mto_post  WHERE author_id= :author_id GROUP BY `year`  ORDER BY `year` DESC", nativeQuery = true)
    List<Object[]> findAuthorIdAndCreated(@Param("author_id") long author_id);

    @Modifying
    @Query(value = "SELECT * FROM `mto_post` where   DATE_FORMAT(created,'%Y') = :year", nativeQuery = true)
    List<Post> findByCreated(@Param("year") String year);
}
