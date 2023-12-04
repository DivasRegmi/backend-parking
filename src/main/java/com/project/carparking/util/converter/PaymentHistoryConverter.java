package com.project.carparking.util.converter;

import com.nayan.cms.dto.PaymentHistoryDTO;
import com.nayan.cms.entity.PaymentHistory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PaymentHistoryConverter {

    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static PaymentHistoryDTO toDTO(PaymentHistory entity) {
        PaymentHistoryDTO dto = new PaymentHistoryDTO();
        dto.setId(entity.getId());
        dto.setPaidAmount(entity.getPaidAmount());
        dto.setPaidDate(entity.getPaidDate().format(dateFormatter));
        // Note: We don't include the 'payment' field in the DTO since it's a related entity.
        return dto;
    }

    public static PaymentHistory toEntity(PaymentHistoryDTO dto) {
        PaymentHistory entity = new PaymentHistory();
        entity.setId(dto.getId());
        entity.setPaidAmount(dto.getPaidAmount());
        entity.setPaidDate(LocalDate.parse(dto.getPaidDate(), dateFormatter));
        // Note: We don't update the 'payment' field here as it's usually managed differently.
        return entity;
    }

    public static void updateEntity(PaymentHistory entity, PaymentHistoryDTO dto) {
        entity.setPaidAmount(dto.getPaidAmount());
        entity.setPaidDate(LocalDate.parse(dto.getPaidDate(), dateFormatter));
        // Note: We don't update the 'id' field as it's usually immutable or managed differently.
        // Also, we don't update the 'payment' field as it's a ManyToOne relationship and managed differently.
    }
}
