package com.cxf.mblog.modules.repository;

import com.cxf.mblog.modules.entity.Comment;
import com.cxf.mblog.modules.entity.DictType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

/**
 * @author xfchai
 * @ClassName DictTypeRepository.java
 * @Description TODO
 * @createTime 2020/11/19 17:50:00
 */
public interface DictTypeRepository extends JpaRepository<DictType, Long>, JpaSpecificationExecutor<DictType> {
    @Query(value = "delete  from dict_type where dict_type.id in (:ids)", nativeQuery = true)
    int removeById(@Param("ids") Collection<Long> ids);

    @Modifying
    @Query(value = "update dict_type set dict_type=:dicttype where id=:id", nativeQuery = true)
    void update(@Param("dicttype") String dicttype, @Param("id") Long id);

    List<DictType> removeByIdIn(Collection<Long> ids);
}
