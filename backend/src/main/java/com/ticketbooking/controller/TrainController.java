package com.ticketbooking.controller;
import com.ticketbooking.dto.response.ApiResponse;
import com.ticketbooking.model.Train;
import com.ticketbooking.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/trains")
@CrossOrigin(origins = "*")
public class TrainController {

    @Autowired
    private TrainService trainService;

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<Train>>> searchTrains(
            @RequestParam String from,
            @RequestParam String to,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
        List<Train> trains = trainService.searchTrains(from, to, date);
        return ResponseEntity.ok(ApiResponse.success(trains));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Train>> getTrain(@PathVariable Long id) {
        Train train = trainService.getTrainById(id);
        return ResponseEntity.ok(ApiResponse.success(train));
    }
}