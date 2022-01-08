package com.Booking.exception.Handler;

import com.Booking.Dto.ErrorDTO;
import com.Booking.exception.RecordNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionalHandler extends ResponseEntityExceptionHandler
{
    @ExceptionHandler(RecordNotFoundException.class)
    public final ResponseEntity<ErrorDTO> handleRecordNotFoundException(RecordNotFoundException e, WebRequest req){
        ErrorDTO response = new ErrorDTO(e.getMessage(), 400);

        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }
}
