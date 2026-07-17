// TicketBookingApplication.java
package com.ticketbooking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendApplication {
	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
		System.out.println("========================================");
		System.out.println("Ticket Booking System Backend Started!");
		System.out.println("Swagger UI: http://localhost:8080/api/swagger-ui.html");
		System.out.println("========================================");
	}
}