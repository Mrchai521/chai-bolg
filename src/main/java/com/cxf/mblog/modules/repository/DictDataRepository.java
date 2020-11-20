package com.cxf.mblog.modules.repository;

import com.cxf.mblog.modules.entity.DictData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author xfchai
 * @ClassName DictDataRepository.java
 * @Description TODO
 * @createTime 2020/11/20 09:50:00
 */
public interface DictDataRepository extends JpaRepository<DictData, Long>, JpaSpecificationExecutor<DictData> {
    @Query(value = "select count(1) from dict_data where dict_type=:dictType", nativeQuery = true)
    int countByDictType(@Param("dictType") String dictType);

    List<DictData> findAllByDictType(String dictType);
}
