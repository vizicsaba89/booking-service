package hu.vizicsaba.booking.service.validator;

import hu.vizicsaba.booking.service.model.validator.ValidationRequest;
import hu.vizicsaba.booking.service.model.validator.ValidationResult;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.List;

@Service
public class StartDateEndDateValidator implements BaseValidator {

    @Override
    public ValidationResult validate(ValidationRequest validationRequest) {
        if (validationRequest.getFrom().isAfter(validationRequest.getTo())) {
            return new ValidationResult(false, ValidatorMessage.INVALID_START_DATE_END_DATE_MESSAGE);
        }

        return new ValidationResult(true, null);
    }

}
