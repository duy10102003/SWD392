package com.swd392.group2.hms_outpatient_gr2.service;


import com.swd392.group2.hms_outpatient_gr2.model.dto.MedicalExaminationHistoryDto;
import org.springframework.stereotype.Service;

@Service
public interface MedicalExaminationHistoryValidationService {
    String validateFields(MedicalExaminationHistoryDto medicalExaminationHistoryDto);
}
