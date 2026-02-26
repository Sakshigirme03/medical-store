package com.medical.store.controller;

import com.medical.store.entity.MedicineRequest;
import com.medical.store.repository.MedicineRequestRepository;
import com.medical.store.response.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/requests")
public class MedicineRequestController {

    private final MedicineRequestRepository repository;

    public MedicineRequestController(MedicineRequestRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ApiResponse<MedicineRequest> saveRequest(@RequestBody MedicineRequest request) {

        MedicineRequest saved = repository.save(request);

        return new ApiResponse<>("SUCCESS", "Request submitted successfully", saved);
    }

    @GetMapping
    public ApiResponse<List<MedicineRequest>> getAllRequests() {

        List<MedicineRequest> list = repository.findAll();

        return new ApiResponse<>("SUCCESS", "All requests fetched", list);
    }

    @PutMapping("/{id}/complete")
    public ApiResponse<MedicineRequest> completeRequest(@PathVariable Long id) {

        MedicineRequest request = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        request.setStatus("COMPLETED");

        MedicineRequest updated = repository.save(request);

        return new ApiResponse<>("SUCCESS", "Request marked as completed", updated);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteRequest(@PathVariable Long id) {

        repository.deleteById(id);

        return new ApiResponse<>("SUCCESS", "Request deleted successfully", "Deleted");
    }
}