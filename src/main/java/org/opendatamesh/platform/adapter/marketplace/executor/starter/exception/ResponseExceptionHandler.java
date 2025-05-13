package org.opendatamesh.platform.adapter.marketplace.executor.starter.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@ControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler({OdmApiException.class})
    protected ResponseEntity<Object> handleOdmException(OdmApiException e, WebRequest request) {
        if (e.getStatus() == HttpStatus.INTERNAL_SERVER_ERROR) {
            logger.error(e.getErrorName() + ":" + e.getMessage(), e);
        } else {
            logger.info(e.getErrorName() + ":" + e.getMessage());
        }
        String url = getUrl(request);
        ErrorResponse error = new ErrorResponse();
        error.setStatus(e.getStatus().value());
        error.setError(e.getErrorName());
        error.setMessage(e.getMessage());
        error.setPath(url);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return handleExceptionInternal(e, error, headers, e.getStatus(), request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (body == null && !HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            logger.debug("Creating body for unhandled exception", ex);
            headers.setContentType(MediaType.APPLICATION_JSON);
            String url = getUrl(request);
            String message = ex.getMessage();
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setStatus(status.value());
            errorResponse.setError(ex.getClass().getName());
            errorResponse.setMessage(message);
            errorResponse.setPath(url);
            body = errorResponse;
        }
        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<ObjectError> errors = ex.getAllErrors();
        String message = String.format("Errors: %s", errors.stream().map(Objects::toString).collect(Collectors.joining("; ")));
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(status.value());
        errorResponse.setError("BindException");
        errorResponse.setMessage(message);
        errorResponse.setPath(getUrl(request));
        return handleExceptionInternal(ex, errorResponse, headers, status, request);
    }

    @ExceptionHandler({RuntimeException.class})
    protected ResponseEntity<Object> handleRuntimeException(RuntimeException e, WebRequest request) {
        logger.error("Unknown server error: ", e);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String url = getUrl(request);
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(status.value());
        errorResponse.setError("ServerError");
        errorResponse.setMessage("Unknown Internal Server Error");
        errorResponse.setPath(url);
        return handleExceptionInternal(e, errorResponse, headers, status, request);
    }

    private String getUrl(WebRequest request) {
        String url = request.toString();
        if (request instanceof ServletWebRequest) {
            ServletWebRequest r = (ServletWebRequest) request;
            url = r.getRequest().getRequestURI();
        }
        return url;
    }
} 