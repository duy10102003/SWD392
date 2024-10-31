package com.swd392.group2.hms_outpatient_gr2.controller;

import com.swd392.group2.hms_outpatient_gr2.model.dto.MedicineDto;
import com.swd392.group2.hms_outpatient_gr2.model.entity.Medicine;
import com.swd392.group2.hms_outpatient_gr2.service.IMedicineService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/medicine")
public class MedicineController {
    private final IMedicineService medicineService;

    @Autowired
    public MedicineController(IMedicineService medicineService) {
        this.medicineService = medicineService;
    }


    @GetMapping("/list")
    public String showMedicineList(Model model) {
        List<Medicine> medicineList = medicineService.getMedicineList();
        model.addAttribute("medicineList", medicineList);

        return "medicine/list";
    }


    @GetMapping("/detail")
    public String viewCustomerDetail(@RequestParam("id") Integer medicineId, Model model) {
        Medicine medicine = medicineService.getMedicineById(medicineId);
        MedicineDto medicineDto = new MedicineDto();
        medicineDto.loadFromEntity(medicine);
        model.addAttribute("medicineDto", medicineDto);

        return "medicine/detail";
    }


    @GetMapping("/add")
    public String addNewMedicine(Model model) {
        MedicineDto medicineDto = new MedicineDto();
        model.addAttribute("medicineDto", medicineDto);

        return "medicine/add";
    }


    @PostMapping("/add")
    public String addNewMedicine(@Valid @ModelAttribute("medicineDto") MedicineDto medicineDto,
                            BindingResult result,
                            Model model) {
        model.addAttribute("medicineDto", medicineDto);
        if (result.hasErrors()) {
            return "medicine/add";
        } else {
            if (medicineService.getMedicineByName(medicineDto.getName()) == null){
                medicineDto.setActive(true);
                medicineService.addNewMedicine(medicineDto);
                return "redirect:/medicine/list?addSuccess";
            } else {
                model.addAttribute("errorMessage", "Medicine already exists.");
                return "medicine/add";
            }
        }
    }
}
