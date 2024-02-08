package com.project.carparking.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class VehicleEntryExitStampResponse {
    private LocalDate entryDate;
    private LocalTime entryTime;
    private Double  totalHoursParked;

}
