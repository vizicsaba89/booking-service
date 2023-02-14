package hu.vizicsaba.booking.service.validator;

public interface ValidatorMessage {

    String INVALID_DATE_CHUNK_MESSAGE = "Requested date is neither full nor half hour";

    String INVALID_DATE_TIME_INTERVAL_MESSAGE = "Date interval cannot be greater than {0} minutes";

    String INVALID_START_DATE_END_DATE_MESSAGE = "Start date cannot be after end date";

    String START_DATE_END_DATE_EQUAL_MESSAGE = "Start date cannot be equal to end date";

    String INVALID_WORKING_HOURS_MESSAGE = "Cannot book outside of working hours";

    String DATE_OVERLAP_MESSAGE = "Requested date overlaps with an existing one";
}
