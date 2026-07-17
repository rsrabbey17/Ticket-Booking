package com.ticketbooking.controller;

import com.ticketbooking.dto.request.BookingRequest;
import com.ticketbooking.dto.request.PaymentRequest;
import com.ticketbooking.dto.response.ApiResponse;
import com.ticketbooking.model.Booking;
import com.ticketbooking.model.User;
import com.ticketbooking.service.BookingService;
import com.ticketbooking.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/bookings")
@CrossOrigin(origins = "*")
@SecurityRequirement(name = "bearerAuth")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private UserService userService;

    private User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.findByEmail(email);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Booking>> createBooking(@Valid @RequestBody BookingRequest request) {
        Booking booking = bookingService.createBooking(getCurrentUser(), request);
        return ResponseEntity.ok(ApiResponse.success("Booking created successfully", booking));
    }

    @PostMapping("/pay")
    public ResponseEntity<ApiResponse<Booking>> processPayment(@Valid @RequestBody PaymentRequest request) {
        Booking booking = bookingService.processPayment(request.getBookingId(), request.getPaymentMethod());
        return ResponseEntity.ok(ApiResponse.success("Payment processed successfully", booking));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Booking>> cancelBooking(@PathVariable Long id) {
        Booking booking = bookingService.cancelBooking(id);
        return ResponseEntity.ok(ApiResponse.success("Booking cancelled successfully", booking));
    }

    @GetMapping("/my-bookings")
    public ResponseEntity<ApiResponse<List<Booking>>> getMyBookings() {
        List<Booking> bookings = bookingService.getUserBookings(getCurrentUser());
        return ResponseEntity.ok(ApiResponse.success(bookings));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Booking>> getBooking(@PathVariable Long id) {
        Booking booking = bookingService.getBookingById(id);
        return ResponseEntity.ok(ApiResponse.success(booking));
    }
}