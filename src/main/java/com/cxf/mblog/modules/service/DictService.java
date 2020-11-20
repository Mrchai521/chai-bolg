package com.cxf.mblog.modules.service;

import com.cxf.mblog.modules.data.CommentVO;
import com.cxf.mblog.modules.data.DictTypeVO;
import com.cxf.mblog.modules.entity.DictType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author xfchai
 * @ClassName DictService.java
 * @Description TODO
 * @createTime 2020/11/19 17:28:00
 */
public interface DictService {
    Page<DictTypeVO> paging4Admin(Pageable pageable);

    int deleteDictTypeByIds(List<Long> ids);

    /**
     * 通过id查询dicttype
     *
     * @param id
     * @return
     */
    DictType findById(Long id);

    /**
     * 修改字典分类
     *
     * @param dictType
     */
    void update(DictType dictType);

    /**
     * 新增字典分类
     *
     * @param view
     */
    void save(DictTypeVO view);
}
