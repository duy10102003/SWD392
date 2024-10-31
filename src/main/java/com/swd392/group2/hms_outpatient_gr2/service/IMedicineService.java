package com.swd392.group2.hms_outpatient_gr2.service;

import com.swd392.group2.hms_outpatient_gr2.model.dto.MedicineDto;
import com.swd392.group2.hms_outpatient_gr2.model.entity.Medicine;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IMedicineService {
    Medicine getMedicineById(Integer id);
    Medicine getMedicineByName(String name);
    List<Medicine> getMedicineList();
    void addNewMedicine(MedicineDto medicineDto);
    boolean checkStock(Integer medicineId, Integer quantity);
    void updateStock(Integer medicineId, Integer quantity);

    boolean checkStock(Integer medicineId, int quantityToCheck);
    void updateStock(Integer medicineId, int quantityToReduce);

}