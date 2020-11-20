package com.cxf.mblog.modules.service.impl;

import com.cxf.mblog.modules.entity.DictData;
import com.cxf.mblog.modules.repository.DictDataRepository;
import com.cxf.mblog.modules.service.DictDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author xfchai
 * @ClassName DictDataServiceImpl.java
 * @Description TODO
 * @createTime 2020/11/20 09:35:00
 */
@Service
@Transactional(readOnly = true)
public class DictDataServiceImpl implements DictDataService {
    @Autowired
    private DictDataRepository dictDataRepository;

    @Override
    public List<DictData> findAllByDictType(String dictType) {
        return dictDataRepository.findAllByDictType(dictType);
    }
}
