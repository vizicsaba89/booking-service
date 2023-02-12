package hu.vizicsaba.booking.service;

import hu.vizicsaba.booking.data.model.Booking;
import hu.vizicsaba.booking.data.repository.BookingRepository;
import hu.vizicsaba.booking.service.model.BookingRequest;
import hu.vizicsaba.booking.service.model.BookingResponse;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class BookingServiceImpl implements BookingService {

    private BookingRepository bookingRepository;

    public BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public Mono<BookingResponse> createBooking(BookingRequest bookingRequest) {
        return bookingRepository.save(new ModelMapper().map(bookingRequest, Booking.class))
            .flatMap(createdBooking -> Mono.just(new ModelMapper().map(createdBooking, BookingResponse.class)));
    }

}
