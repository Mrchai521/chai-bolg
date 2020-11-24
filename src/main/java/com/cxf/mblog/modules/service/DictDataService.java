package com.cxf.mblog.modules.service;

import com.cxf.mblog.modules.data.DictDataVO;
import com.cxf.mblog.modules.entity.DictData;

import java.util.List;

/**
 * @author xfchai
 * @ClassName DictDataService.java
 * @Description TODO
 * @createTime 2020/11/20 09:34:00
 */
public interface DictDataService {
    /**
     * 通过字典分类名称查询
     *
     * @param dictType
     * @return
     */
    List<DictData> findAllByDictType(String dictType);

    /**
     * 保存
     *
     * @param view
     */
    void save(DictDataVO view);

    /**
     * 通过id批量删除
     *
     * @param ids
     * @return
     */
    int delete(List<Long> ids);

    /**
     * 修改
     * @param dictDataVO
     */
    void update(DictDataVO dictDataVO);

    /**
     * 通过id查询
     * @param id
     * @return
     */
    DictData findById(Long id);
}
