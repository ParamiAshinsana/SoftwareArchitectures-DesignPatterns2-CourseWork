package org.example2.paymentservice.util;

import lombok.RequiredArgsConstructor;
import org.example2.paymentservice.dto.PaymentDTO;
import org.example2.paymentservice.entity.PaymentEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PaymentMapping {
    private final ModelMapper modelMapper;

    public PaymentDTO toPaymentDTO(PaymentEntity paymentEntity) {
        return  modelMapper.map(paymentEntity, PaymentDTO.class);
    }
    public PaymentEntity toPayment(PaymentDTO paymentDTO) {
        return  modelMapper.map(paymentDTO, PaymentEntity.class);
    }
    public List<PaymentDTO> toPaymentDTOList(List<PaymentEntity> paymentEntities) {
        return modelMapper.map(paymentEntities, List.class);
    }
}
