package org.example2.paymentservice.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example2.paymentservice.dto.PaymentDTO;
import org.example2.paymentservice.entity.PaymentEntity;
import org.example2.paymentservice.repository.PaymentDAO;
import org.example2.paymentservice.service.PaymentService;
import org.example2.paymentservice.util.PaymentMapping;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PaymentServiceIMPL implements PaymentService {
    private final PaymentDAO paymentDAO;
    private final PaymentMapping paymentMapping;
    @Override
    public PaymentDTO processThePayment(PaymentDTO paymentDTO) {
        PaymentEntity paymentEntity = paymentMapping.toPayment(paymentDTO);

        paymentEntity = paymentDAO.save(paymentEntity);
        return paymentMapping.toPaymentDTO(paymentEntity);
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
