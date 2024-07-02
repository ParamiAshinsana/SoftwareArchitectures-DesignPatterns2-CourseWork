package org.example2.paymentservice.repository;

import org.example2.paymentservice.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentDAO extends JpaRepository<PaymentEntity, String> {
}
