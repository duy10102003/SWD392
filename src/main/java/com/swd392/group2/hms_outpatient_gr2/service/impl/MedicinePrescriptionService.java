package com.swd392.group2.hms_outpatient_gr2.service.impl;

import com.swd392.group2.hms_outpatient_gr2.model.entity.Medicine;
import com.swd392.group2.hms_outpatient_gr2.model.entity.MedicineInvoice;
import com.swd392.group2.hms_outpatient_gr2.model.entity.MedicinePrescription;
import com.swd392.group2.hms_outpatient_gr2.model.entity.MedicineItem;
import com.swd392.group2.hms_outpatient_gr2.repository.MedicinePrescriptionRepository;
import com.swd392.group2.hms_outpatient_gr2.repository.MedicineItemRepository;
import com.swd392.group2.hms_outpatient_gr2.repository.MedicineRepository;
import com.swd392.group2.hms_outpatient_gr2.repository.MedicineInvoiceRepository;
import com.swd392.group2.hms_outpatient_gr2.service.IMedicineInvoiceService;
import com.swd392.group2.hms_outpatient_gr2.service.IMedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier; // Import Qualifier
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MedicinePrescriptionService {

    @Autowired
    private MedicinePrescriptionRepository prescriptionRepository;

    @Autowired
    private MedicineItemRepository medicineItemRepository;

    @Autowired
    @Qualifier("medicineServiceImpl")
    private IMedicineService medicineService;

    @Autowired
    @Qualifier("medicineInvoiceServiceImpl")
    private IMedicineInvoiceService medicineInvoiceService;

    public MedicinePrescription createPrescription(MedicinePrescription prescription) {
        // Khởi tạo danh sách nếu chưa được khởi tạo
        if (prescription.getMedicineItems() == null) {
            prescription.setMedicineItems(new ArrayList<>());
        }

        // Lưu đơn thuốc
        MedicinePrescription savedPrescription = prescriptionRepository.save(prescription);

        // Lưu các mục thuốc và cập nhật số lượng
        for (MedicineItem item : prescription.getMedicineItems()) {
            item.setMedicinePrescription(savedPrescription);
            medicineItemRepository.save(item);

            // Kiểm tra và cập nhật số lượng thuốc
            if (medicineService.checkStock(item.getMedicine().getId(), item.getQuantity())) {
                medicineService.updateStock(item.getMedicine().getId(), item.getQuantity());
            }
        }

        // Lấy hóa đơn từ đơn thuốc
        MedicineInvoice invoice = prescription.getMedicalRecord().getMedicineInvoice();
        if (invoice != null) {
            float totalPrice = (float) prescription.getMedicineItems().stream()
                    .mapToDouble(item -> item.getMedicine().getPrice() * item.getQuantity())
                    .sum();
            invoice.setTotalPrice(totalPrice);
            medicineInvoiceService.updateMedicineInvoice(invoice);
        }

        return savedPrescription;
    }

    public List<MedicinePrescription> getAllPrescriptions() {
        return prescriptionRepository.findAll(); // Sử dụng phương thức của JPA để lấy tất cả
    }

    public void save(MedicinePrescription medicinePrescription) {
        prescriptionRepository.save(medicinePrescription);
    }
}
