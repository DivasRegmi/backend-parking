package com.project.carparking.util.converter;

import com.nayan.cms.dto.ContactDTO;
import com.nayan.cms.entity.Contact;

public class ContactConverter {

    public static ContactDTO toDTO(Contact entity) {
        ContactDTO dto = new ContactDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setEmail(entity.getEmail());
        dto.setOrganization(entity.getOrganization());
        dto.setDescription(entity.getDescription());
        return dto;
    }

    public static Contact toEntity(ContactDTO dto) {
        Contact entity = new Contact();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setEmail(dto.getEmail());
        entity.setOrganization(dto.getOrganization());
        entity.setDescription(dto.getDescription());
        return entity;
    }

    public static void updateEntity(Contact entity, ContactDTO dto) {
        if (dto.getName() != null && !dto.getName().isEmpty()) {
            entity.setName(dto.getName());
        }
        if (dto.getPhoneNumber() != null && !dto.getPhoneNumber().isEmpty()) {
            entity.setPhoneNumber(dto.getPhoneNumber());
        }
        if (dto.getEmail() != null && !dto.getEmail().isEmpty()) {
            entity.setEmail(dto.getEmail());
        }
        if (dto.getOrganization() != null && !dto.getOrganization().isEmpty()) {
            entity.setOrganization(dto.getOrganization());
        }
        if (dto.getDescription() != null && !dto.getDescription().isEmpty()) {
            entity.setDescription(dto.getDescription());
        }
        // Note: We don't update the 'id' field as it's usually immutable after creation.
    }
}
