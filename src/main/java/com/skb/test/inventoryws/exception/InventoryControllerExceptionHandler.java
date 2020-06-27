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

    @ExceptionHandler(InventoryNotFoundException.class)
    public final ResponseEntity<LibraryApiError> handleLibraryResourceNotFoundException(
            InventoryNotFoundException e, WebRequest webRequest) {

        return new ResponseEntity<>(new LibraryApiError(e.getTraceId(), e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InventoryAlreadyExistException.class)
    public final ResponseEntity<LibraryApiError> handleLibraryResourceAlreadyExistException(
            InventoryAlreadyExistException e, WebRequest webRequest) {

        return new ResponseEntity<>(new LibraryApiError(e.getTraceId(), e.getMessage()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InventoryBadRequestException.class)
    public final ResponseEntity<LibraryApiError> handleLibraryResourceBadRequestException(
            InventoryBadRequestException e, WebRequest webRequest) {

        return new ResponseEntity<>(new LibraryApiError(e.getTraceId(), e.getMessage()), HttpStatus.BAD_REQUEST);
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
