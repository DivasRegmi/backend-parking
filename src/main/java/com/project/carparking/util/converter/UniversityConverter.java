package com.project.carparking.util.converter;

import com.nayan.cms.dto.UniversityDTO;
import com.nayan.cms.entity.University;

public class UniversityConverter {

    public static UniversityDTO toDTO(University entity) {
        UniversityDTO dto = new UniversityDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());

        if (entity.getAbroadSection() != null) {
            dto.setAbroadSectionId(entity.getAbroadSection().getId());
        }

        return dto;
    }

    public static University toEntity(UniversityDTO dto) {
        University entity = new University();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        // Note: We don't update the 'abroadSection' field here as it's usually managed differently.
        return entity;
    }

    public static void updateEntity(University entity, UniversityDTO dto) {
        entity.setName(dto.getName());
        // Note: We don't update the 'id' field as it's usually immutable or managed differently.
        // Also, we don't update the 'abroadSection' field here as it's a ManyToOne relationship and managed differently.
    }
}
