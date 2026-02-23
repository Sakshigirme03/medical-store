package com.medical.store.request;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "medicine_requests")
public class MedicineRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Medicine name cannot be empty")
    private String medicineName;

    @NotBlank(message = "Phone number cannot be empty")
    private String phoneNumber;

    private String status; // PENDING, AVAILABLE, NOT_AVAILABLE

    public MedicineRequest() {}

    public MedicineRequest(String medicineName, String phoneNumber) {
        this.medicineName = medicineName;
        this.phoneNumber = phoneNumber;
        this.status = "PENDING";
    }

    public Long getId() { return id; }

    public String getMedicineName() { return medicineName; }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getPhoneNumber() { return phoneNumber; }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStatus() { return status; }

    public void setStatus(String status) {
        this.status = status;
    }
}