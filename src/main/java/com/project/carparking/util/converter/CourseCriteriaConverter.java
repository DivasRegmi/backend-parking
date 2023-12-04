package com.project.carparking.util.converter;

import com.nayan.cms.dto.CourseCriteriaDTO;
import com.nayan.cms.entity.CourseCriteria;

public class CourseCriteriaConverter {

    public static CourseCriteriaDTO toDTO(CourseCriteria entity) {
        CourseCriteriaDTO dto = new CourseCriteriaDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setValue(entity.getValue());
        // Note: We don't include the 'course' field in the DTO since it's a related entity.
        return dto;
    }

    public static CourseCriteria toEntity(CourseCriteriaDTO dto) {
        CourseCriteria entity = new CourseCriteria();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setValue(dto.getValue());
        // Note: We don't update the 'course' field here as it's usually managed differently.
        return entity;
    }

    public static void updateEntity(CourseCriteria entity, CourseCriteriaDTO dto) {
        if (dto.getName() != null && !dto.getName().isEmpty()) {
            entity.setName(dto.getName());
        }
        // Note: We don't update the 'id' and 'value' fields as they're usually immutable or managed differently.
        // Also, we don't update the 'course' field as it's a ManyToOne relationship and managed differently.
    }
}
