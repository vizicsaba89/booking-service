package hu.vizicsaba.booking.service.validator;

import hu.vizicsaba.booking.service.model.validator.ValidationRequest;
import hu.vizicsaba.booking.service.model.validator.ValidationResult;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;

@Service
public class DateChunkValidator implements BaseValidator {

    @Override
    public ValidationResult validate(ValidationRequest validationRequest) {
        if (validationRequest.getFrom().getSecond() != 0 || validationRequest.getFrom().getMinute() != 0 && validationRequest.getFrom().getMinute() != 30) {
            return new ValidationResult(false, ValidatorMessage.INVALID_DATE_CHUNK_MESSAGE);
        }

        if (validationRequest.getTo().getSecond() != 0 || validationRequest.getTo().getMinute() != 0 && validationRequest.getTo().getMinute() != 30) {
            return new ValidationResult(false, ValidatorMessage.INVALID_DATE_CHUNK_MESSAGE);
        }

        return new ValidationResult(true, null);
    }

}
