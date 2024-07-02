package org.example2.paymentservice.service;

import org.example2.paymentservice.dto.PaymentDTO;

import java.util.List;

public interface PaymentService {
    PaymentDTO processThePayment(PaymentDTO paymentDTO);
    void deletePayment(String id);
    void modifyThePayment(String id, PaymentDTO paymentDTO);
    List<PaymentDTO> getAllPaymentDetails();
    PaymentDTO getSelectedPaymentDetails(String id);
}
