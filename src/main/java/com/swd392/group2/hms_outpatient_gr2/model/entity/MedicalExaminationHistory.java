package com.swd392.group2.hms_outpatient_gr2.model.entity;

import com.swd392.group2.hms_outpatient_gr2.model.dto.MedicalExaminationHistoryDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.io.Serializable;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "medical_examination_history")
public class MedicalExaminationHistory implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(nullable = false)
    @Nationalized
    private String examinationDescription;

    @Column(nullable = false)
    private Date createDate;

    @ManyToOne
    @JoinColumn(name = "reception_counter_staff_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private PatientInfo patientInfo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "medical_record_id")
    private MedicalRecord medicalRecord;

    public void LoadFromDto(MedicalExaminationHistoryDto mehDto) throws ParseException {
        this.examinationDescription = mehDto.getDescription();
        this.patientInfo = mehDto.getPatientInfo();
        this.account = mehDto.getAccount();
        String dateString = mehDto.getCreatedDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date utilDate = sdf.parse(dateString);
        this.createDate = new Date(utilDate.getTime());
        this.medicalRecord = mehDto.getMedicalRecord();
    }
}
