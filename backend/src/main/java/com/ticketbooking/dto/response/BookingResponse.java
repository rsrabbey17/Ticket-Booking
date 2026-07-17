package com.ticketbooking.dto.response;
import com.ticketbooking.model.enums.BookingStatus;
import com.ticketbooking.model.enums.TransportType;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class BookingResponse {
    private Long id;
    private String bookingReference;
    private TransportType transportType;
    private Long transportId;
    private String fromLocation;
    private String toLocation;
    private LocalDateTime travelDate;
    private Integer numberOfSeats;
    private BigDecimal totalPrice;
    private String seatNumbers;
    private BookingStatus status;
    private LocalDateTime bookingDate;
    private String paymentStatus;

    // Constructors
    public BookingResponse() {}

    public BookingResponse(Long id, String bookingReference, TransportType transportType,
                           Long transportId, String fromLocation, String toLocation,
                           LocalDateTime travelDate, Integer numberOfSeats, BigDecimal totalPrice,
                           String seatNumbers, BookingStatus status, LocalDateTime bookingDate,
                           String paymentStatus) {
        this.id = id;
        this.bookingReference = bookingReference;
        this.transportType = transportType;
        this.transportId = transportId;
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
        this.travelDate = travelDate;
        this.numberOfSeats = numberOfSeats;
        this.totalPrice = totalPrice;
        this.seatNumbers = seatNumbers;
        this.status = status;
        this.bookingDate = bookingDate;
        this.paymentStatus = paymentStatus;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookingReference() {
        return bookingReference;
    }

    public void setBookingReference(String bookingReference) {
        this.bookingReference = bookingReference;
    }

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

    public String getFromLocation() {
        return fromLocation;
    }

    public void setFromLocation(String fromLocation) {
        this.fromLocation = fromLocation;
    }

    public String getToLocation() {
        return toLocation;
    }

    public void setToLocation(String toLocation) {
        this.toLocation = toLocation;
    }

    public LocalDateTime getTravelDate() {
        return travelDate;
    }

    public void setTravelDate(LocalDateTime travelDate) {
        this.travelDate = travelDate;
    }

    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(Integer numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getSeatNumbers() {
        return seatNumbers;
    }

    public void setSeatNumbers(String seatNumbers) {
        this.seatNumbers = seatNumbers;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public LocalDateTime getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDateTime bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}