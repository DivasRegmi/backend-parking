package com.project.carparking.util.converter;

import com.nayan.cms.dto.VisaStatusDTO;
import com.nayan.cms.entity.VisaStatus;

public class VisaStatusConverter {

    public static VisaStatusDTO toDTO(VisaStatus entity) {
        VisaStatusDTO dto = new VisaStatusDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());

        if (entity.getVisaStatusCategory() != null) {
            dto.setVisaStatusCategoryId(entity.getVisaStatusCategory().getId());
        }

        return dto;
    }

    public static VisaStatus toEntity(VisaStatusDTO dto) {
        VisaStatus entity = new VisaStatus();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        // Note: We don't update the 'visaStatusCategory' field here as it's usually managed differently.
        return entity;
    }

    public static void updateEntity(VisaStatus entity, VisaStatusDTO dto) {
        entity.setName(dto.getName());
        // Note: We don't update the 'id' field as it's usually immutable or managed differently.
        // Also, we don't update the 'visaStatusCategory' field here as it's a ManyToOne relationship and managed differently.
    }
}
