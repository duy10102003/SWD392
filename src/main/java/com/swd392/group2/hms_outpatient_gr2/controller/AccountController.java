package com.swd392.group2.hms_outpatient_gr2.controller;

import com.swd392.group2.hms_outpatient_gr2.model.dto.AccountDto;
import com.swd392.group2.hms_outpatient_gr2.model.entity.Account;
import com.swd392.group2.hms_outpatient_gr2.model.entity.Department;
import com.swd392.group2.hms_outpatient_gr2.model.entity.Role;
import com.swd392.group2.hms_outpatient_gr2.service.AccountService;
import com.swd392.group2.hms_outpatient_gr2.service.DepartmentService;
import com.swd392.group2.hms_outpatient_gr2.service.RoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/account")
public class AccountController {
    private final AccountService accountService;
    private final RoleService roleService;
    private final DepartmentService departmentService;

    @Autowired
    public AccountController(AccountService accountService,
                             RoleService roleService,
                             DepartmentService departmentService) {
        this.accountService = accountService;
        this.roleService = roleService;
        this.departmentService = departmentService;
    }

    @GetMapping("/list")
    public String ShowAccountList(Model model) {
        List<Account> accountList = accountService.getAccountList();
        model.addAttribute("accountList", accountList);

        return "account/list";
    }

    @GetMapping("/detail")
    public String viewAccountDetail(@RequestParam("id") Integer accountId, Model model) {
        Account account = accountService.getAccountById(accountId);
        AccountDto accountDto = new AccountDto();
        accountDto.loadFromEntity(account);
        model.addAttribute("accountDto", accountDto);

        return "account/detail";
    }

    @GetMapping("/add")
    public String addNewAccount(Model model) {
        AccountDto accountDto = new AccountDto();
        model.addAttribute("accountDto", accountDto);

        List<Role> roleList = roleService.getRoleList();
        model.addAttribute("roleList", roleList);

        List<Department> departmentList = departmentService.getDepartmentList();
        model.addAttribute("departmentList", departmentList);

        return "account/add";
    }

    @PostMapping("/add")
    public String addNewAccount(@Valid @ModelAttribute("accountDto") AccountDto accountDto,
                            BindingResult result,
                            Model model) {
        model.addAttribute("accountDto", accountDto);

        List<Role> roleList = roleService.getRoleList();
        model.addAttribute("roleList", roleList);

        List<Department> departmentList = departmentService.getDepartmentList();
        model.addAttribute("departmentList", departmentList);

        if (result.hasErrors()) {
            return "account/add";
        } else {
            if (accountService.getAccountByUsername(accountDto.getUsername()) == null){
                accountDto.setActive(true);
                accountService.addNewAccount(accountDto);
                return "redirect:/account/list?addSuccess";
            } else {
                model.addAttribute("errorMessage", "Account already exists.");
                return "account/add";
            }
        }
    }
}
