package hu.vizicsaba.booking.service.validator;

import hu.vizicsaba.booking.service.model.validator.ValidationRequest;
import hu.vizicsaba.booking.service.model.validator.ValidationResult;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class StartDateEndDateEqualValidator implements BaseValidator {

    @Override
    public ValidationResult validate(ValidationRequest validationRequest) {
        if (validationRequest.getFrom().isEqual(validationRequest.getTo())) {
            return new ValidationResult(false, ValidatorMessage.START_DATE_END_DATE_EQUAL_MESSAGE);
        }

        return new ValidationResult(true, null);
    }

}
