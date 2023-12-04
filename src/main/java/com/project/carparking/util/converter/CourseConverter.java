package com.project.carparking.util.converter;

import com.nayan.cms.dto.CourseDTO;
import com.nayan.cms.entity.Course;

public class CourseConverter {

    public static CourseDTO toDTO(Course entity) {
        CourseDTO dto = new CourseDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setCoursePeriod(entity.getCoursePeriod());
        dto.setTotalFee(entity.getTotalFee());
        dto.setYearlyFee(entity.getYearlyFee());
        dto.setSemesterFee(entity.getSemesterFee());
        dto.setLocation(entity.getLocation());
        dto.setNearestCity(entity.getNearestCity());
        dto.setIntake(entity.getIntake());
        dto.setEmail(entity.getEmail());
        dto.setApplicantFee(entity.getApplicantFee());
        dto.setCommissionPercentage(entity.getCommissionPercentage());
        dto.setScholarship(entity.getScholarship());
        dto.setOverview(entity.getOverview());
        // Note: We don't include the 'university', 'courseType', and 'courseCriteriaList' fields in the DTO
        // since they are related entities and a collection type.
        return dto;
    }

    public static Course toEntity(CourseDTO dto) {
        Course entity = new Course();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setCoursePeriod(dto.getCoursePeriod());
        entity.setTotalFee(dto.getTotalFee());
        entity.setYearlyFee(dto.getYearlyFee());
        entity.setSemesterFee(dto.getSemesterFee());
        entity.setLocation(dto.getLocation());
        entity.setNearestCity(dto.getNearestCity());
        entity.setIntake(dto.getIntake());
        entity.setEmail(dto.getEmail());
        entity.setApplicantFee(dto.getApplicantFee());
        entity.setCommissionPercentage(dto.getCommissionPercentage());
        entity.setScholarship(dto.getScholarship());
        entity.setOverview(dto.getOverview());
        // Note: We don't update the 'university', 'courseType', and 'courseCriteriaList' fields here as they're usually managed differently.
        return entity;
    }

    public static void updateEntity(Course entity, CourseDTO dto) {
        if (dto.getName() != null && !dto.getName().isEmpty()) {
            entity.setName(dto.getName());
        }
        if (dto.getCoursePeriod() != null && !dto.getCoursePeriod().isEmpty()) {
            entity.setCoursePeriod(dto.getCoursePeriod());
        }
        if (dto.getTotalFee() != null && !dto.getTotalFee().isEmpty()) {
            entity.setTotalFee(dto.getTotalFee());
        }
        if (dto.getYearlyFee() != null && !dto.getYearlyFee().isEmpty()) {
            entity.setYearlyFee(dto.getYearlyFee());
        }
        if (dto.getSemesterFee() != null && !dto.getSemesterFee().isEmpty()) {
            entity.setSemesterFee(dto.getSemesterFee());
        }
        if (dto.getLocation() != null && !dto.getLocation().isEmpty()) {
            entity.setLocation(dto.getLocation());
        }
        if (dto.getNearestCity() != null && !dto.getNearestCity().isEmpty()) {
            entity.setNearestCity(dto.getNearestCity());
        }
        if (dto.getIntake() != null && !dto.getIntake().isEmpty()) {
            entity.setIntake(dto.getIntake());
        }
        if (dto.getEmail() != null && !dto.getEmail().isEmpty()) {
            entity.setEmail(dto.getEmail());
        }
        if (dto.getApplicantFee() != null && !dto.getApplicantFee().isEmpty()) {
            entity.setApplicantFee(dto.getApplicantFee());
        }
        if (dto.getCommissionPercentage() != null && !dto.getCommissionPercentage().isEmpty()) {
            entity.setCommissionPercentage(dto.getCommissionPercentage());
        }
        if (dto.getScholarship() != null && !dto.getScholarship().isEmpty()) {
            entity.setScholarship(dto.getScholarship());
        }
        if (dto.getOverview() != null && !dto.getOverview().isEmpty()) {
            entity.setOverview(dto.getOverview());
        }
        // Note: We don't update the 'id', 'university', 'courseType', and 'courseCriteriaList' fields as they're usually immutable or managed differently.
    }
}
