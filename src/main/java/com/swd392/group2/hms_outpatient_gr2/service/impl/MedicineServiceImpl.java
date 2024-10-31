package com.swd392.group2.hms_outpatient_gr2.service.impl;

import com.swd392.group2.hms_outpatient_gr2.model.dto.MedicineDto;
import com.swd392.group2.hms_outpatient_gr2.model.entity.Medicine;
import com.swd392.group2.hms_outpatient_gr2.repository.MedicineRepository;
import com.swd392.group2.hms_outpatient_gr2.service.IMedicineService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("medicineServiceImpl")
public class MedicineServiceImpl implements IMedicineService {
    private final MedicineRepository medicineRepository;

    public MedicineServiceImpl(MedicineRepository medicineRepository) {
        this.medicineRepository = medicineRepository;
    }


    @Override
    public Medicine getMedicineById(Integer id) {
        Optional<Medicine> medicine = medicineRepository.findById(id);
        return medicine.orElse(null);
    }


    @Override
    public Medicine getMedicineByName(String name) {
        return medicineRepository.findByName(name);
    }


    @Override
    public List<Medicine> getMedicineList() {
        return medicineRepository.findAll();
    }


    @Override
    public void addNewMedicine(MedicineDto medicineDto) {
        Medicine medicine = new Medicine();
        medicine.loadFromDto(medicineDto);
        medicineRepository.save(medicine);
    }

    @Override
    public boolean checkStock(Integer medicineId, Integer quantity) {
        return false;
    }

    @Override
    public void updateStock(Integer medicineId, Integer quantity) {

    }
    @Override
    public boolean checkStock(Integer medicineId, int quantityToCheck) {
        Medicine medicine = medicineRepository.findById(medicineId)
                .orElseThrow(() -> new RuntimeException("Medicine not found"));

        return medicine.getQuantity() >= quantityToCheck;
    }

    @Override
    public void updateStock(Integer medicineId, int quantityToReduce) {
        Medicine medicine = medicineRepository.findById(medicineId)
                .orElseThrow(() -> new RuntimeException("Medicine not found"));
        medicine.setQuantity(medicine.getQuantity() - quantityToReduce);
        medicineRepository.save(medicine);
    }
}
