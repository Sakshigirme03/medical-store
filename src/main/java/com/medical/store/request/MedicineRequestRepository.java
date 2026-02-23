package com.medical.store.request;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicineRequestRepository extends JpaRepository<MedicineRequest, Long> {
}