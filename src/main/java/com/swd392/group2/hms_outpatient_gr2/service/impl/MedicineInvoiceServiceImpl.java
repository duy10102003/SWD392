package com.swd392.group2.hms_outpatient_gr2.service.impl;

import com.swd392.group2.hms_outpatient_gr2.model.entity.MedicineInvoice;
import com.swd392.group2.hms_outpatient_gr2.repository.MedicineInvoiceRepository;
import com.swd392.group2.hms_outpatient_gr2.service.IMedicineInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("medicineInvoiceServiceImpl")
public class MedicineInvoiceServiceImpl implements IMedicineInvoiceService {
    @Autowired
    private MedicineInvoiceRepository medicineInvoiceRepository;

    public MedicineInvoice updateMedicineInvoice(MedicineInvoice invoice) {
        return medicineInvoiceRepository.save(invoice); // Lưu hoặc cập nhật hóa đơn
    }

    public void updateTotalPrice(Integer invoiceId, double newTotalPrice) {
        MedicineInvoice invoice = medicineInvoiceRepository.findById(invoiceId).orElseThrow(() -> new RuntimeException("Invoice not found"));
        invoice.setTotalPrice(newTotalPrice);
        medicineInvoiceRepository.save(invoice);
    }

    public MedicineInvoice findMedicineInvoice(Integer invoiceId) {
        return medicineInvoiceRepository.findById(invoiceId).orElseThrow(() -> new RuntimeException("Invoice not found"));

    }
}
