package com.skb.test.inventoryws.exception;

import com.skb.test.inventoryws.util.LibraryApiUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.UUID;

@ControllerAdvice
public class InventoryControllerExceptionHandler extends ResponseEntityExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger(InventoryControllerExceptionHandler.class);

    @ExceptionHandler(InventoryResourceNotFoundException.class)
    public final ResponseEntity<LibraryApiError> handleLibraryResourceNotFoundException(
            InventoryResourceNotFoundException e, WebRequest webRequest) {

        return new ResponseEntity<>(new LibraryApiError(e.getTraceId(), e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InventoryResourceAlreadyExistException.class)
    public final ResponseEntity<LibraryApiError> handleLibraryResourceAlreadyExistException(
            InventoryResourceAlreadyExistException e, WebRequest webRequest) {

        return new ResponseEntity<>(new LibraryApiError(e.getTraceId(), e.getMessage()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InventoryResourceBadRequestException.class)
    public final ResponseEntity<LibraryApiError> handleLibraryResourceBadRequestException(
            InventoryResourceBadRequestException e, WebRequest webRequest) {

        return new ResponseEntity<>(new LibraryApiError(e.getTraceId(), e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InventoryResourceUnauthorizedException.class)
    public final ResponseEntity<LibraryApiError> handleLibraryResourceUnauthorizedException(
            InventoryResourceUnauthorizedException e, WebRequest webRequest) {

        return new ResponseEntity<>(new LibraryApiError(e.getTraceId(), e.getMessage()), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<LibraryApiError> handleAllException(
            Exception e, WebRequest webRequest) {

        String traceId = getTraceId(webRequest);
        logger.error(traceId, e);
        return new ResponseEntity<>(new LibraryApiError(traceId, e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    private String getTraceId(WebRequest webRequest) {
        String traceId = webRequest.getHeader("Trace-Id");
        if(!LibraryApiUtils.doesStringValueExist(traceId)) {
            traceId = UUID.randomUUID().toString();
        }

        return traceId;
    }
}
