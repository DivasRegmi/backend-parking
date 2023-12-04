package com.project.carparking.util.converter;

import com.nayan.cms.dto.CourseTypeDTO;
import com.nayan.cms.entity.CourseType;

public class CourseTypeConverter {

    public static CourseTypeDTO toDTO(CourseType entity) {
        CourseTypeDTO dto = new CourseTypeDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }

    public static CourseType toEntity(CourseTypeDTO dto) {
        CourseType entity = new CourseType();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        return entity;
    }

    public static void updateEntity(CourseType entity, CourseTypeDTO dto) {
        if (dto.getName() != null && !dto.getName().isEmpty()) {
            entity.setName(dto.getName());
        }
        // Note: We don't update the 'id' field as it's usually immutable or managed differently.
    }
}
