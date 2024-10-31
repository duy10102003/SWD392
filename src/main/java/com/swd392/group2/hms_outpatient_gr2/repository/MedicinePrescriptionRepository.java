package com.swd392.group2.hms_outpatient_gr2.repository;

import com.swd392.group2.hms_outpatient_gr2.model.entity.MedicinePrescription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicinePrescriptionRepository extends JpaRepository<MedicinePrescription, Integer> {
}
