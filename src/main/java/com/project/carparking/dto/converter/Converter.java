package com.project.carparking.dto.converter;


import com.project.carparking.dto.PaginationResponse;
import com.project.carparking.dto.UserResponse;
import com.project.carparking.dto.VehicleEntryExitStampResponse;
import com.project.carparking.entity.EnumRole;
import com.project.carparking.entity.User;
import com.project.carparking.entity.VehicleEntryExitStamp;
import org.springframework.data.domain.Page;

import java.time.temporal.ChronoUnit;

public class Converter {

    public static UserResponse convertToUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .phoneNo(user.getPhoneNo())
                .address(user.getAddress())
                .isAdmin(user.getRole() == EnumRole.ADMIN)
                .vehicles(user.getVehicles())
                .createdAt(user.getCreatedAt())
                .build();
    }

    public static VehicleEntryExitStampResponse convertToVehicleEntryExitStampResponse(VehicleEntryExitStamp stamp) {
        VehicleEntryExitStampResponse response = new VehicleEntryExitStampResponse();
        response.setEntryDate(stamp.getEntryTime().toLocalDate());
        response.setEntryTime(stamp.getEntryTime().toLocalTime());

        if (stamp.getExitTime() != null) {
            // Calculate total hours parked
            long hours = ChronoUnit.HOURS.between(stamp.getEntryTime(), stamp.getExitTime());
            long minutes = ChronoUnit.MINUTES.between(stamp.getEntryTime(), stamp.getExitTime()) % 60;
            double totalHoursParked = hours + (double) minutes / 60;
            response.setTotalHoursParked(totalHoursParked);
        } else {
            response.setTotalHoursParked(null);
        }

        return response;
    }

    public static PaginationResponse convertPageToPageResponse(Page<?> page){
        PaginationResponse paginationResponse = new PaginationResponse();
        paginationResponse.setPageNo(page.getNumber());
        paginationResponse.setPageSize(page.getSize());
        paginationResponse.setTotalElements(page.getTotalElements());
        paginationResponse.setTotalPages(page.getTotalPages());
        paginationResponse.setLast(page.isLast());

        return paginationResponse;
    }


}
