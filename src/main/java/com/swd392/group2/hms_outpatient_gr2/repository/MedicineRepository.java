package com.swd392.group2.hms_outpatient_gr2.repository;

import com.swd392.group2.hms_outpatient_gr2.model.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Integer> {
    Medicine findByName(String name);
}
