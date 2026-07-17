package com.ticketbooking.service;

import com.ticketbooking.model.Train;
import com.ticketbooking.repository.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TrainService {

    @Autowired
    private TrainRepository trainRepository;

    public List<Train> searchTrains(String from, String to, LocalDateTime date) {
        return trainRepository.searchTrains(from, to, date);
    }

    public Train getTrainById(Long id) {
        return trainRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Train not found with id: " + id));
    }

    public Train updateTrainSeats(Long id, Integer seatsToReduce) {
        Train train = getTrainById(id);
        train.setAvailableSeats(train.getAvailableSeats() - seatsToReduce);
        return trainRepository.save(train);
    }
}