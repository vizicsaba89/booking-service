package hu.vizicsaba.booking.service.validator;

import hu.vizicsaba.booking.service.model.validator.ValidationRequest;
import hu.vizicsaba.booking.service.model.validator.ValidationResult;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;

@Service
public class DateTimeIntervalValidator implements BaseValidator {

    @Override
    public ValidationResult validate(ValidationRequest validationRequest) {
        long diffInHours = validationRequest.getFrom().until(validationRequest.getTo(), ChronoUnit.HOURS);

        if (diffInHours > 3) {
            return new ValidationResult(false, "Cannot be more than 3 hours");
        }

        return new ValidationResult(true, null);
    }

}
