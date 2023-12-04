package com.project.carparking.util.converter;

import com.nayan.cms.dto.StaffDTO;
import com.nayan.cms.entity.Staff;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StaffConverter {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static StaffDTO toDTO(Staff entity) {
        StaffDTO dto = new StaffDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setDOB(entity.getDOB());
        dto.setGender(entity.getGender());
        dto.setSalary(entity.getSalary());
        if (entity.getRole() != null) {
            dto.setRoleId(entity.getRole().getId());
        }
        // Note: We don't include the 'createdAt' field in the DTO as it's usually not used for updates.
        return dto;
    }

    public static Staff toEntity(StaffDTO dto) {
        Staff entity = new Staff();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setDOB(dto.getDOB());
        entity.setGender(dto.getGender());
        entity.setSalary(dto.getSalary());
        // Note: We don't update the 'role' field here as it's usually managed differently.
        return entity;
    }

    public static void updateEntity(Staff entity, StaffDTO dto) {
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setDOB(dto.getDOB());
        entity.setGender(dto.getGender());
        entity.setSalary(dto.getSalary());
        // Note: We don't update the 'id' field as it's usually immutable or managed differently.
        // Also, we don't update the 'roleId' field here as it's usually managed differently.
        // Also, we don't update the 'role' field as it's a ManyToOne relationship and managed differently.
    }
}
