package com.project.carparking.util.converter;

import com.nayan.cms.dto.TaskDTO;
import com.nayan.cms.entity.Task;

import java.time.LocalDateTime;

public class TaskConverter {

    public static TaskDTO toDTO(Task entity) {
        TaskDTO dto = new TaskDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());

        if (entity.getAssignToStaff() != null) {
            dto.setAssignToStaffId(entity.getAssignToStaff().getId());
        }

        if (entity.getAssignToApplicant() != null) {
            dto.setAssignToApplicantId(entity.getAssignToApplicant().getId());
        }

        dto.setDeadline(entity.getDeadline());
        dto.setComplete(entity.isComplete());
        dto.setStart(entity.getStart());
        dto.setEnd(entity.getEnd());
        dto.setAllDay(entity.isAllDay());
        dto.setAssignToType(entity.getAssignToType());
        return dto;
    }

    public static Task toEntity(TaskDTO dto) {
        Task entity = new Task();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setDeadline(dto.getDeadline());
        entity.setComplete(dto.isComplete());
        entity.setStart(dto.getStart());
        entity.setEnd(dto.getEnd());
        entity.setAllDay(dto.isAllDay());
        entity.setAssignToType(dto.getAssignToType());
        // Note: We don't update the 'assignToStaff' and 'assignToApplicant' fields here as they're usually managed differently.
        return entity;
    }

    public static void updateEntity(Task entity, TaskDTO dto) {
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setDeadline(dto.getDeadline());
        entity.setComplete(dto.isComplete());
        entity.setStart(dto.getStart());
        entity.setEnd(dto.getEnd());
        entity.setAllDay(dto.isAllDay());
        entity.setAssignToType(dto.getAssignToType());
        // Note: We don't update the 'id' field as it's usually immutable or managed differently.
        // Also, we don't update the 'assignToStaff' and 'assignToApplicant' fields here as they're usually managed differently.
    }
}
