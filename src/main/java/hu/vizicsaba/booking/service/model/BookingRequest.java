package hu.vizicsaba.booking.service.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingRequest {

    @NotNull
    private String userId;

    @NotNull
    private LocalDateTime from;

    @NotNull
    private LocalDateTime to;

}
