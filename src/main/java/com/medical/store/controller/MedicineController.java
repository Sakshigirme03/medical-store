package com.medical.store.controller;

import com.medical.store.dto.MedicineDTO;
import com.medical.store.entity.Medicine;
import com.medical.store.response.ApiResponse;
import com.medical.store.service.MedicineService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicines")
public class MedicineController {

    private final MedicineService medicineService;

    public MedicineController(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    // 🔹 Get All Medicines (Pagination)
    @GetMapping
    public ApiResponse<List<MedicineDTO>> getAllMedicines(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        List<MedicineDTO> medicines = medicineService.getAllMedicines(page, size)
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

        return new ApiResponse<>(
                "SUCCESS",
                "Medicines fetched successfully",
                medicines
        );
    }

    // 🔹 Add Medicine
    @PostMapping
    public ApiResponse<Medicine> addMedicine(@Valid @RequestBody Medicine medicine) {

        Medicine saved = medicineService.saveMedicine(medicine);

        return new ApiResponse<>(
                "SUCCESS",
                "Medicine added successfully",
                saved
        );
    }

    // 🔹 Get Medicine By ID
    @GetMapping("/{id}")
    public ApiResponse<MedicineDTO> getMedicineById(@PathVariable Long id) {

        Medicine medicine = medicineService.getMedicineById(id);

        MedicineDTO dto = new MedicineDTO(
                medicine.getId(),
                medicine.getName(),
                medicine.getManufacturer(),
                medicine.getPrice(),
                medicine.getQuantity(),
                medicine.isAvailable()
        );

        return new ApiResponse<>(
                "SUCCESS",
                "Medicine fetched successfully",
                dto
        );
    }

    // 🔹 Update Medicine
    @PutMapping("/{id}")
    public ApiResponse<Medicine> updateMedicine(@PathVariable Long id,
                                                @Valid @RequestBody Medicine updatedMedicine) {

        Medicine updated = medicineService.updateMedicine(id, updatedMedicine);

        return new ApiResponse<>(
                "SUCCESS",
                "Medicine updated successfully",
                updated
        );
    }

    // 🔹 Delete Medicine
    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteMedicine(@PathVariable Long id) {

        medicineService.deleteMedicine(id);

        return new ApiResponse<>(
                "SUCCESS",
                "Medicine deleted successfully",
                null
        );
    }
}