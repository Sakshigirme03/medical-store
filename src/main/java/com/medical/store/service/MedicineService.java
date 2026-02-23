package com.medical.store.service;

import com.medical.store.entity.Medicine;
import com.medical.store.repository.MedicineRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicineService {

    private final MedicineRepository medicineRepository;

    public MedicineService(MedicineRepository medicineRepository) {
        this.medicineRepository = medicineRepository;
    }

    public List<Medicine> getAllMedicines(int page, int size) {
        return medicineRepository
                .findAll(PageRequest.of(page, size))
                .getContent();
    }

    public Medicine getMedicineById(Long id) {
        return medicineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medicine not found"));
    }

    public Medicine saveMedicine(Medicine medicine) {
        return medicineRepository.save(medicine);
    }

    public Medicine updateMedicine(Long id, Medicine updatedMedicine) {
        Medicine existing = getMedicineById(id);

        existing.setName(updatedMedicine.getName());
        existing.setManufacturer(updatedMedicine.getManufacturer());
        existing.setPrice(updatedMedicine.getPrice());
        existing.setQuantity(updatedMedicine.getQuantity());

        return medicineRepository.save(existing);
    }

    public void deleteMedicine(Long id) {
        Medicine medicine = getMedicineById(id);
        medicineRepository.delete(medicine);
    }
}