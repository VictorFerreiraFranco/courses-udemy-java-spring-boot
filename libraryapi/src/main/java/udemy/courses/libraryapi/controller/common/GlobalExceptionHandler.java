package udemy.courses.libraryapi.controller.common;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import udemy.courses.libraryapi.controller.dto.error.ErrorField;
import udemy.courses.libraryapi.controller.dto.error.ErrorResponse;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<FieldError> fieldErrors = e.getFieldErrors();

        List<ErrorField> listErrorField = fieldErrors
                .stream()
                .map(fe -> new ErrorField(fe.getField(), fe.getDefaultMessage()))
                .toList();

        return new ErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY.value(), "validation error", listErrorField);
    }

}
