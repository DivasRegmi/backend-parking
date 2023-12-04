package com.project.carparking.util.converter;

import com.nayan.cms.dto.ApplicantDTO;
import com.nayan.cms.entity.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ApplicantConverter {

    public static ApplicantDTO toDTO(Applicant entity) {
        ApplicantDTO dto = new ApplicantDTO();
        dto.setId(entity.getId());
        dto.setStatus(entity.getStatus());
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        dto.setSecondaryEmail(entity.getSecondaryEmail());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setSecondaryPhone(entity.getSecondaryPhone());
        dto.setDob(entity.getDob());
        dto.setGender(entity.getGender());
        dto.setInterestedCountry(entity.getInterestedCountry());
        dto.setInterestedCourse(entity.getInterestedCourse());
        dto.setSummary(entity.getSummary());
        dto.setCreatedAt(entity.getCreatedAt());

        // Mapping related entities (if available)
        if (entity.getAddress() != null) {
            dto.setAddressDTO(AddressConverter.toDTO(entity.getAddress()));
        }

        if (entity.getParentsInformation() != null) {
            dto.setParentsInformationDTO(ParentsInformationConverter.toDTO(entity.getParentsInformation()));
        }



        if (dto.getAcademicsDTO() != null) {
            List<Academic> academics = AcademicConverter.toEntityList(dto.getAcademicsDTO());
            entity.setAcademics(academics);
            for (Academic academic : academics) {
                academic.setApplicant(entity);
            }
        }

        if (dto.getScoresDTO() != null && !dto.getScoresDTO().isEmpty()) {
            List<Score> scores = ScoreConverter.toEntityList(dto.getScoresDTO());
            entity.setScores(scores);
            for (Score score : scores) {
                score.setApplicant(entity);
            }
        }

        // Convert DocumentDTO list to Document entity list
        if (dto.getDocumentsDTO() != null && !dto.getDocumentsDTO().isEmpty()) {
            List<Document> documents = DocumentConverter.toEntityList(dto.getDocumentsDTO());
            entity.setDocuments(documents);
            for (Document document : documents) {
                document.setApplicant(entity);
            }
        }


        if (entity.getCounselor() != null) {
            dto.setCounselorDTO(StaffConverter.toDTO(entity.getCounselor()));
        }

        return dto;
    }

    public static Applicant toEntity(ApplicantDTO dto) {
        Applicant entity = new Applicant();

        // Map simple fields from DTO to entity
        entity.setId(dto.getId());
        entity.setStatus(dto.getStatus());
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setSecondaryEmail(dto.getSecondaryEmail());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setSecondaryPhone(dto.getSecondaryPhone());
        entity.setDob(dto.getDob());
        entity.setGender(dto.getGender());
        entity.setInterestedCountry(dto.getInterestedCountry());
        entity.setInterestedCourse(dto.getInterestedCourse());
        entity.setSummary(dto.getSummary());
        entity.setCreatedAt(dto.getCreatedAt());


        // Map related entity fields (if available)
        if (dto.getAddressDTO() != null) {
            entity.setAddress(AddressConverter.toEntity(dto.getAddressDTO()));
        }

        if (dto.getParentsInformationDTO() != null) {
            entity.setParentsInformation(ParentsInformationConverter.toEntity(dto.getParentsInformationDTO()));
        }

        // For academic, scores, documents, and counselor fields, we will need separate lists in the ApplicantDTO
        // to handle multiple related entities (OneToMany relationships)

        // For example, assuming the lists are defined in the ApplicantDTO as follows:
        // private List<AcademicDTO> academicsDTO;
        // private List<ScoreDTO> scoresDTO;
        // private List<DocumentDTO> documentsDTO;

        // We can update the ApplicantConverter.toEntity method to handle the lists like this:

        // Map list of AcademicDTOs to list of Academic entities
        if (dto.getAcademicsDTO() != null) {
            List<Academic> academics = dto.getAcademicsDTO().stream()
                    .map(AcademicConverter::toEntity)
                    .collect(Collectors.toList());

            entity.setAcademics(academics);
            for (Academic academic : academics) {
                academic.setApplicant(entity);
            }
        }

        // Map list of ScoreDTOs to list of Score entities
        if (dto.getScoresDTO() != null) {
            List<Score> scores = dto.getScoresDTO().stream()
                    .map(ScoreConverter::toEntity)
                    .collect(Collectors.toList());
            entity.setScores(scores);

            // Set the applicant for each score
            for (Score score : scores) {
                score.setApplicant(entity);
            }
        }

// Map list of DocumentDTOs to list of Document entities
        if (dto.getDocumentsDTO() != null) {
            List<Document> documents = dto.getDocumentsDTO().stream()
                    .map(DocumentConverter::toEntity)
                    .collect(Collectors.toList());
            entity.setDocuments(documents);

            // Set the applicant for each document
            for (Document document : documents) {
                document.setApplicant(entity);
            }
        }

        // Map counselor DTO to the Staff entity (assuming there's only one counselor per applicant)
        if (dto.getCounselorDTO() != null) {
            Staff counselor = StaffConverter.toEntity(dto.getCounselorDTO());
            entity.setCounselor(counselor);
        }

        return entity;
    }

    public static void updateEntity(Applicant entity, ApplicantDTO dto) {
        if (dto.getStatus() != null) {
            entity.setStatus(dto.getStatus());
        }
        if (dto.getName() != null) {
            entity.setName(dto.getName());
        }
        if (dto.getEmail() != null) {
            entity.setEmail(dto.getEmail());
        }
        if (dto.getSecondaryEmail() != null) {
            entity.setSecondaryEmail(dto.getSecondaryEmail());
        }
        if (dto.getPhoneNumber() != null) {
            entity.setPhoneNumber(dto.getPhoneNumber());
        }
        if (dto.getSecondaryPhone() != null) {
            entity.setSecondaryPhone(dto.getSecondaryPhone());
        }
        if (dto.getDob() != null) {
            entity.setDob(dto.getDob());
        }
        if (dto.getGender() != null) {
            entity.setGender(dto.getGender());
        }
        if (dto.getInterestedCountry() != null) {
            entity.setInterestedCountry(dto.getInterestedCountry());
        }
        if (dto.getInterestedCourse() != null) {
            entity.setInterestedCourse(dto.getInterestedCourse());
        }
        if (dto.getSummary() != null) {
            entity.setSummary(dto.getSummary());
        }
        if (dto.getCreatedAt() != null) {
            entity.setCreatedAt(dto.getCreatedAt());
        }


    }
}
