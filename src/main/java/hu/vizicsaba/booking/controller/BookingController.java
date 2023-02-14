package hu.vizicsaba.booking.controller;

import hu.vizicsaba.booking.service.BookingService;
import hu.vizicsaba.booking.service.model.BookingRequest;
import hu.vizicsaba.booking.service.model.BookingResponse;
import jakarta.validation.Valid;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@RequestMapping("/bookings")
@RestController()
public class BookingController {

    private BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping
    public Flux<BookingResponse> getAllBookings(@Param("date")LocalDateTime date) {
        if (date != null) {
            return bookingService.getBookingByDate(date).flux();
        }

        return bookingService.getAllBookings();
    }

    @PostMapping
    public Mono<BookingResponse> createBooking(@Valid @RequestBody BookingRequest request) {
        return bookingService.createBooking(request);
    }
}
