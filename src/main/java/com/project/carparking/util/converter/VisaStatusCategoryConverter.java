package com.project.carparking.util.converter;

import com.nayan.cms.dto.VisaStatusCategoryDTO;
import com.nayan.cms.entity.VisaStatusCategory;

public class VisaStatusCategoryConverter {

    public static VisaStatusCategoryDTO toDTO(VisaStatusCategory entity) {
        VisaStatusCategoryDTO dto = new VisaStatusCategoryDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }

    public static VisaStatusCategory toEntity(VisaStatusCategoryDTO dto) {
        VisaStatusCategory entity = new VisaStatusCategory();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        // Note: We don't update the 'visaStatus' field here as it's a OneToMany relationship and managed differently.
        return entity;
    }

    public static void updateEntity(VisaStatusCategory entity, VisaStatusCategoryDTO dto) {
        entity.setName(dto.getName());
        // Note: We don't update the 'id' field as it's usually immutable or managed differently.
        // Also, we don't update the 'visaStatus' field here as it's a OneToMany relationship and managed differently.
    }
}
