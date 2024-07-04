package org.example2.paymentservice.service.impl;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.example2.paymentservice.dto.PaymentDTO;
import org.example2.paymentservice.entity.PaymentEntity;
import org.example2.paymentservice.exception.InvalidPaymentException;
import org.example2.paymentservice.repository.PaymentDAO;
import org.example2.paymentservice.service.PaymentService;
import org.example2.paymentservice.util.PaymentMapping;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

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

//    private void validatePaymentDTO(PaymentDTO paymentDTO) {
//        if (!StringUtils.hasText(paymentDTO.getPaymentId())) {
//            throw new InvalidPaymentException("Payment ID cannot be empty");
//        }
//        if (!StringUtils.hasText(paymentDTO.getDescription())) {
//            throw new InvalidPaymentException("Description cannot be empty");
//        }
//        if (!StringUtils.hasText(paymentDTO.getPaymentMethod())) {
//            throw new InvalidPaymentException("Payment method cannot be empty");
//        }
//        if (paymentDTO.getAmount() <= 0) {
//            throw new InvalidPaymentException("Amount must be greater than 0");
//        }
//        if (paymentDTO.getPaymentStatus() == null) {
//            throw new InvalidPaymentException("Payment status cannot be null");
//        }
//    }


    @Override
    public void deletePayment(String id) {
        if(!paymentDAO.existsById(id)) throw new NotFoundException("Vehicle not found");
        paymentDAO.deleteById(id);
    }

    @Override
    public void modifyThePayment(String id, PaymentDTO paymentDTO) {
        Optional<PaymentEntity> tmpTicket = paymentDAO.findById(id);
        if (!tmpTicket.isPresent()) throw new NotFoundException("Ticket not found");

        tmpTicket.get().setDescription(paymentDTO.getDescription());
        tmpTicket.get().setPaymentMethod(paymentDTO.getPaymentMethod());
        tmpTicket.get().setAmount(paymentDTO.getAmount());
        tmpTicket.get().setPaymentStatus(paymentDTO.getPaymentStatus());
    }

    @Override
    public List<PaymentDTO> getAllPaymentDetails() {
        return paymentMapping.toPaymentDTOList(paymentDAO.findAll());
    }

    @Override
    public PaymentDTO getSelectedPaymentDetails(String id) {
        if(!paymentDAO.existsById(id)) throw new NotFoundException("Payment not found");
        return paymentMapping.toPaymentDTO(paymentDAO.getReferenceById(id));
    }
}
