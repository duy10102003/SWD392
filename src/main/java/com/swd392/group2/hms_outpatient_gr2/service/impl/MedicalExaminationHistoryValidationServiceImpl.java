package com.swd392.group2.hms_outpatient_gr2.service.impl;

import com.swd392.group2.hms_outpatient_gr2.model.dto.MedicalExaminationHistoryDto;
import com.swd392.group2.hms_outpatient_gr2.repository.AccountRepository;
import com.swd392.group2.hms_outpatient_gr2.repository.MedicalRecordRepository;
import com.swd392.group2.hms_outpatient_gr2.repository.PatientInfoRepository;
import com.swd392.group2.hms_outpatient_gr2.service.MedicalExaminationHistoryValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MedicalExaminationHistoryValidationServiceImpl implements MedicalExaminationHistoryValidationService {
    private final AccountRepository accountRepository;
    private final PatientInfoRepository patientInfoRepository;
    private final MedicalRecordRepository medicalRecordRepository;

    @Autowired
    public MedicalExaminationHistoryValidationServiceImpl(AccountRepository accountRepository,
                                                          PatientInfoRepository patientInfoRepository,
                                                          MedicalRecordRepository medicalRecordRepository) {
        this.accountRepository = accountRepository;
        this.patientInfoRepository = patientInfoRepository;
        this.medicalRecordRepository = medicalRecordRepository;
    }

    @Override
    public String validateFields(MedicalExaminationHistoryDto medicalExaminationHistoryDto) {
        List<String> errors = new ArrayList<>();
        if (medicalExaminationHistoryDto.getStaffId() == 0 || accountRepository.findById(medicalExaminationHistoryDto.getStaffId()).isEmpty()) {
            errors.add("Staff is not exist!");
        }
        if (medicalExaminationHistoryDto.getDescription().trim().isBlank()) {
            errors.add("Description can not be blank!");
        }
        if (medicalExaminationHistoryDto.getCreatedDate().trim().isBlank()) {
            errors.add("Created date can not be blank!");
        }
        if (medicalExaminationHistoryDto.getPatientId() == 0 || patientInfoRepository.findById(medicalExaminationHistoryDto.getPatientId()).isEmpty()) {
            errors.add("Patient is not exist!");
        }
        StringBuilder result = new StringBuilder();
        if (errors.isEmpty()) {
            return "success || Add Medical Examination History Successfully!";
        } else {
            result.append("error || ");
            for (int i = 0; i < errors.size(); i++) {
                result.append(errors.get(i)).append(" || ");
            }
        }
        return result.toString();
    }
}
