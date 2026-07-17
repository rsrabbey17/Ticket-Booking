package com.ticketbooking.utils;

import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
public class BookingReferenceGenerator {

    public String generate() {
        return "TKT" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}