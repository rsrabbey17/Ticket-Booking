package com.ticketbooking.service;

import com.ticketbooking.model.Bus;
import com.ticketbooking.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class BusService {

    @Autowired
    private BusRepository busRepository;

    public List<Bus> searchBuses(String from, String to, LocalDateTime date) {
        return busRepository.searchBuses(from, to, date);
    }

    public Bus getBusById(Long id) {
        return busRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bus not found with id: " + id));
    }

    public Bus updateBusSeats(Long id, Integer seatsToReduce) {
        Bus bus = getBusById(id);
        bus.setAvailableSeats(bus.getAvailableSeats() - seatsToReduce);
        return busRepository.save(bus);
    }
}