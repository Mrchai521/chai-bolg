package com.cxf.mblog.modules.repository;

import com.cxf.mblog.modules.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author xfchai
 * @ClassName MenuRepository.java
 * @Description TODO
 * @createTime 2020/12/25 15:48:00
 */
public interface MenuRepository extends JpaRepository<Menu,Long>, JpaSpecificationExecutor<Menu> {
}
