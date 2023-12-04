package com.project.carparking.util.converter;

import com.nayan.cms.dto.AbroadSectionDTO;
import com.nayan.cms.entity.AbroadSection;

public class AbroadSectionConverter {

    public static AbroadSectionDTO toDTO(AbroadSection entity) {
        AbroadSectionDTO dto = new AbroadSectionDTO();
        dto.setId(entity.getId());
        dto.setCountry(entity.getCountry());
        return dto;
    }

    public static AbroadSection toEntity(AbroadSectionDTO dto) {
        AbroadSection entity = new AbroadSection();
        entity.setId(dto.getId());
        entity.setCountry(dto.getCountry());
        return entity;
    }

    public static void updateEntity(AbroadSection entity, AbroadSectionDTO dto) {
        if (dto.getCountry() != null && !dto.getCountry().isEmpty()) {
            entity.setCountry(dto.getCountry());
        }
        // Note: We don't update the 'id' field as it's usually immutable after creation.
    }
}
