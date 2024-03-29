package com.example.demo.excepciones;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ApiExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({ NotFoundException.class })
    @ResponseBody
    public Error notFoundRequest(HttpServletRequest request, Exception ex) {
        return new Error(ex, request.getRequestURI());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            BadRequestException.class,
            org.springframework.dao.DuplicateKeyException.class,
            org.springframework.web.HttpRequestMethodNotSupportedException.class,
            org.springframework.web.bind.MethodArgumentNotValidException.class,
            org.springframework.web.bind.MissingRequestHeaderException.class,
            org.springframework.web.bind.MissingServletRequestParameterException.class,
            org.springframework.http.converter.HttpMessageNotReadableException.class
    })
    @ResponseBody
    public Error badRequest(HttpServletRequest request, Exception ex) {
        return new Error(ex, request.getRequestURI());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler({ Conflict.class })
    @ResponseBody
    public Error conflict(HttpServletRequest request, Exception ex) {
        return new Error(ex, request.getRequestURI());
    }

    // @ResponseStatus(HttpStatus.FORBIDDEN)
    // @ExceptionHandler({ Forbidden.class })
    // @ResponseBody
    // public Error forbidden(HttpServletRequest request, Exception ex) {
    // return new Error(ex, request.getRequestURI());
    // }

    // @ResponseStatus(HttpStatus.UNAUTHORIZED)
    // @ExceptionHandler({
    // Unauthorized.class,
    // org.springframework.boot.acces
    // })
    // @ResponseBody
    // public void unauthorized(){}

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({
            Exception.class
    })
    @ResponseBody
    public Error internalError(HttpServletRequest request, Exception ex) {
        return new Error(ex, request.getRequestURI());
    }

}
