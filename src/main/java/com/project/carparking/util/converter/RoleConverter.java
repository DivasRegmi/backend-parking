package com.project.carparking.util.converter;

import com.nayan.cms.dto.RoleDTO;
import com.nayan.cms.entity.Role;

public class RoleConverter {

    public static RoleDTO toDTO(Role entity) {
        RoleDTO dto = new RoleDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }

    public static Role toEntity(RoleDTO dto) {
        Role entity = new Role();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        return entity;
    }

    public static void updateEntity(Role entity, RoleDTO dto) {
        if (dto.getName() != null && !dto.getName().isEmpty()) {
            entity.setName(dto.getName());
        }
        // Note: We don't update the 'id' field as it's usually immutable after creation.
    }
}
