package com.ticketbooking.repository;

import com.ticketbooking.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    @Query("SELECT f FROM Flight f WHERE " +
            "LOWER(f.fromCity) = LOWER(:fromCity) AND " +
            "LOWER(f.toCity) = LOWER(:toCity) AND " +
            "DATE(f.departureTime) = DATE(:departureDate) AND " +
            "(:classType IS NULL OR f.classType = :classType)")
    List<Flight> searchFlights(@Param("fromCity") String fromCity,
                               @Param("toCity") String toCity,
                               @Param("departureDate") LocalDateTime departureDate,
                               @Param("classType") String classType);
}