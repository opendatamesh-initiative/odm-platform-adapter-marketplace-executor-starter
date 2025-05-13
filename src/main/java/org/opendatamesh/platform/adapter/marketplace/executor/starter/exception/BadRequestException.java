package org.opendatamesh.platform.adapter.marketplace.executor.starter.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends OdmApiException {
    public BadRequestException(String message) {
        super(message, HttpStatus.BAD_REQUEST, "BadRequest");
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause, HttpStatus.BAD_REQUEST, "BadRequest");
    }
} 