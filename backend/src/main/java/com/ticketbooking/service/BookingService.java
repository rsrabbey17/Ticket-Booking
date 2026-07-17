package com.ticketbooking.service;
import com.ticketbooking.dto.request.BookingRequest;
import com.ticketbooking.model.Booking;
import com.ticketbooking.model.User;
import com.ticketbooking.model.enums.BookingStatus;
import com.ticketbooking.model.enums.TransportType;
import com.ticketbooking.repository.BookingRepository;
import com.ticketbooking.utils.BookingReferenceGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private FlightService flightService;

    @Autowired
    private TrainService trainService;

    @Autowired
    private BusService busService;

    @Autowired
    private BookingReferenceGenerator referenceGenerator;

    @Transactional
    public Booking createBooking(User user, BookingRequest request) {
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setTransportType(request.getTransportType());
        booking.setTransportId(request.getTransportId());
        booking.setNumberOfSeats(request.getNumberOfSeats());
        booking.setSeatNumbers(request.getSeatNumbers());
        booking.setBookingReference(referenceGenerator.generate());
        booking.setStatus(BookingStatus.CONFIRMED);

        // Set transport details and calculate price
        if (request.getTransportType() == TransportType.FLIGHT) {
            var flight = flightService.getFlightById(request.getTransportId());
            booking.setFromLocation(flight.getFromCity());
            booking.setToLocation(flight.getToCity());
            booking.setTravelDate(flight.getDepartureTime());
            booking.setTotalPrice(flight.getPrice().multiply(BigDecimal.valueOf(request.getNumberOfSeats())));
            flightService.updateFlightSeats(request.getTransportId(), request.getNumberOfSeats());
        } else if (request.getTransportType() == TransportType.TRAIN) {
            var train = trainService.getTrainById(request.getTransportId());
            booking.setFromLocation(train.getFromStation());
            booking.setToLocation(train.getToStation());
            booking.setTravelDate(train.getDepartureTime());
            booking.setTotalPrice(train.getPrice().multiply(BigDecimal.valueOf(request.getNumberOfSeats())));
            trainService.updateTrainSeats(request.getTransportId(), request.getNumberOfSeats());
        } else if (request.getTransportType() == TransportType.BUS) {
            var bus = busService.getBusById(request.getTransportId());
            booking.setFromLocation(bus.getFromCity());
            booking.setToLocation(bus.getToCity());
            booking.setTravelDate(bus.getDepartureTime());
            booking.setTotalPrice(bus.getPrice().multiply(BigDecimal.valueOf(request.getNumberOfSeats())));
            busService.updateBusSeats(request.getTransportId(), request.getNumberOfSeats());
        }

        return bookingRepository.save(booking);
    }

    @Transactional
    public Booking processPayment(Long bookingId, String paymentMethod) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        booking.setPaymentStatus("COMPLETED");
        booking.setPaymentMethod(paymentMethod);
        return bookingRepository.save(booking);
    }

    @Transactional
    public Booking cancelBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        booking.setStatus(BookingStatus.CANCELLED);

        // Restore seats (simplified - you'd need to restore to the specific transport)
        return bookingRepository.save(booking);
    }

    public List<Booking> getUserBookings(User user) {
        return bookingRepository.findByUserOrderByBookingDateDesc(user);
    }

    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
    }
}