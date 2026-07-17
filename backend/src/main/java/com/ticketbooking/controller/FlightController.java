package com.ticketbooking.controller;

import com.ticketbooking.dto.response.ApiResponse;
import com.ticketbooking.model.Flight;
import com.ticketbooking.service.FlightService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/flights")
@CrossOrigin(origins = "*")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @GetMapping("/search")
    @Operation(summary = "Search for flights")
    public ResponseEntity<ApiResponse<List<Flight>>> searchFlights(
            @RequestParam String from,
            @RequestParam String to,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date,
            @RequestParam(required = false) String classType) {
        List<Flight> flights = flightService.searchFlights(from, to, date, classType);
        return ResponseEntity.ok(ApiResponse.success(flights));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get flight by ID")
    public ResponseEntity<ApiResponse<Flight>> getFlight(@PathVariable Long id) {
        Flight flight = flightService.getFlightById(id);
        return ResponseEntity.ok(ApiResponse.success(flight));
    }
}