package org.example.module16.handler;

import jakarta.persistence.OptimisticLockException;
import org.example.module16.exception.EntityNotFoundException;
import org.example.module16.model.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = EntityNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleException(EntityNotFoundException exception) {
        return new ErrorResponse(HttpStatus.NOT_FOUND.name(), exception.getMessage());
    }

    @ExceptionHandler(value = OptimisticLockException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleException(OptimisticLockException exception) {
        return new ErrorResponse(HttpStatus.CONFLICT.name(), exception.getMessage());
    }

    @ExceptionHandler(value = Throwable.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleException(Throwable exception) {
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.name(), exception.getMessage());
    }
}
