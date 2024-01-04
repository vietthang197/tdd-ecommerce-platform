package com.tdd.catalog.exeception;

import com.tdd.catalog.dto.BaseResponse;
import com.tdd.catalog.utils.ResponseCode;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ InvalidDataRequestException.class })
    public BaseResponse<Void> handleInvalidDataRequest(Exception ex, WebRequest webRequest) {
        BaseResponse<Void> baseResponse = new BaseResponse<>();
        baseResponse.setErrCode(ResponseCode.GW400);
        baseResponse.setErrMessage(ex.getMessage());
        return baseResponse;
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        BaseResponse<Void> baseResponse = new BaseResponse<>();
        baseResponse.setErrCode(ResponseCode.GW400);

        if (!ex.getBindingResult().getFieldErrors().isEmpty()) {
            baseResponse.setErrMessage(ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage());
        } else if (!ex.getBindingResult().getGlobalErrors().isEmpty()) {
            baseResponse.setErrMessage(ex.getBindingResult().getGlobalErrors().get(0).getDefaultMessage());
        } else {
            baseResponse.setErrMessage(ex.getMessage());
        }
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
}
