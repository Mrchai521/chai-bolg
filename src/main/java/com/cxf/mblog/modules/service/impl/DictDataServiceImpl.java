package com.cxf.mblog.modules.service.impl;

import com.cxf.mblog.modules.data.DictDataVO;
import com.cxf.mblog.modules.entity.DictData;
import com.cxf.mblog.modules.repository.DictDataRepository;
import com.cxf.mblog.modules.service.DictDataService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

/**
 * @author xfchai
 * @ClassName DictDataServiceImpl.java
 * @Description 字典数据项业务
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

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void save(DictDataVO view) {
        DictData dictData = new DictData();
        BeanUtils.copyProperties(view, dictData);
        dictData.setCreateTime(Calendar.getInstance().getTime());
        dictDataRepository.save(dictData);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public int delete(List<Long> ids) {
        List<DictData> list = dictDataRepository.removeByIdIn(ids);
        return 0;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void update(DictDataVO dictDataVO) {
        Optional<DictData> optional = dictDataRepository.findById(dictDataVO.getId());
        DictData dictData = optional.orElse(new DictData());
        dictData.setCreateTime(dictData.getCreateTime());
        BeanUtils.copyProperties(dictDataVO,dictData);
        dictData.setUpdateTime(Calendar.getInstance().getTime());
        dictDataRepository.save(dictData);
    }

    @Override
    public DictData findById(Long id) {
        return dictDataRepository.getOne(id);
    }
}
