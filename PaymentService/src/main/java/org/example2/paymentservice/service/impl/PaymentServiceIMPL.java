package org.example2.paymentservice.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example2.paymentservice.dto.PaymentDTO;
import org.example2.paymentservice.service.PaymentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PaymentServiceIMPL implements PaymentService {
    @Override
    public PaymentDTO processThePayment(PaymentDTO paymentDTO) {
        return null;
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
