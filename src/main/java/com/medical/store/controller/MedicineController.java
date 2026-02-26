package com.medical.store.controller;

import com.medical.store.dto.MedicineDTO;
import com.medical.store.entity.Medicine;
import com.medical.store.response.ApiResponse;
import com.medical.store.service.MedicineService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3002"})
@RestController
@RequestMapping("/medicines")
public class MedicineController {

    private final MedicineService medicineService;

    public MedicineController(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    // ================= GET ALL MEDICINES =================
    @GetMapping
    public ApiResponse<List<MedicineDTO>> getAllMedicines() {

        List<MedicineDTO> medicines = medicineService.getAllMedicines()
                .stream()
                .map(this::convertToDTO)
                .toList();

        return new ApiResponse<>(
                "SUCCESS",
                "Medicines fetched successfully",
                medicines
        );
    }

    // ================= SEARCH MEDICINES =================
    @GetMapping("/search")
    public ApiResponse<List<MedicineDTO>> searchMedicines(
            @RequestParam String name) {

        List<MedicineDTO> medicines = medicineService.searchByName(name)
                .stream()
                .map(this::convertToDTO)
                .toList();

        return new ApiResponse<>(
                "SUCCESS",
                medicines.isEmpty() ? "Medicine not found" : "Medicine found",
                medicines
        );
    }

    // ================= GET MEDICINE BY ID =================
    @GetMapping("/{id}")
    public ApiResponse<MedicineDTO> getMedicineById(@PathVariable Long id) {

        Medicine medicine = medicineService.getMedicineById(id);

        return new ApiResponse<>(
                "SUCCESS",
                "Medicine fetched successfully",
                convertToDTO(medicine)
        );
    }

    // ================= ADD MEDICINE =================
    @PostMapping
    public ApiResponse<Medicine> addMedicine(
            @Valid @RequestBody Medicine medicine) {

        Medicine saved = medicineService.saveMedicine(medicine);

        return new ApiResponse<>(
                "SUCCESS",
                "Medicine added successfully",
                saved
        );
    }

    // ================= UPDATE MEDICINE =================
    @PutMapping("/{id}")
    public ApiResponse<Medicine> updateMedicine(
            @PathVariable Long id,
            @Valid @RequestBody Medicine updatedMedicine) {

        Medicine updated = medicineService.updateMedicine(id, updatedMedicine);

        return new ApiResponse<>(
                "SUCCESS",
                "Medicine updated successfully",
                updated
        );
    }

    // ================= DELETE MEDICINE =================
    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteMedicine(@PathVariable Long id) {

        medicineService.deleteMedicine(id);

        return new ApiResponse<>(
                "SUCCESS",
                "Medicine deleted successfully",
                null
        );
    }

    // ================= DTO CONVERTER =================
    private MedicineDTO convertToDTO(Medicine medicine) {
        return new MedicineDTO(
                medicine.getId(),
                medicine.getName(),
                medicine.getManufacturer(),
                medicine.getPrice(),
                medicine.getQuantity(),
                medicine.isAvailable()
        );
    }
}