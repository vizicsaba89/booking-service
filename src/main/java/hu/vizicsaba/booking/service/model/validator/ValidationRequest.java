package hu.vizicsaba.booking.service.model.validator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidationRequest {

    private LocalDateTime from;

    private LocalDateTime to;

}
