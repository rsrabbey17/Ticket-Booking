package com.ticketbooking.dto.request;

import com.ticketbooking.model.enums.TransportType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class BookingRequest {
    @NotNull(message = "Transport type is required")
    private TransportType transportType;

    @NotNull(message = "Transport ID is required")
    private Long transportId;

    @NotNull(message = "Number of seats is required")
    @Min(value = 1, message = "At least 1 seat is required")
    private Integer numberOfSeats;

    private String seatNumbers;

    // Constructors
    public BookingRequest() {
    }

    public BookingRequest(TransportType transportType, Long transportId, Integer numberOfSeats, String seatNumbers) {
        this.transportType = transportType;
        this.transportId = transportId;
        this.numberOfSeats = numberOfSeats;
        this.seatNumbers = seatNumbers;
    }

    // Getters and Setters
    public TransportType getTransportType() {
        return transportType;
    }

    public void setTransportType(TransportType transportType) {
        this.transportType = transportType;
    }

    public Long getTransportId() {
        return transportId;
    }

    public void setTransportId(Long transportId) {
        this.transportId = transportId;
    }

    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(Integer numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public String getSeatNumbers() {
        return seatNumbers;
    }

    public void setSeatNumbers(String seatNumbers) {
        this.seatNumbers = seatNumbers;
    }
}