package com.medical.store.request;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/requests")
public class MedicineRequestController {

    private final MedicineRequestRepository repository;

    public MedicineRequestController(MedicineRequestRepository repository) {
        this.repository = repository;
    }

    // Patient sends request
    @PostMapping
    public MedicineRequest createRequest(@Valid @RequestBody MedicineRequest request) {

        request.setStatus("PENDING");
        return repository.save(request);
    }

    // Admin views all requests
    @GetMapping
    public List<MedicineRequest> getAllRequests() {
        return repository.findAll();
    }
}