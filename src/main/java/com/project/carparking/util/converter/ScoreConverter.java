package com.project.carparking.util.converter;

import com.nayan.cms.dto.ScoreDTO;
import com.nayan.cms.entity.Score;

import java.util.ArrayList;
import java.util.List;

public class ScoreConverter {

    public static ScoreDTO toDTO(Score entity) {
        ScoreDTO dto = new ScoreDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setListening(entity.getListening());
        dto.setReading(entity.getReading());
        dto.setWriting(entity.getWriting());
        dto.setSpeaking(entity.getSpeaking());
        dto.setAttendedDate(entity.getAttendedDate());
        dto.setOverall(entity.getOverall());
        return dto;
    }

    public static Score toEntity(ScoreDTO dto) {
        Score entity = new Score();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setListening(dto.getListening());
        entity.setReading(dto.getReading());
        entity.setWriting(dto.getWriting());
        entity.setSpeaking(dto.getSpeaking());
        entity.setAttendedDate(dto.getAttendedDate());
        entity.setOverall(dto.getOverall());
        return entity;
    }

    public static void updateEntity(Score entity, ScoreDTO dto) {
        if (dto.getName() != null && !dto.getName().isEmpty()) {
            entity.setName(dto.getName());
        }
        if (dto.getListening() != null) {
            entity.setListening(dto.getListening());
        }
        if (dto.getReading() != null) {
            entity.setReading(dto.getReading());
        }
        if (dto.getWriting() != null) {
            entity.setWriting(dto.getWriting());
        }
        if (dto.getSpeaking() != null) {
            entity.setSpeaking(dto.getSpeaking());
        }
        if (dto.getAttendedDate() != null) {
            entity.setAttendedDate(dto.getAttendedDate());
        }
        if (dto.getOverall() != null) {
            entity.setOverall(dto.getOverall());
        }
        // Note: We don't update the 'id' and 'applicant' fields as they're usually immutable or managed differently.
    }

    public static List<Score> toEntityList(List<ScoreDTO> dtoList) {
        List<Score> entityList = new ArrayList<>();
        if (dtoList != null) {
            for (ScoreDTO dto : dtoList) {
                entityList.add(toEntity(dto));
            }
        }
        return entityList;
    }
}
