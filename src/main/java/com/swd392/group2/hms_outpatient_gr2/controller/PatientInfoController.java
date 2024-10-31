package com.swd392.group2.hms_outpatient_gr2.controller;

import com.swd392.group2.hms_outpatient_gr2.model.dto.PatientInfoDto;
import com.swd392.group2.hms_outpatient_gr2.model.entity.PatientInfo;
import com.swd392.group2.hms_outpatient_gr2.service.PatientInfoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/patient-info")
public class PatientInfoController {
    private final PatientInfoService patientInfoService;

    @Autowired
    public PatientInfoController(PatientInfoService patientInfoService) {
        this.patientInfoService = patientInfoService;
    }

    @GetMapping("/list")
    public String showPatientInfoList(Model model) {
        List<PatientInfo> patientInfoList = patientInfoService.getPatientInfoList();
        model.addAttribute("patientInfoList", patientInfoList);
        return "patient-info/list";
    }

    @GetMapping("/detail")
    public String viewPatientInfoDetail(@RequestParam("id") Integer patientInfoId, Model model) {
        PatientInfo patientInfo = patientInfoService.getPatientInfoById(patientInfoId);
        PatientInfoDto patientInfoDto = new PatientInfoDto();
        patientInfoDto.loadFromEntity(patientInfo);
        model.addAttribute("patientInfo", patientInfo);
        return "patient-info/detail";
    }

    @GetMapping("/add")
    public String getAddNewPatientInfo(Model model) {
        PatientInfoDto patientInfoDto = new PatientInfoDto();
        model.addAttribute("patientInfoDto", patientInfoDto);
        return "patient-info/add";
    }

    @PostMapping("/add")
    public String addNewPatientInfo(@Valid @ModelAttribute("patientInfoDto") PatientInfoDto patientInfoDto,
                                    BindingResult result,
                                    Model model) {
        if (result.hasErrors()) {
            model.addAttribute("patientInfoDto", patientInfoDto);
            return "patient-info/add";
        } else {
            patientInfoService.addNewPatientInfo(patientInfoDto);
            return "redirect:/patient-info/add?addSuccess=true";
        }
    }
}
