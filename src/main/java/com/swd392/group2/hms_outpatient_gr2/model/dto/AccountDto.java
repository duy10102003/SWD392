package com.swd392.group2.hms_outpatient_gr2.model.dto;

import com.swd392.group2.hms_outpatient_gr2.model.entity.Account;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Date;

/**
 * DTO for {@link Account}
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountDto implements Serializable {
    @NotBlank(message = "Username is mandatory")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String username;

    @NotBlank(message = "Password is mandatory")
    //@Size(min = 8, message = "Password must be at least 8 characters")
    private String password;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotNull(message = "Date of birth is mandatory")
    @Past(message = "Date of birth must be in the past")
    private Date dateOfBirth;

    private boolean gender;

    @NotBlank(message = "Address is mandatory")
    private String address;

    @NotBlank(message = "Phone number is mandatory")
    @Pattern(regexp = "^\\+?[0-9. ()-]{7,25}$", message = "Phone number is invalid")
    private String phoneNumber;

    @NotBlank(message = "Role name is mandatory")
    private String roleName;

    @NotBlank(message = "Department name is mandatory")
    private String departmentName;

    private boolean isActive;

    public void loadFromEntity(Account entity) {
        this.username = entity.getUsername();
        this.password = entity.getPassword();
        this.name = entity.getName();
        this.dateOfBirth = entity.getDateOfBirth();
        this.gender = entity.isGender();
        this.address = entity.getAddress();
        this.phoneNumber = entity.getPhoneNumber();
        this.roleName = entity.getRole().getName();
        if (entity.getDepartment() != null) {
            this.departmentName = entity.getDepartment().getName();
        } else {
            this.departmentName = "No Department";
        }
        this.isActive = entity.isActive();
    }
}