package com.cxf.mblog.modules.data;

import com.cxf.mblog.modules.entity.DictData;
import lombok.Data;

import java.io.Serializable;

/**
 * @author xfchai
 * @ClassName DictDataVO.java
 * @Description TODO
 * @createTime 2020/11/19 17:45:00
 */
@Data
public class DictDataVO extends DictData implements Serializable {
    private Long dictId;
}
