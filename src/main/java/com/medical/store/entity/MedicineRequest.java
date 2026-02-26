package com.medical.store.entity;

import jakarta.persistence.*;

@Entity
public class MedicineRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;
    private String phone;
    private String medicineName;
    private String status = "PENDING";

    public MedicineRequest() {}

    public MedicineRequest(String customerName, String phone, String medicineName) {
        this.customerName = customerName;
        this.phone = phone;
        this.medicineName = medicineName;
    }

    public Long getId() { return id; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getMedicineName() { return medicineName; }
    public void setMedicineName(String medicineName) { this.medicineName = medicineName; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}