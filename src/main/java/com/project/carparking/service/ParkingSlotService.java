package com.project.carparking.service;

import com.project.carparking.entity.ParkingSlot;
import com.project.carparking.repository.ParkingSlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParkingSlotService {

    private final ParkingSlotRepository parkingSlotRepository;

    @Autowired
    public ParkingSlotService(ParkingSlotRepository parkingSlotRepository) {
        this.parkingSlotRepository = parkingSlotRepository;
    }

    public List<ParkingSlot> getAllParkingSlots() {
        return parkingSlotRepository.findAll();
    }

    public Optional<ParkingSlot> getParkingSlotById(Long id) {
        return parkingSlotRepository.findById(id);
    }

    public Optional<ParkingSlot> getParkingSlotBySlotNumber(Long id) {
        return parkingSlotRepository.findByParkingSlot(id);
    }

    public ParkingSlot saveParkingSlot(ParkingSlot parkingSlot) {
        // You can add business logic here before saving, if needed
        return parkingSlotRepository.save(parkingSlot);
    }

    public void deleteParkingSlot(Long id) {
        parkingSlotRepository.deleteById(id);
    }

    public boolean isParkingSlotAvailable(Long id) {
        Optional<ParkingSlot> optionalParkingSlot = parkingSlotRepository.findById(id);
        return optionalParkingSlot.map(ParkingSlot::isSlotStatus).orElse(false);
    }
}
