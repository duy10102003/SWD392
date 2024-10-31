package com.swd392.group2.hms_outpatient_gr2.service;

import com.swd392.group2.hms_outpatient_gr2.model.entity.Department;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DepartmentService {
    Department getDepartmentByName(String name);
    List<Department> getDepartmentList();
    void addNewDepartment(Department department);
}
