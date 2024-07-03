package org.example2.paymentservice.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example2.paymentservice.dto.PaymentDTO;
import org.example2.paymentservice.entity.PaymentEntity;
import org.example2.paymentservice.repository.PaymentDAO;
import org.example2.paymentservice.service.PaymentService;
import org.example2.paymentservice.util.PaymentMapping;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PaymentServiceIMPL implements PaymentService {
    private final PaymentDAO paymentDAO;
    private final PaymentMapping paymentMapping;
    @Override
    public PaymentDTO processThePayment(PaymentDTO paymentDTO) {
        validatePaymentDTO(paymentDTO);

        PaymentEntity paymentEntity = paymentMapping.toPayment(paymentDTO);
        paymentEntity = paymentDAO.save(paymentEntity);
        return paymentMapping.toPaymentDTO(paymentEntity);
    }

    private void validatePaymentDTO(PaymentDTO paymentDTO) {
        if (!StringUtils.hasText(paymentDTO.getPaymentId())) {
            throw new IllegalArgumentException("Payment ID cannot be empty");
        }
        if (!StringUtils.hasText(paymentDTO.getDescription())) {
            throw new IllegalArgumentException("Description cannot be empty");
        }
        if (!StringUtils.hasText(paymentDTO.getPaymentMethod())) {
            throw new IllegalArgumentException("Payment method cannot be empty");
        }
        if (paymentDTO.getAmount() <= 0) {
            throw new IllegalArgumentException("Amount must be greater than 0");
        }
        if (paymentDTO.getPaymentStatus() == null) {
            throw new IllegalArgumentException("Payment status cannot be null");
        }
    }


    @Override
    public void deletePayment(String id) {

    }

    @Override
    public void modifyThePayment(String id, PaymentDTO paymentDTO) {

    }

    @Override
    public List<PaymentDTO> getAllPaymentDetails() {
        return null;
    }

    @Override
    public PaymentDTO getSelectedPaymentDetails(String id) {
        return null;
    }
}
