package com.ticketbooking.repository;

import com.ticketbooking.model.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BusRepository extends JpaRepository<Bus, Long> {
    @Query("SELECT b FROM Bus b WHERE " +
            "LOWER(b.fromCity) = LOWER(:fromCity) AND " +
            "LOWER(b.toCity) = LOWER(:toCity) AND " +
            "DATE(b.departureTime) = DATE(:departureDate)")
    List<Bus> searchBuses(@Param("fromCity") String fromCity,
                          @Param("toCity") String toCity,
                          @Param("departureDate") LocalDateTime departureDate);
}
