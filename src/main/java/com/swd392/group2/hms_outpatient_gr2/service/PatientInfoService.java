package com.swd392.group2.hms_outpatient_gr2.service;

import com.swd392.group2.hms_outpatient_gr2.model.dto.PatientInfoDto;
import com.swd392.group2.hms_outpatient_gr2.model.entity.PatientInfo;

import java.util.List;

public interface PatientInfoService {
    PatientInfo getPatientInfoById(Integer id);
    List<PatientInfo> getPatientInfoList();
    PatientInfo addNewPatientInfo(PatientInfoDto patientInfo);
    PatientInfo updatePatientInfo(PatientInfoDto patientInfo);
    boolean deletePatientInfo(Integer id);
}
