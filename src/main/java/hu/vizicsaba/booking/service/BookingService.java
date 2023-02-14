package hu.vizicsaba.booking.service;

import hu.vizicsaba.booking.service.model.BookingRequest;
import hu.vizicsaba.booking.service.model.BookingResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

public interface BookingService {

    Flux<BookingResponse> getAllBookings();

    Mono<Integer> getNumberOfOverLapBookings(LocalDateTime from, LocalDateTime to);

    Mono<BookingResponse> createBooking(BookingRequest bookingRequest);

    Mono<BookingResponse> getBookingByDate(LocalDateTime date);

}
