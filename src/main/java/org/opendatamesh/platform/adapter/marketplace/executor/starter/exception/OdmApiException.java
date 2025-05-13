package org.opendatamesh.platform.adapter.marketplace.executor.starter.exception;

import org.springframework.http.HttpStatus;

public class OdmApiException extends RuntimeException {
    private final HttpStatus status;
    private final String errorName;

    public OdmApiException(String message, HttpStatus status, String errorName) {
        super(message);
        this.status = status;
        this.errorName = errorName;
    }

    public OdmApiException(String message, Throwable cause, HttpStatus status, String errorName) {
        super(message, cause);
        this.status = status;
        this.errorName = errorName;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getErrorName() {
        return errorName;
    }
} 