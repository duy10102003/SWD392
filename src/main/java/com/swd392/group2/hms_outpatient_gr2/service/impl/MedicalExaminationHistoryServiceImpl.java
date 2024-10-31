package com.swd392.group2.hms_outpatient_gr2.service.impl;

import com.swd392.group2.hms_outpatient_gr2.model.dto.MedicalExaminationHistoryDto;
import com.swd392.group2.hms_outpatient_gr2.model.entity.*;
import com.swd392.group2.hms_outpatient_gr2.repository.AccountRepository;
import com.swd392.group2.hms_outpatient_gr2.repository.MedicalExaminationHistoryRepository;
import com.swd392.group2.hms_outpatient_gr2.repository.PatientInfoRepository;
import com.swd392.group2.hms_outpatient_gr2.service.MedicalExaminationHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalExaminationHistoryServiceImpl implements MedicalExaminationHistoryService {
    private final MedicalExaminationHistoryRepository medicalExaminationHistoryRepository;
    private final PatientInfoRepository patientInfoRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public MedicalExaminationHistoryServiceImpl(MedicalExaminationHistoryRepository medicalExaminationHistoryRepository,
                                                PatientInfoRepository patientInfoRepository,
                                                AccountRepository accountRepository) {
        this.medicalExaminationHistoryRepository = medicalExaminationHistoryRepository;
        this.patientInfoRepository = patientInfoRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public List<MedicalExaminationHistory> getMedicalExaminationHistoryList() {
        return medicalExaminationHistoryRepository.findAll();
    }

    @Override
    public boolean addNewMedicalExaminationHistory(MedicalExaminationHistoryDto medicalExaminationHistoryDto) {
        try {
            MedicalExaminationHistory medicalExaminationHistory = new MedicalExaminationHistory();
            medicalExaminationHistory.LoadFromDto(medicalExaminationHistoryDto);

            Account account = accountRepository.findById(medicalExaminationHistoryDto.getStaffId()).get();
            medicalExaminationHistory.setAccount(account);

            PatientInfo patientInfo = patientInfoRepository.findById(medicalExaminationHistoryDto.getPatientId()).get();
            medicalExaminationHistory.setPatientInfo(patientInfo);

            medicalExaminationHistoryRepository.save(medicalExaminationHistory);
            return true;

        } catch (Exception ex) {
            return false;
        }
    }
}
