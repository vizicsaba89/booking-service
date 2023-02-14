package hu.vizicsaba.booking.service.exception;

import java.util.List;

public class BookingRequestException extends RuntimeException {

    private List<String> errors;

    public BookingRequestException(List<String> errors) {
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }

    @Override
    public String getMessage() {
        return String.join(",", errors);
    }

}
