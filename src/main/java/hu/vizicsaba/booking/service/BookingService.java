package hu.vizicsaba.booking.service;

import hu.vizicsaba.booking.service.model.BookingRequest;
import hu.vizicsaba.booking.service.model.BookingResponse;
import reactor.core.publisher.Mono;

public interface BookingService {

    Mono<BookingResponse> createBooking(BookingRequest bookingRequest);

}
