package com.swd392.group2.hms_outpatient_gr2.controller;


import com.swd392.group2.hms_outpatient_gr2.model.dto.MedicalExaminationHistoryDto;
import com.swd392.group2.hms_outpatient_gr2.model.entity.MedicalExaminationHistory;
import com.swd392.group2.hms_outpatient_gr2.service.AccountService;
import com.swd392.group2.hms_outpatient_gr2.service.MedicalExaminationHistoryService;
import com.swd392.group2.hms_outpatient_gr2.service.MedicalExaminationHistoryValidationService;
import com.swd392.group2.hms_outpatient_gr2.service.PatientInfoService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/medical-examination-history")
public class MedicalExaminationHistoryController {
    private final MedicalExaminationHistoryService medicalExaminationHistoryService;
    private final MedicalExaminationHistoryValidationService medicalExaminationHistoryValidationService;
    private final AccountService accountService;
    private final PatientInfoService patientInfoService;

    public MedicalExaminationHistoryController(MedicalExaminationHistoryService medicalExaminationHistoryService,
                                               MedicalExaminationHistoryValidationService medicalExaminationHistoryValidationService,
                                               AccountService accountService,
                                               PatientInfoService patientInfoService) {
        this.medicalExaminationHistoryService = medicalExaminationHistoryService;
        this.medicalExaminationHistoryValidationService = medicalExaminationHistoryValidationService;
        this.accountService = accountService;
        this.patientInfoService = patientInfoService;
    }

    @GetMapping("/list")
    public String showAll(Model model) {
        List<MedicalExaminationHistory> examinationList = medicalExaminationHistoryService.getMedicalExaminationHistoryList();
        MedicalExaminationHistoryDto[] examinationDtoList = new MedicalExaminationHistoryDto[examinationList.size()];
        for (int i = 0; i < examinationList.size(); i++) {
            examinationDtoList[i] = new MedicalExaminationHistoryDto();
            examinationDtoList[i].loadFromEntity(examinationList.get(i));
        }
        model.addAttribute("examinationList", examinationDtoList);
        return "medical-examination-history/list";
    }

    @GetMapping("/add")
    public String addNewMedicalExaminationHistory(Model model) {
        MedicalExaminationHistoryDto medicalExaminationHistoryDto = new MedicalExaminationHistoryDto();
        medicalExaminationHistoryDto.setStaffId(accountService.getUserDetail().getAccount().getId());
        model.addAttribute("medicalExaminationHistoryDto", medicalExaminationHistoryDto);
        return "medical-examination-history/add";
    }

    @PostMapping("/add")
    public String addNewMedicalExaminationHistory(@Valid @ModelAttribute("medicalExaminationHistoryDto") MedicalExaminationHistoryDto medicalExaminationHistoryDto,
                            Model model) {
        String response = medicalExaminationHistoryValidationService.validateFields(medicalExaminationHistoryDto);
        String[] responses = response.split(" \\|\\| ");
        if (responses[0].equals("error")) {
            List<String> errors = new ArrayList<>();
            for (int i = 1; i < responses.length; i++) {
                errors.add(responses[i]);
            }
            model.addAttribute("medicalExaminationHistoryDto", medicalExaminationHistoryDto);
            model.addAttribute("currStaffId", accountService.getUserDetail().getAccount().getId());
            model.addAttribute("errors", errors);
        } else {
            boolean isSuccess = medicalExaminationHistoryService.addNewMedicalExaminationHistory(medicalExaminationHistoryDto);
            if (isSuccess) {
                model.addAttribute("response", responses[1]);
                model.addAttribute("patientList", patientInfoService.getPatientInfoList());
            } else {
                model.addAttribute("response", "Fail to add! Something went wrong!");
                model.addAttribute("medicalExaminationHistoryDto", medicalExaminationHistoryDto);
                model.addAttribute("currStaffId", accountService.getUserDetail().getAccount().getId());
            }
        }
        return "medical-examination-history/add";
    }
}
