package hu.vizicsaba.booking.service.validator;

import hu.vizicsaba.booking.service.model.validator.ValidationRequest;
import hu.vizicsaba.booking.service.model.validator.ValidationResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class WorkingHoursValidator implements BaseValidator {

    @Value("${configuration.validator.workingHoursStart}")
    private Integer workingHoursStart;

    @Value("${configuration.validator.workingHoursEnd}")
    private Integer workingHoursEnd;

    @Override
    public ValidationResult validate(ValidationRequest validationRequest) {
        DayOfWeek fromDayOfWeek = validationRequest.getFrom().getDayOfWeek();
        DayOfWeek toDayOfWeek = validationRequest.getTo().getDayOfWeek();

        List<DayOfWeek> weekend = List.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY);

        if (weekend.contains(fromDayOfWeek) || weekend.contains(toDayOfWeek)) {
            return new ValidationResult(false, ValidatorMessage.INVALID_WORKING_HOURS_MESSAGE);
        }

        if (validationRequest.getFrom().getHour() < workingHoursStart || validationRequest.getFrom().getHour() > workingHoursEnd) {
            return new ValidationResult(false, ValidatorMessage.INVALID_WORKING_HOURS_MESSAGE);
        }

        if (validationRequest.getTo().getHour() < workingHoursStart || validationRequest.getTo().getHour() > workingHoursEnd) {
            return new ValidationResult(false, ValidatorMessage.INVALID_WORKING_HOURS_MESSAGE);
        }

        return new ValidationResult(true, null);
    }

}
