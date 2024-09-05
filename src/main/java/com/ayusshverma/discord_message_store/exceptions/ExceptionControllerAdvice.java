package com.ayusshverma.discord_message_store.exceptions;

import java.util.List;
import java.util.ArrayList;

import com.ayusshverma.discord_message_store.dto.RestErrorDto;
import com.ayusshverma.discord_message_store.dto.ValidationMessageDto;

import com.ayusshverma.discord_message_store.exceptions.base.EntityConflictException;
import com.ayusshverma.discord_message_store.exceptions.base.EntityNotFoundException;

import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties.Http;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.net.URI;
import java.time.OffsetDateTime;

@ControllerAdvice
public class ExceptionControllerAdvice {
    private static void fillExceptionInfo(RestErrorDto errorDto, Exception ex, WebRequest request) {
        errorDto.setMessage(ex.getLocalizedMessage());
        URI requestPath = URI.create(request.getDescription(false));
        errorDto.setPath(requestPath);
        errorDto.setTimestamp(OffsetDateTime.now());
        // TODO: Only return stack trace in dev environments
        StringBuilder stackTrace = new StringBuilder();
        for (StackTraceElement element : ex.getStackTrace()) {
            stackTrace.append(element.toString());
            stackTrace.append("\n");
        }
        errorDto.setTrace(stackTrace.toString());
        errorDto.setError(ex.getClass().getSimpleName());
    }

    private static void fillValidationInfo(RestErrorDto errorDto, MethodArgumentNotValidException ex, WebRequest request) {
        BindingResult bindingResult = ex.getBindingResult();
        List<ValidationMessageDto> schemaValidationErrors = new ArrayList<>();

        bindingResult.getAllErrors().forEach(error -> {
            ValidationMessageDto validationMessageDto = new ValidationMessageDto();
            validationMessageDto.setMessage(error.getDefaultMessage());
            schemaValidationErrors.add(validationMessageDto);
        });
        errorDto.setSchemaValidationErrors(schemaValidationErrors);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<RestErrorDto> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;

        RestErrorDto errorDto = new RestErrorDto();
        errorDto.setStatus(httpStatus.value());
        fillExceptionInfo(errorDto, ex, request);
        return ResponseEntity.status(httpStatus).body(errorDto);
    }

    @ExceptionHandler(EntityConflictException.class)
    public ResponseEntity<RestErrorDto> handleEntityExistsException(EntityConflictException ex, WebRequest request) {
        HttpStatus httpStatus = HttpStatus.CONFLICT;

        RestErrorDto errorDto = new RestErrorDto();
        errorDto.setStatus(httpStatus.value());
        fillExceptionInfo(errorDto, ex, request);
        return ResponseEntity.status(httpStatus).body(errorDto);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RestErrorDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        
        RestErrorDto errorDto = new RestErrorDto();
        errorDto.setStatus(httpStatus.value());
        fillExceptionInfo(errorDto, ex, request);
        fillValidationInfo(errorDto, ex, request);
        return ResponseEntity.status(httpStatus).body(errorDto);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<RestErrorDto> handleException(Exception ex, WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        RestErrorDto errorDto = new RestErrorDto();
        errorDto.setStatus(status.value());
        fillExceptionInfo(errorDto, ex, request);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDto);
    }
}
