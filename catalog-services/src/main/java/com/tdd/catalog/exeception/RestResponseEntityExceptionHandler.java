package com.tdd.catalog.exeception;

import com.tdd.catalog.dto.BaseResponse;
import com.tdd.catalog.utils.ResponseCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ InvalidDataRequestException.class })
    public BaseResponse<Void> handleInvalidDataRequest(Exception ex, WebRequest webRequest) {
        BaseResponse<Void> baseResponse = new BaseResponse<>();
        baseResponse.setErrCode(ResponseCode.GW400);
        baseResponse.setErrMessage(ex.getMessage());
        return baseResponse;
    }
}
