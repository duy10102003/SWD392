package com.swd392.group2.hms_outpatient_gr2.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "medicine_prescription")
public class MedicinePrescription implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medicine_prescription_id")
    private Integer medicinePrescriptionID;

    @ManyToOne
    @JoinColumn(name = "medicine_invoice_id")
    private MedicineInvoice medicineInvoice;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Account doctor;

    @OneToMany(mappedBy = "medicinePrescription", cascade = CascadeType.ALL, orphanRemoval = true) // Đảm bảo cascade và orphanRemoval
    private List<MedicineItem> medicineItems = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "medical_record_id", nullable = false)
    private MedicalRecord medicalRecord;
}
