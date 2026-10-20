package com.example.payment_service.repository;

import com.example.payment_service.model.Dues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DuesRepository extends JpaRepository<Dues,Long> {
}
