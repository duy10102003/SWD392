package com.swd392.group2.hms_outpatient_gr2.model.dto;

import com.swd392.group2.hms_outpatient_gr2.model.entity.Department;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * DTO for {@link Department}
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DepartmentDto implements Serializable {

    @NotBlank(message = "Name is mandatory")
    String name;

    boolean isActive;

    public void loadFromEntity(Department entity) {
        this.name = entity.getName();
        this.isActive = entity.isActive();
    }
}