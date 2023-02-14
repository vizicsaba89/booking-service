package hu.vizicsaba.booking.service.validator;

import hu.vizicsaba.booking.service.model.validator.ValidationRequest;
import hu.vizicsaba.booking.service.model.validator.ValidationResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.time.temporal.ChronoUnit;

@Service
public class DateTimeIntervalValidator implements BaseValidator {

    @Value("${configuration.validator.dateTimeIntervalInMinutes}")
    private Integer dateTimeIntervalInMinutes;

    @Override
    public ValidationResult validate(ValidationRequest validationRequest) {
        long diffInMinutes = validationRequest.getFrom().until(validationRequest.getTo(), ChronoUnit.MINUTES);

        if (diffInMinutes > dateTimeIntervalInMinutes) {
            return new ValidationResult(false, MessageFormat.format(ValidatorMessage.INVALID_DATE_TIME_INTERVAL_MESSAGE, dateTimeIntervalInMinutes));
        }

        return new ValidationResult(true, null);
    }

}
