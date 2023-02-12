package hu.vizicsaba.booking.data.repository;

import hu.vizicsaba.booking.data.model.Booking;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface BookingRepository extends ReactiveCrudRepository<Booking, Long> {
}
