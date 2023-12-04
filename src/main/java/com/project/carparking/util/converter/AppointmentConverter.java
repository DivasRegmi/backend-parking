package com.project.carparking.util.converter;

import com.nayan.cms.dto.AppointmentDTO;
import com.nayan.cms.entity.Appointment;

public class AppointmentConverter {

    public static AppointmentDTO toDTO(Appointment entity) {
        AppointmentDTO dto = new AppointmentDTO();
        dto.setId(entity.getId());
        dto.setType(entity.getType());
        dto.setDescription(entity.getDescription());
        // Note: We don't include the 'assignTo', 'applicant', and 'date' fields in the DTO
        // since they are related entities and a complex type.
        return dto;
    }

    public static Appointment toEntity(AppointmentDTO dto) {
        Appointment entity = new Appointment();
        entity.setId(dto.getId());
        entity.setType(dto.getType());
        entity.setDescription(dto.getDescription());
        // Note: We don't update the 'assignTo', 'applicant', and 'date' fields here as they're usually managed differently.
        return entity;
    }

    public static void updateEntity(Appointment entity, AppointmentDTO dto) {
        if (dto.getType() != null) {
            entity.setType(dto.getType());
        }
        if (dto.getDescription() != null && !dto.getDescription().isEmpty()) {
            entity.setDescription(dto.getDescription());
        }
        // Note: We don't update the 'id', 'assignTo', 'applicant', and 'date' fields as they're usually immutable or managed differently.
    }
}
