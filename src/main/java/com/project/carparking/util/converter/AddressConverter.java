package com.project.carparking.util.converter;

import com.nayan.cms.dto.AddressDTO;
import com.nayan.cms.entity.Address;

public class AddressConverter {

    public static AddressDTO toDTO(Address entity) {
        AddressDTO dto = new AddressDTO();
        dto.setId(entity.getId());
        dto.setCountry(entity.getCountry());
        dto.setCity(entity.getCity());
        dto.setState(entity.getState());
        dto.setPostalCode(entity.getPostalCode());
        dto.setStreet(entity.getStreet());
        return dto;
    }

    public static Address toEntity(AddressDTO dto) {
        Address entity = new Address();
        entity.setId(dto.getId());
        entity.setCountry(dto.getCountry());
        entity.setCity(dto.getCity());
        entity.setState(dto.getState());
        entity.setPostalCode(dto.getPostalCode());
        entity.setStreet(dto.getStreet());
        return entity;
    }

    public static void updateEntity(Address entity, AddressDTO dto) {
        if (dto.getCountry() != null && !dto.getCountry().isEmpty()) {
            entity.setCountry(dto.getCountry());
        }
        if (dto.getCity() != null && !dto.getCity().isEmpty()) {
            entity.setCity(dto.getCity());
        }
        if (dto.getState() != null && !dto.getState().isEmpty()) {
            entity.setState(dto.getState());
        }
        if (dto.getPostalCode() != null && !dto.getPostalCode().isEmpty()) {
            entity.setPostalCode(dto.getPostalCode());
        }
        if (dto.getStreet() != null && !dto.getStreet().isEmpty()) {
            entity.setStreet(dto.getStreet());
        }
        // Note: We don't update the 'id' field as it's usually immutable after creation.
    }
}
