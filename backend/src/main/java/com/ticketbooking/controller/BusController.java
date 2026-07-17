package com.ticketbooking.controller;

import com.ticketbooking.dto.response.ApiResponse;
import com.ticketbooking.model.Bus;
import com.ticketbooking.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/buses")
@CrossOrigin(origins = "*")
public class BusController {

    @Autowired
    private BusService busService;

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<Bus>>> searchBuses(
            @RequestParam String from,
            @RequestParam String to,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
        List<Bus> buses = busService.searchBuses(from, to, date);
        return ResponseEntity.ok(ApiResponse.success(buses));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Bus>> getBus(@PathVariable Long id) {
        Bus bus = busService.getBusById(id);
        return ResponseEntity.ok(ApiResponse.success(bus));
    }
}