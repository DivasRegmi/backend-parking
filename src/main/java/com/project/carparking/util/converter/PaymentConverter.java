package com.project.carparking.util.converter;

import com.nayan.cms.dto.PaymentDTO;
import com.nayan.cms.entity.Payment;
import java.util.stream.Collectors;

public class PaymentConverter {

    public static PaymentDTO toDTO(Payment entity) {
        PaymentDTO dto = new PaymentDTO();
        dto.setId(entity.getId());
        dto.setTotalAmount(entity.getTotalAmount());

        // Convert the list of PaymentHistory entities to PaymentHistoryDTOs using PaymentHistoryConverter
        dto.setPaymentHistoryList(entity.getPaymentHistoryList()
                .stream()
                .map(PaymentHistoryConverter::toDTO)
                .collect(Collectors.toList()));

        // Note: We don't include the 'applicant' field in the DTO since it's a related entity.
        return dto;
    }

    public static Payment toEntity(PaymentDTO dto) {
        Payment entity = new Payment();
        entity.setId(dto.getId());
        entity.setTotalAmount(dto.getTotalAmount());

        // Convert the list of PaymentHistoryDTOs to PaymentHistory entities using PaymentHistoryConverter
        entity.setPaymentHistoryList(dto.getPaymentHistoryList()
                .stream()
                .map(PaymentHistoryConverter::toEntity)
                .collect(Collectors.toList()));

        // Note: We don't update the 'applicant' field here as it's usually managed differently.
        return entity;
    }

    public static void updateEntity(Payment entity, PaymentDTO dto) {
        if (dto.getTotalAmount() != null) {
            entity.setTotalAmount(dto.getTotalAmount());
        }

        // Note: We don't update the 'id' field as it's usually immutable or managed differently.
        // Also, we don't update the 'paymentHistoryList' and 'applicant' fields as they are managed differently.
    }
}
