package org.example2.ticketservice.repository;

import org.example2.ticketservice.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentDAO extends JpaRepository<PaymentEntity, String> {
    Optional<PaymentEntity> findById(String paymentId);
}
