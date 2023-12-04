package com.project.carparking.util.converter;

import com.nayan.cms.dto.AcademicDTO;
import com.nayan.cms.entity.Academic;

import java.util.ArrayList;
import java.util.List;

public class AcademicConverter {

    public static AcademicDTO toDTO(Academic entity) {
        AcademicDTO dto = new AcademicDTO();
        dto.setId(entity.getId());
        dto.setInstitution(entity.getInstitution());
        dto.setDegreeTitle(entity.getDegreeTitle());
        dto.setDegreeLevel(entity.getDegreeLevel());
        dto.setCourseStart(entity.getCourseStart());
        dto.setCourseEnd(entity.getCourseEnd());
        dto.setSubject(entity.getSubject());
        dto.setPercentage(entity.getPercentage());
        dto.setGPA(entity.getGPA());
        return dto;
    }

    public static Academic toEntity(AcademicDTO dto) {
        Academic entity = new Academic();
        entity.setId(dto.getId());
        entity.setInstitution(dto.getInstitution());
        entity.setDegreeTitle(dto.getDegreeTitle());
        entity.setDegreeLevel(dto.getDegreeLevel());
        entity.setCourseStart(dto.getCourseStart());
        entity.setCourseEnd(dto.getCourseEnd());
        entity.setSubject(dto.getSubject());
        entity.setPercentage(dto.getPercentage());
        entity.setGPA(dto.getGPA());
        return entity;
    }

    public static void updateEntity(Academic entity, AcademicDTO dto) {
        if (dto.getInstitution() != null && !dto.getInstitution().isEmpty()) {
            entity.setInstitution(dto.getInstitution());
        }
        if (dto.getDegreeTitle() != null && !dto.getDegreeTitle().isEmpty()) {
            entity.setDegreeTitle(dto.getDegreeTitle());
        }
        if (dto.getDegreeLevel() != null && !dto.getDegreeLevel().isEmpty()) {
            entity.setDegreeLevel(dto.getDegreeLevel());
        }
        if (dto.getCourseStart() != null) {
            entity.setCourseStart(dto.getCourseStart());
        }
        if (dto.getCourseEnd() != null) {
            entity.setCourseEnd(dto.getCourseEnd());
        }
        if (dto.getSubject() != null && !dto.getSubject().isEmpty()) {
            entity.setSubject(dto.getSubject());
        }
        if (dto.getPercentage() != null) {
            entity.setPercentage(dto.getPercentage());
        }
        if (dto.getGPA() != null) {
            entity.setGPA(dto.getGPA());
        }
        // Note: We don't update the 'id' field as it's usually immutable after creation.
    }

    public static List<Academic> toEntityList(List<AcademicDTO> dtoList) {
        List<Academic> entityList = new ArrayList<>();
        if (dtoList != null) {
            for (AcademicDTO dto : dtoList) {
                entityList.add(toEntity(dto));
            }
        }
        return entityList;
    }
}
