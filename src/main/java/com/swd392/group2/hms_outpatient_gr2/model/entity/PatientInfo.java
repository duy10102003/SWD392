package com.swd392.group2.hms_outpatient_gr2.model.entity;

import com.swd392.group2.hms_outpatient_gr2.model.dto.PatientInfoDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "patient_info")
public class PatientInfo implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(nullable = false)
    @Nationalized
    private String name;

    @Column(nullable = false)
    private Date dateOfBirth;

    @Column(nullable = false)
    private boolean gender;

    @Column(nullable = false)
    @Nationalized
    private String address;

    @Column(nullable = false)
    private String phoneNumber;

    @OneToOne(mappedBy = "patientInfo", cascade = CascadeType.ALL)
    private HealthInsurance healthInsurance;

    @OneToMany(mappedBy = "patientInfo", cascade = CascadeType.ALL)
    private Collection<MedicalExaminationHistory> medicalExaminationHistories;

    public void loadFromDto(PatientInfoDto dto) {
        this.name = dto.getName();
        this.dateOfBirth = dto.getDateOfBirth();
        this.gender = dto.getGender();
        this.address = dto.getAddress();
        this.phoneNumber = dto.getPhoneNumber();
    }
}
