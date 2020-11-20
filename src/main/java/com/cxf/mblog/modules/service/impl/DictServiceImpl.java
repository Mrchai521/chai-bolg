package com.cxf.mblog.modules.service.impl;

import com.cxf.mblog.base.lang.MtonsException;
import com.cxf.mblog.base.utils.BeanMapUtils;
import com.cxf.mblog.modules.data.DictTypeVO;
import com.cxf.mblog.modules.entity.DictType;
import com.cxf.mblog.modules.repository.DictDataRepository;
import com.cxf.mblog.modules.repository.DictTypeRepository;
import com.cxf.mblog.modules.service.DictService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

/**
 * @author xfchai
 * @ClassName DictServiceImpl.java
 * @Description TODO
 * @createTime 2020/11/19 17:28:00
 */
@Service
@Transactional(readOnly = true)
public class DictServiceImpl implements DictService {
    @Autowired
    private DictTypeRepository dictTypeRepository;
    @Autowired
    private DictDataRepository dictDataRepository;

    @Override
    public Page<DictTypeVO> paging4Admin(Pageable pageable) {
        Page<DictType> page = dictTypeRepository.findAll(pageable);
        List<DictTypeVO> list = new ArrayList<>();
        page.getContent().forEach(n -> list.add(BeanMapUtils.copy(n)));
        return new PageImpl<>(list, pageable, page.getTotalElements());
    }

    /**
     * 批量删除字典类型
     *
     * @param ids
     * @return
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public int deleteDictTypeByIds(List<Long> ids) {
        for (Long id : ids) {
            DictType dictType = dictTypeRepository.getOne(id);
            if (dictDataRepository.countByDictType(dictType.getDictType()) > 0) {
                //存在关联
                throw new MtonsException(String.format("%1$s已分配,不能删除", dictType.getDictName()));
            }
        }
        //否则就批量删除
        List<DictType> list = dictTypeRepository.removeByIdIn(ids);
        return (int) list.stream().count();
    }

    @Override
    public DictType findById(Long id) {
        return dictTypeRepository.findById(id).get();
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void update(DictType dictType) {
        Optional<DictType> option = dictTypeRepository.findById(dictType.getId());
        DictType po = option.orElse(new DictType());
        dictType.setCreateTime(po.getCreateTime());
        dictType.setUpdateTime(Calendar.getInstance().getTime());
        BeanUtils.copyProperties(dictType, po);
        dictTypeRepository.save(po);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void save(DictTypeVO view) {
        DictType dictType = new DictType();
        BeanUtils.copyProperties(view, dictType);
        dictType.setCreateTime(Calendar.getInstance().getTime());
        dictTypeRepository.save(dictType);
    }
}
