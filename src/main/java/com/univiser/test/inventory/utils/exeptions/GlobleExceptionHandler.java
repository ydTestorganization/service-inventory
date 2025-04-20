package com.univiser.test.inventory.utils.exeptions;

import com.univiser.test.inventory.dto.ErrorResponse;
import com.univiser.test.inventory.utils.message.ErrorMessage;
import org.springframework.http.ResponseEntity;

import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class GlobleExceptionHandler {


    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorResponse> handleApiException(ApiException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getCode(), ex.getMessage());
        return ResponseEntity.status(ex.getStatusCode()).body(errorResponse);

    }
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErrorResponse> handleHttpStatusCodeException(NoResourceFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(404, ex.getResourcePath()+ ErrorMessage.ENDPOINT_NOT_FOUND);
        return ResponseEntity.status(404).body(errorResponse);
    }
    @ExceptionHandler(ResourceAccessException.class)
    public ResponseEntity<ErrorResponse> handleHttpStatusCodeException(ResourceAccessException ex) {
        ErrorResponse errorResponse = new ErrorResponse(503, ex.getMessage());
        return ResponseEntity.status(503).body(errorResponse);
    }
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleHttpStatusCodeException(HttpRequestMethodNotSupportedException ex) {
        ErrorResponse errorResponse = new ErrorResponse(405, ex.getMessage());
        return ResponseEntity.status(405).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse(500, ex.getMessage());
        return ResponseEntity.status(500).body(errorResponse);
    }
}
