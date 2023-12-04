package com.project.carparking.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
    private String parkingSlot;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}