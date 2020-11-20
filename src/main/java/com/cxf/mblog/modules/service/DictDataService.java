package com.cxf.mblog.modules.service;

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
}
