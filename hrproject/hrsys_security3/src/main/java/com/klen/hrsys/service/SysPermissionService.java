package com.klen.hrsys.service;


import com.klen.hrsys.entity.SysPermission;

import java.util.List;

public interface SysPermissionService {
    List<SysPermission> search();

    SysPermission searchById(Integer id);

    boolean update(SysPermission sysPermission);

    boolean delete(Integer id);

    boolean add(SysPermission sysPermission);
}
