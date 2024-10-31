package com.swd392.group2.hms_outpatient_gr2.model.dto;


import com.swd392.group2.hms_outpatient_gr2.model.entity.PatientInfo;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Date;

/**
 * DTO for {PatientInfo}
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PatientInfoDto implements Serializable{
    @NotBlank(message = "This field is required.")
    @Size(min = 1, max = 255, message = "Name must be between 1 and 255 characters.")
    String name;

    @NotNull(message = "This field is required.")
    @PastOrPresent(message = "Date of birth must be in the past or present.")
    Date dateOfBirth;

    @NotNull(message = "Gender selection is required.")
    Boolean gender;

    @NotBlank(message = "This field is required.")
    @Size(min = 1, max = 255, message = "Address must be between 1 and 255 characters.")
    String address;

    @NotBlank(message = "This field is required.")
    @Pattern(regexp = "0\\d{9}", message = "Phone number must start with 0 and have 10 digits.")
    String phoneNumber;

    public void loadFromEntity(PatientInfo entity) {
        this.name = entity.getName();
        this.dateOfBirth = entity.getDateOfBirth();
        this.gender = entity.isGender();
        this.address = entity.getAddress();
        this.phoneNumber = entity.getPhoneNumber();
    }
}
