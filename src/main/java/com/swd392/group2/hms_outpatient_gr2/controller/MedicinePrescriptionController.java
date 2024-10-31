package com.swd392.group2.hms_outpatient_gr2.controller;

import com.swd392.group2.hms_outpatient_gr2.model.entity.*;
import com.swd392.group2.hms_outpatient_gr2.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MedicinePrescriptionController {

    @Autowired
    private MedicalRecordService medicalRecordService;

    @Autowired
    private MedicineServiceImpl medicineService;

    @Autowired
    private MedicinePrescriptionService medicinePrescriptionService;

    @Autowired
    private MedicineInvoiceServiceImpl medicineInvoiceService;

    @Autowired
    private AccountServiceImpl accountService;

    @GetMapping("/prescription/create")
    public String requestCreateMedicinePrescription(Model model) {
        List<MedicalRecord> medicalRecords = medicalRecordService.getAllMedicalRecords();
        List<Medicine> medicines = medicineService.getMedicineList();
        model.addAttribute("medicalRecords", medicalRecords);
        model.addAttribute("medicines", medicines);
        return "prescription/createPrescription";
    }

//    @PostMapping("/prescription/create")
//    public String createPrescription(
//            @RequestParam Integer medicalRecordId,
//            @RequestParam Integer medicineInvoiceId,
//            @RequestParam Integer doctorId,
//            @RequestParam List<Integer> medicineIds,
//            @RequestParam List<Integer> quantities) {
//
//        MedicinePrescription prescription = new MedicinePrescription();
//
//        // Lấy MedicalRecord
//        MedicalRecord medicalRecord = medicalRecordService.getMedicalRecordById(medicalRecordId);
//        prescription.setMedicalRecord(medicalRecord);
//
//        // Lấy MedicineInvoice và Doctor
//        MedicineInvoice medicineInvoice = medicineInvoiceService.findMedicineInvoice(medicineInvoiceId);
//        Account doctor = accountService.getAccountById(doctorId);
//        prescription.setMedicineInvoice(medicineInvoice);
//        prescription.setDoctor(doctor);
//
//        double totalPrice = 0.0; // Biến để tính tổng giá
//
//        // Cập nhật danh sách MedicineItems và tính tổng giá
//        for (int i = 0; i < medicineIds.size(); i++) {
//            Medicine medicine = medicineService.getMedicineById(medicineIds.get(i));
//            int quantity = quantities.get(i);
//
//            // Cập nhật số lượng thuốc
//            medicine.setQuantity(medicine.getQuantity() - quantity);
//
//            // Tạo item cho prescription
//            MedicineItem item = new MedicineItem();
//            item.setMedicine(medicine);
//            item.setQuantity(quantity);
//            prescription.getMedicineItems().add(item); // Thêm vào danh sách
//
//            // Cộng dồn giá vào tổng
//            totalPrice += medicine.getPrice() * quantity;
//        }
//
//        // Lưu prescription
//        medicinePrescriptionService.save(prescription);
//
//        // Cập nhật tổng giá cho MedicineInvoice
//        medicineInvoiceService.updateTotalPrice(medicineInvoiceId, totalPrice);
//
//        return "redirect:/prescriptions"; // Redirect đến danh sách đơn thuốc hoặc trang khác
//    }
@PostMapping("/prescription/create")
public String requestCreateMedicinePrescription(
        @RequestParam Integer medicalRecordId,
        @RequestParam Integer medicineInvoiceId,
        @RequestParam Integer doctorId,
        @RequestParam List<Integer> medicineIds,
        @RequestParam List<Integer> quantities) {

    MedicinePrescription prescription = new MedicinePrescription();

    // Lấy MedicalRecord
    MedicalRecord medicalRecord = medicalRecordService.getMedicalRecordById(medicalRecordId);
    prescription.setMedicalRecord(medicalRecord);

    // Lấy MedicineInvoice và Doctor
    MedicineInvoice medicineInvoice = medicineInvoiceService.findMedicineInvoice(medicineInvoiceId);
    Account doctor = accountService.getAccountById(doctorId);
    prescription.setMedicineInvoice(medicineInvoice);
    prescription.setDoctor(doctor);

    double totalPrice = 0.0; // Biến để tính tổng giá

    // Cập nhật danh sách MedicineItems và kiểm tra số lượng
    for (int i = 0; i < medicineIds.size(); i++) {
        Integer medicineId = medicineIds.get(i);
        int quantity = quantities.get(i);

        // Kiểm tra số lượng thuốc
        if (!medicineService.checkStock(medicineId, quantity)) {
            // Nếu không đủ số lượng, trả về thông báo lỗi
            return "redirect:/prescription/create?error=not_enough_stock"; // Bạn có thể thay đổi cách xử lý lỗi
        }

        // Cập nhật stock
        medicineService.updateStock(medicineId, quantity);

        // Tạo item cho prescription
        Medicine medicine = medicineService.getMedicineById(medicineId);
        MedicineItem item = new MedicineItem();
        item.setMedicine(medicine);
        item.setQuantity(quantity);
        prescription.getMedicineItems().add(item); // Thêm vào danh sách

        // Cộng dồn giá vào tổng
        totalPrice += medicine.getPrice() * quantity;
    }

    // Lưu prescription
    medicinePrescriptionService.save(prescription);

    // Cập nhật tổng giá cho MedicineInvoice
    medicineInvoiceService.updateTotalPrice(medicineInvoiceId, totalPrice);

    return "redirect:/listPrescriptions?addSuccess=true";
}

    @GetMapping("/listPrescriptions")
    public String listPrescriptions() {
        return "prescription/listPrescriptions"; // Đảm bảo đây là tên template đúng
    }

}
