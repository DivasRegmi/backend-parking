package com.project.carparking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "vehicle")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numberPlate;

    private String model;

    @OneToOne(mappedBy = "vehicle", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private ParkingSlot parkingSlot;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<VehicleEntryExitStamp> entryExitStamps;


    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

}