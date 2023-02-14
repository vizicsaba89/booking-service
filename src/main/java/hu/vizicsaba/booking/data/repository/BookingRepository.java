package hu.vizicsaba.booking.data.repository;

import hu.vizicsaba.booking.data.model.Booking;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

public interface BookingRepository extends ReactiveCrudRepository<Booking, Long> {

    @Query("SELECT COUNT(*) FROM bookings b WHERE :from < b.to_date AND b.from_date < :to")
    Mono<Integer> countOverLapBookings(LocalDateTime from, LocalDateTime to);

    @Query("SELECT * FROM bookings b WHERE :date BETWEEN b.from_date AND b.to_date")
    Mono<Booking> getBookingByDate(LocalDateTime date);

}
