package com.medical.store.controller;

import com.medical.store.entity.Medicine;
import com.medical.store.repository.MedicineRepository;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import com.medical.store.dto.MedicineDTO;

import java.util.List;

@RestController
@RequestMapping("/medicines")
public class MedicineController {

    private final MedicineRepository medicineRepository;

    public MedicineController(MedicineRepository medicineRepository) {
        this.medicineRepository = medicineRepository;
    }

    @GetMapping
    public List<MedicineDTO> getAllMedicines() {
        return medicineRepository.findAll()
                .stream()
                .map(m -> new MedicineDTO(
                        m.getId(),
                        m.getName(),
                        m.getManufacturer(),
                        m.getPrice(),
                        m.getQuantity(),
                        m.isAvailable()
                ))
                .toList();
    }
    @PostMapping
    public Medicine addMedicine(@Valid @RequestBody Medicine medicine) {
        return medicineRepository.save(medicine);
    }
    @GetMapping("/{id}")
    public MedicineDTO getMedicineById(@PathVariable Long id) {

        Medicine medicine = medicineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medicine not found"));

        return new MedicineDTO(
                medicine.getId(),
                medicine.getName(),
                medicine.getManufacturer(),
                medicine.getPrice(),
                medicine.getQuantity(),
                medicine.isAvailable()
        );
    }
    @PutMapping("/{id}")
    public Medicine updateMedicine(@PathVariable Long id, @Valid @RequestBody Medicine updatedMedicine) {
        Medicine existingMedicine = medicineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medicine not found"));

        existingMedicine.setName(updatedMedicine.getName());
        existingMedicine.setManufacturer(updatedMedicine.getManufacturer());
        existingMedicine.setPrice(updatedMedicine.getPrice());
        existingMedicine.setQuantity(updatedMedicine.getQuantity());

        return medicineRepository.save(existingMedicine);
    }
    @DeleteMapping("/{id}")
    public String deleteMedicine(@PathVariable Long id) {

        Medicine medicine = medicineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medicine not found"));

        medicineRepository.delete(medicine);

        return "Medicine deleted successfully";
    }
}
