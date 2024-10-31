package com.swd392.group2.hms_outpatient_gr2.service.impl;

import com.swd392.group2.hms_outpatient_gr2.model.entity.MedicalRecord;
import com.swd392.group2.hms_outpatient_gr2.repository.MedicalRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalRecordService {

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    // Lấy tất cả hồ sơ y tế
    public List<MedicalRecord> getAllMedicalRecords() {
        return medicalRecordRepository.findAll();
    }

    // Lấy hồ sơ y tế theo ID
    public MedicalRecord getMedicalRecordById(Integer id) {
        return medicalRecordRepository.findById(id).orElse(null);
    }
}
