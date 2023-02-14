package hu.vizicsaba.booking.controller;

import hu.vizicsaba.booking.service.exception.BookingRequestException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Map;

@RestControllerAdvice
@Log4j2
public class RestExceptionHandler {

    @ExceptionHandler(BookingRequestException.class)
    ResponseEntity<Map<String, List<String>>> bookingRequestValidationError(BookingRequestException ex) {
        return ResponseEntity.internalServerError().body(Map.of("errors", ex.getErrors()));
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<Map<String, String>> generalError(Exception ex) {
        log.error(ex.getMessage(), ex);

        return ResponseEntity.internalServerError().body(Map.of("errors", ex.getMessage()));
    }

}
