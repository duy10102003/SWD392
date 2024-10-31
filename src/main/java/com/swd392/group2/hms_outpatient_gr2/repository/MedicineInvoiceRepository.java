package com.swd392.group2.hms_outpatient_gr2.repository;

import com.swd392.group2.hms_outpatient_gr2.model.entity.MedicineInvoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicineInvoiceRepository extends JpaRepository<MedicineInvoice, Integer> {
    MedicineInvoice findMedicineInvoiceById(int id);
}
