package hu.vizicsaba.booking.service.model.validator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidationResult {

    private boolean isValid;

    private String errorMessage;

}
