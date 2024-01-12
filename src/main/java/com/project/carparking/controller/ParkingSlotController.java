package com.project.carparking.controller;

import com.project.carparking.entity.ParkingSlot;
import com.project.carparking.service.ParkingSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/parking-slots")
public class ParkingSlotController {

    private final ParkingSlotService parkingSlotService;

    @Autowired
    public ParkingSlotController(ParkingSlotService parkingSlotService) {
        this.parkingSlotService = parkingSlotService;
    }

    @GetMapping
    public ResponseEntity<List<ParkingSlot>> getAllParkingSlots() {
        List<ParkingSlot> parkingSlots = parkingSlotService.getAllParkingSlots();
        return ResponseEntity.ok(parkingSlots);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingSlot> getParkingSlotById(@PathVariable Long id) {
        Optional<ParkingSlot> parkingSlot = parkingSlotService.getParkingSlotById(id);
        return parkingSlot.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/by-slot-number/{slotNumber}")
    public ResponseEntity<ParkingSlot> getParkingSlotBySlotNumber(@PathVariable Long id) {
        Optional<ParkingSlot> parkingSlot = parkingSlotService.getParkingSlotBySlotNumber(id);
        return parkingSlot.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ParkingSlot> saveParkingSlot(@RequestBody ParkingSlot parkingSlot) {
        ParkingSlot savedParkingSlot = parkingSlotService.saveParkingSlot(parkingSlot);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedParkingSlot);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParkingSlot(@PathVariable Long id) {
        parkingSlotService.deleteParkingSlot(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/is-available/{id}")
    public ResponseEntity<Boolean> isParkingSlotAvailable(@PathVariable Long id) {
        boolean isAvailable = parkingSlotService.isParkingSlotAvailable(id);
        return ResponseEntity.ok(isAvailable);
    }
}
