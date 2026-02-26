package com.medical.store.repository;

import com.medical.store.entity.MedicineRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicineRequestRepository extends JpaRepository<MedicineRequest, Long> {
}