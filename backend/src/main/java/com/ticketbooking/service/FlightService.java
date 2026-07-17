package com.ticketbooking.service;

import com.ticketbooking.model.Flight;
import com.ticketbooking.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    public List<Flight> searchFlights(String from, String to, LocalDateTime date, String classType) {
        return flightRepository.searchFlights(from, to, date, classType);
    }

    public Flight getFlightById(Long id) {
        return flightRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Flight not found with id: " + id));
    }

    public Flight updateFlightSeats(Long id, Integer seatsToReduce) {
        Flight flight = getFlightById(id);
        flight.setAvailableSeats(flight.getAvailableSeats() - seatsToReduce);
        return flightRepository.save(flight);
    }
}