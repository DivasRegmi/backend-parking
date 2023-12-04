package com.project.carparking.util.converter;

import com.nayan.cms.dto.DocumentDTO;
import com.nayan.cms.entity.Document;

import java.util.ArrayList;
import java.util.List;

public class DocumentConverter {

    public static DocumentDTO toDTO(Document entity) {
        DocumentDTO dto = new DocumentDTO();
        dto.setId(entity.getId());
        dto.setImageUrl(entity.getImageUrl());
        dto.setType(entity.getType());
        // Note: We don't include the 'applicant' field in the DTO since it's a related entity.
        return dto;
    }

    public static Document toEntity(DocumentDTO dto) {
        Document entity = new Document();
        entity.setId(dto.getId());
        entity.setImageUrl(dto.getImageUrl());
        entity.setType(dto.getType());
        // Note: We don't update the 'applicant' field here as it's usually managed differently.
        return entity;
    }

    public static void updateEntity(Document entity, DocumentDTO dto) {
        if (dto.getImageUrl() != null && !dto.getImageUrl().isEmpty()) {
            entity.setImageUrl(dto.getImageUrl());
        }
        if (dto.getType() != null && !dto.getType().isEmpty()) {
            entity.setType(dto.getType());
        }
        // Note: We don't update the 'id' field as it's usually immutable or managed differently.
        // Also, we don't update the 'applicant' field as it's a ManyToOne relationship and managed differently.
    }

    public static List<Document> toEntityList(List<DocumentDTO> dtoList) {
        List<Document> entityList = new ArrayList<>();
        if (dtoList != null) {
            for (DocumentDTO dto : dtoList) {
                entityList.add(toEntity(dto));
            }
        }
        return entityList;
    }
}
