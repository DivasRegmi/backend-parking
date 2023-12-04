package com.project.carparking.util.converter;

import com.nayan.cms.dto.ParentsInformationDTO;
import com.nayan.cms.entity.ParentsInformation;

public class ParentsInformationConverter {

    public static ParentsInformationDTO toDTO(ParentsInformation entity) {
        ParentsInformationDTO dto = new ParentsInformationDTO();
        dto.setId(entity.getId());
        dto.setFatherName(entity.getFatherName());
        dto.setMotherName(entity.getMotherName());
        return dto;
    }

    public static ParentsInformation toEntity(ParentsInformationDTO dto) {
        ParentsInformation entity = new ParentsInformation();
        entity.setId(dto.getId());
        entity.setFatherName(dto.getFatherName());
        entity.setMotherName(dto.getMotherName());
        return entity;
    }

    public static void updateEntity(ParentsInformation entity, ParentsInformationDTO dto) {
        if (dto.getFatherName() != null && !dto.getFatherName().isEmpty()) {
            entity.setFatherName(dto.getFatherName());
        }
        if (dto.getMotherName() != null && !dto.getMotherName().isEmpty()) {
            entity.setMotherName(dto.getMotherName());
        }
        // Note: We don't update the 'id' field as it's usually immutable or managed differently.
    }
}
