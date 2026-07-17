package com.ticketbooking.repository;

import com.ticketbooking.model.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TrainRepository extends JpaRepository<Train, Long> {
    @Query("SELECT t FROM Train t WHERE " +
            "LOWER(t.fromStation) = LOWER(:fromStation) AND " +
            "LOWER(t.toStation) = LOWER(:toStation) AND " +
            "DATE(t.departureTime) = DATE(:departureDate)")
    List<Train> searchTrains(@Param("fromStation") String fromStation,
                             @Param("toStation") String toStation,
                             @Param("departureDate") LocalDateTime departureDate);
}