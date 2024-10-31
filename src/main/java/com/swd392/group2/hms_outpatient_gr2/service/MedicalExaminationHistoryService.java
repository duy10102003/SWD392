package com.swd392.group2.hms_outpatient_gr2.service;

import com.swd392.group2.hms_outpatient_gr2.model.dto.MedicalExaminationHistoryDto;
import com.swd392.group2.hms_outpatient_gr2.model.entity.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MedicalExaminationHistoryService {
    List<MedicalExaminationHistory> getMedicalExaminationHistoryList();
    boolean addNewMedicalExaminationHistory(MedicalExaminationHistoryDto medicalExaminationHistoryDto);
}
