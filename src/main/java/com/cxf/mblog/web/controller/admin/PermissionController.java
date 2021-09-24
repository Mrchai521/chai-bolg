package com.cxf.mblog.web.controller.admin;

import com.cxf.mblog.modules.data.PermissionTree;
import com.cxf.mblog.modules.service.PermissionService;
import com.cxf.mblog.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author xfchai
 * @ClassName PermissionController.java
 * @Description PermissionController
 * @createTime 2021/09/06 09:38:00
 */
@RestController
@RequestMapping("/admin/permission")
public class PermissionController extends BaseController {
    @Autowired
    private PermissionService permissionService;

    @GetMapping("/tree")
    public List<PermissionTree> tree() {
        return permissionService.tree();
    }
}
