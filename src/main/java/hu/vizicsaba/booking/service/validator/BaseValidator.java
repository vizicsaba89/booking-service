package hu.vizicsaba.booking.service.validator;

import hu.vizicsaba.booking.service.model.validator.ValidationRequest;
import hu.vizicsaba.booking.service.model.validator.ValidationResult;
import reactor.core.publisher.Mono;

public interface BaseValidator {

    ValidationResult validate(ValidationRequest c);

}
