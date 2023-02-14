package hu.vizicsaba.booking.data.service;

import hu.vizicsaba.booking.data.model.Booking;
import hu.vizicsaba.booking.data.repository.BookingRepository;
import hu.vizicsaba.booking.service.BookingService;
import hu.vizicsaba.booking.service.model.BookingRequest;
import hu.vizicsaba.booking.service.model.BookingResponse;
import hu.vizicsaba.booking.service.validator.ValidatorMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.text.MessageFormat;
import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest(properties = {
    "configuration.validator.dateTimeIntervalInMinutes=180",
    "configuration.validator.workingHoursStart=9",
    "configuration.validator.workingHoursEnd=17",
})
public class BookingServiceTest {

    @MockBean
    private BookingRepository bookingRepository;

    @Autowired
    private BookingService bookingService;

    @BeforeEach
    void setup() {
        Mockito.when(
            bookingRepository.countOverLapBookings(
                any(LocalDateTime.class),
                any(LocalDateTime.class))
            )
            .thenReturn(Mono.just(0));

        Mockito.when(
            bookingRepository.countOverLapBookings(
                LocalDateTime.of(2023, 2, 15, 12, 0, 0),
                LocalDateTime.of(2023, 2, 15, 12, 30, 0))
            )
            .thenReturn(Mono.just(1));

        Mockito.when(bookingRepository.save(any(Booking.class)))
            .thenReturn(Mono.just(
                new Booking(
                    1001L,
                    "12gfdg456",
                    LocalDateTime.of(2023, 2, 17, 12, 0, 0),
                    LocalDateTime.of(2023, 2, 17, 12, 30, 0)
                )
            ));
    }

    @Test
    @DisplayName("BookingService -> happy path")
    void givenMethodToSaveBooking_whenRequestIsValid_newEntryShouldCreated() {
        Mono<BookingResponse> booking = bookingService.createBooking(
            BookingRequest.builder()
                .from(LocalDateTime.of(2023, 2, 17, 12, 0, 0))
                .to(LocalDateTime.of(2023, 2, 17, 12, 30, 0))
                .build()
        );

        StepVerifier.create(booking)
            .expectNextMatches(response -> response != null && "12gfdg456" == response.getUserId())
            .expectComplete()
            .verify();
    }

    @Test
    @DisplayName("BookingService -> RequestIntervalIsGreaterThanNHours")
    void givenMethodToSaveBooking_whenRequestIntervalIsGreaterThanNHours_ExceptionShouldBeThrown() {
        Mono<BookingResponse> booking = bookingService.createBooking(
            BookingRequest.builder()
                .from(LocalDateTime.of(2023, 2, 17, 12, 0, 0))
                .to(LocalDateTime.of(2023, 2, 17, 12, 30, 0).plusHours(4))
                .build()
        );

        StepVerifier.create(booking)
            .expectErrorMessage(MessageFormat.format(ValidatorMessage.INVALID_DATE_TIME_INTERVAL_MESSAGE, 180))
            .verify();
    }

    @Test
    @DisplayName("BookingService -> RequestDateIsNotInWorkingHours")
    void givenMethodToSaveBooking_whenRequestDateIsNotInWorkingHours_ExceptionShouldBeThrown() {
        Mono<BookingResponse> booking = bookingService.createBooking(
            BookingRequest.builder()
                .from(LocalDateTime.of(2023, 2, 18, 12, 0, 0))
                .to(LocalDateTime.of(2023, 2, 18, 12, 30, 0))
                .build()
        );

        StepVerifier.create(booking)
            .expectErrorMessage(ValidatorMessage.INVALID_WORKING_HOURS_MESSAGE)
            .verify();
    }

    @Test
    @DisplayName("BookingService -> RequestDateIsNotHalfOrFullHour")
    void givenMethodToSaveBooking_whenRequestDateIsNotHalfOrFullHour_ExceptionShouldBeThrown() {
        Mono<BookingResponse> booking = bookingService.createBooking(
            BookingRequest.builder()
                .from(LocalDateTime.of(2023, 2, 17, 12, 12))
                .to(LocalDateTime.of(2023, 2, 17, 12, 30))
                .build()
        );

        StepVerifier.create(booking)
            .expectErrorMessage(ValidatorMessage.INVALID_DATE_CHUNK_MESSAGE)
            .verify();
    }

    @Test
    @DisplayName("BookingService -> RequestDateOverlapsWithExistingBooking")
    void givenMethodToSaveBooking_whenRequestDateOverlapsWithExistingBooking_ExceptionShouldBeThrown() {
        Mono<BookingResponse> booking = bookingService.createBooking(
            BookingRequest.builder()
                .from(LocalDateTime.of(2023, 2, 15, 12, 0, 0))
                .to(LocalDateTime.of(2023, 2, 15, 12, 30, 0))
                .build()
        );

        StepVerifier.create(booking)
            .expectErrorMessage(ValidatorMessage.DATE_OVERLAP_MESSAGE)
            .verify();
    }

}
