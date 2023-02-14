package hu.vizicsaba.booking.service;

import hu.vizicsaba.booking.data.model.Booking;
import hu.vizicsaba.booking.data.repository.BookingRepository;
import hu.vizicsaba.booking.service.exception.BookingRequestException;
import hu.vizicsaba.booking.service.model.BookingRequest;
import hu.vizicsaba.booking.service.model.BookingResponse;
import hu.vizicsaba.booking.service.model.validator.ValidationRequest;
import hu.vizicsaba.booking.service.model.validator.ValidationResult;
import hu.vizicsaba.booking.service.validator.BaseValidator;
import hu.vizicsaba.booking.service.validator.ValidatorMessage;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {

    private BookingRepository bookingRepository;

    private List<BaseValidator> validators;

    public BookingServiceImpl(BookingRepository bookingRepository,  List<BaseValidator> validators) {
        this.bookingRepository = bookingRepository;
        this.validators = validators;
    }

    @Override
    public Flux<BookingResponse> getAllBookings() {
        return bookingRepository.findAll()
            .map(booking -> new ModelMapper().map(booking, BookingResponse.class));
    }

    @Override
    public Mono<Integer> getNumberOfOverLapBookings(LocalDateTime from, LocalDateTime to) {
        return bookingRepository.countOverLapBookings(from, to);
    }

    @Override
    public Mono<BookingResponse> createBooking(BookingRequest bookingRequest) {
        var validationRequest = new ValidationRequest(bookingRequest.getFrom(), bookingRequest.getTo());

        List<String> errors = validators.stream()
            .map(validator -> validator.validate(validationRequest))
            .filter(validationResult -> !validationResult.isValid())
            .map(ValidationResult::getErrorMessage)
            .collect(Collectors.toList());

        if (!errors.isEmpty()) {
            return Mono.error(new BookingRequestException(errors));
        }

        return getNumberOfOverLapBookings(bookingRequest.getFrom(), bookingRequest.getTo())
            .flatMap(numberOfOverLapBookings -> {
                if (numberOfOverLapBookings > 0) {
                    return Mono.error(new BookingRequestException(List.of(ValidatorMessage.DATE_OVERLAP_MESSAGE)));
                }

                return bookingRepository.save(getConvertedBooking(bookingRequest))
                    .flatMap(createdBooking -> Mono.just(getConvertedBookingResponse(createdBooking)));
            });
    }

    @Override
    public Mono<BookingResponse> getBookingByDate(LocalDateTime date) {
        return bookingRepository.getBookingByDate(date)
            .map(this::getConvertedBookingResponse);
    }

    private Booking getConvertedBooking(BookingRequest request) {
        return Booking.builder()
            .userId(request.getUserId())
            .from(request.getFrom())
            .to(request.getTo())
            .build();
    }

    private BookingResponse getConvertedBookingResponse(Booking booking) {
        return BookingResponse.builder()
            .userId(booking.getUserId())
            .from(booking.getFrom())
            .to(booking.getTo())
            .build();
    }
}
