package com.project.carparking.dto.converter;


import com.project.carparking.dto.PaginationResponse;
import com.project.carparking.dto.UserResponse;
import com.project.carparking.entity.EnumRole;
import com.project.carparking.entity.User;
import org.springframework.data.domain.Page;

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
