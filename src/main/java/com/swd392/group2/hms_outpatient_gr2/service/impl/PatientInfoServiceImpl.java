package com.swd392.group2.hms_outpatient_gr2.service.impl;

import com.swd392.group2.hms_outpatient_gr2.model.dto.PatientInfoDto;
import com.swd392.group2.hms_outpatient_gr2.model.entity.PatientInfo;
import com.swd392.group2.hms_outpatient_gr2.repository.PatientInfoRepository;
import com.swd392.group2.hms_outpatient_gr2.service.PatientInfoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientInfoServiceImpl implements PatientInfoService{
    private final PatientInfoRepository patientInfoRepository;

    public PatientInfoServiceImpl(PatientInfoRepository patientInfoRepository) {
        this.patientInfoRepository = patientInfoRepository;
    }

    @Override
    public PatientInfo getPatientInfoById(Integer id) {
        return patientInfoRepository.findById(id).orElse(null);
    }

    @Override
    public List<PatientInfo> getPatientInfoList() {
        return patientInfoRepository.findAll();
    }

    @Override
    public PatientInfo addNewPatientInfo(PatientInfoDto patientInfoDto) {
        PatientInfo patientInfo = new PatientInfo();
        patientInfo.loadFromDto(patientInfoDto);
        return patientInfoRepository.save(patientInfo);
    }

    @Override
    public PatientInfo updatePatientInfo(PatientInfoDto patientInfoDto) {
        PatientInfo patientInfo = new PatientInfo();
        patientInfo.loadFromDto(patientInfoDto);
        return patientInfoRepository.save(patientInfo);
    }

    @Override
    public boolean deletePatientInfo(Integer id) {
        try {
            patientInfoRepository.deleteById(id);
        }catch (Exception e){
            return false;
        }
        return true;
    }
}
