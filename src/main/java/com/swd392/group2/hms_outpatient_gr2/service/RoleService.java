package com.swd392.group2.hms_outpatient_gr2.service;

import com.swd392.group2.hms_outpatient_gr2.model.entity.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {
    Role getRoleByName(String name);
    List<Role> getRoleList();
    void addNewRole(Role role);
}
