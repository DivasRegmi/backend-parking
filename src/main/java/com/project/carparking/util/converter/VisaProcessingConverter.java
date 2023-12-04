package com.project.carparking.util.converter;

import com.nayan.cms.dto.VisaProcessingDTO;
import com.nayan.cms.entity.VisaProcessing;

public class VisaProcessingConverter {

    public static VisaProcessingDTO toDTO(VisaProcessing entity) {
        VisaProcessingDTO dto = new VisaProcessingDTO();
        dto.setId(entity.getId());
        dto.setCountry(entity.getCountry());

        if (entity.getUniversity() != null) {
            dto.setUniversityId(entity.getUniversity().getId());
        }

        if (entity.getCourse() != null) {
            dto.setCourseId(entity.getCourse().getId());
        }

        dto.setIntakePeriod(entity.getIntakePeriod());

        if (entity.getActiveVisaStatus() != null) {
            dto.setActiveVisaStatusId(entity.getActiveVisaStatus().getId());
        }

        if (entity.getVisaStatusCategory() != null) {
            dto.setVisaStatusCategoryId(entity.getVisaStatusCategory().getId());
        }

        if (entity.getApplicant() != null) {
            dto.setApplicantId(entity.getApplicant().getId());
        }

        return dto;
    }

    public static VisaProcessing toEntity(VisaProcessingDTO dto) {
        VisaProcessing entity = new VisaProcessing();
        entity.setId(dto.getId());
        entity.setCountry(dto.getCountry());
        entity.setIntakePeriod(dto.getIntakePeriod());
        // Note: We don't update the 'university', 'course', 'activeVisaStatus', 'visaStatusCategory', and 'applicant'
        // fields here as they're usually managed differently.
        return entity;
    }

    public static void updateEntity(VisaProcessing entity, VisaProcessingDTO dto) {
        entity.setCountry(dto.getCountry());
        entity.setIntakePeriod(dto.getIntakePeriod());
        // Note: We don't update the 'id' field as it's usually immutable or managed differently.
        // Also, we don't update the 'university', 'course', 'activeVisaStatus', 'visaStatusCategory', and 'applicant'
        // fields here as they're usually managed differently.
    }
}
