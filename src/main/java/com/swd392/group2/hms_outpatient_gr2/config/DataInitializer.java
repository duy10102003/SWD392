package com.swd392.group2.hms_outpatient_gr2.config;

import com.swd392.group2.hms_outpatient_gr2.model.dto.AccountDto;
import com.swd392.group2.hms_outpatient_gr2.model.entity.Department;
import com.swd392.group2.hms_outpatient_gr2.model.entity.Role;
import com.swd392.group2.hms_outpatient_gr2.service.AccountService;
import com.swd392.group2.hms_outpatient_gr2.service.DepartmentService;
import com.swd392.group2.hms_outpatient_gr2.service.RoleService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer {
    private final AccountService accountService;
    private final RoleService roleService;
    private final DepartmentService departmentService;

    @Autowired
    public DataInitializer(AccountService accountService,
                           RoleService roleService,
                           DepartmentService departmentService) {
        this.accountService = accountService;
        this.roleService = roleService;
        this.departmentService = departmentService;
    }

    @PostConstruct
    public void init() {
        createRoles();
        createDepartments();
        createAccount("ROLE_ADMIN", "Cardiology", "admin", "123");
        createAccount("ROLE_DOCTOR", "Neurology", "doctor", "123");
        createAccount("ROLE_CASHIER_COUNTER_STAFF", "Pediatrics", "cashier", "123");
        createAccount("ROLE_RECEPTION_COUNTER_STAFF", "Oncology", "reception", "123");
        createAccount("ROLE_PHARMACY_STAFF", "Orthopedics", "pharmacy", "123");
    }

    private void createRoles() {
        List<Role> roles = Arrays.asList(
                new Role(null, "ROLE_ADMIN", null),
                new Role(null, "ROLE_DOCTOR", null),
                new Role(null, "ROLE_CASHIER_COUNTER_STAFF", null),
                new Role(null, "ROLE_RECEPTION_COUNTER_STAFF", null),
                new Role(null, "ROLE_PHARMACY_STAFF", null)
        );

        for (Role role : roles) {
            if (roleService.getRoleByName(role.getName()) == null) {
                roleService.addNewRole(role);
            }
        }
    }

    private void createDepartments() {
        List<Department> departments = Arrays.asList(
                new Department(null, "Cardiology", true, null),
                new Department(null, "Neurology", true, null),
                new Department(null, "Pediatrics", true, null),
                new Department(null, "Oncology", true, null),
                new Department(null, "Orthopedics", true, null)
        );

        for (Department department : departments) {
            if (departmentService.getDepartmentByName(department.getName()) == null) {
                departmentService.addNewDepartment(department);
            }
        }
    }

    private void createAccount(String roleName, String departmentName, String username, String password) {
        AccountDto accountDto = new AccountDto();
        accountDto.setUsername(username);
        accountDto.setPassword(password);
        accountDto.setRoleName(roleName);
        accountDto.setDepartmentName(departmentName);
        accountDto.setGender(true);
        accountDto.setActive(true);

        if (accountService.getAccountByUsername(accountDto.getUsername()) == null) {
            accountService.addNewAccount(accountDto);
        }
    }
}