package com.project.carparking.util.converter;

import com.nayan.cms.dto.ClassRoomDTO;
import com.nayan.cms.entity.ClassRoom;

public class ClassRoomConverter {

    public static ClassRoomDTO toDTO(ClassRoom entity) {
        ClassRoomDTO dto = new ClassRoomDTO();
        dto.setId(entity.getId());
        dto.setSubject(entity.getSubject());
        dto.setStartingTime(entity.getStartingTime());
        dto.setEndTime(entity.getEndTime());
        dto.setStartDate(entity.getStartDate());
        // Note: We don't include the 'staff' and 'applicants' fields in the DTO
        // since they are related entities and a collection type.
        return dto;
    }

    public static ClassRoom toEntity(ClassRoomDTO dto) {
        ClassRoom entity = new ClassRoom();
        entity.setId(dto.getId());
        entity.setSubject(dto.getSubject());
        entity.setStartingTime(dto.getStartingTime());
        entity.setEndTime(dto.getEndTime());
        entity.setStartDate(dto.getStartDate());
        // Note: We don't update the 'staff' and 'applicants' fields here as they're usually managed differently.
        return entity;
    }

    public static void updateEntity(ClassRoom entity, ClassRoomDTO dto) {
        if (dto.getSubject() != null && !dto.getSubject().isEmpty()) {
            entity.setSubject(dto.getSubject());
        }
        if (dto.getStartingTime() != null) {
            entity.setStartingTime(dto.getStartingTime());
        }
        if (dto.getEndTime() != null) {
            entity.setEndTime(dto.getEndTime());
        }
        if (dto.getStartDate() != null) {
            entity.setStartDate(dto.getStartDate());
        }
        // Note: We don't update the 'id', 'staff', and 'applicants' fields as they're usually immutable or managed differently.
    }
}
