package com.tdd.catalog.dto;

import com.tdd.catalog.utils.ResponseCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse<T> implements Serializable {
    private ResponseCode errCode;
    private String errMessage;
    private T data;

    public static <T> BaseResponse<T> ok(T data) {
        BaseResponse<T> baseResponse = new BaseResponse<>();
        baseResponse.setData(data);
        baseResponse.setErrCode(ResponseCode.GW200);
        baseResponse.setErrMessage("Ok");
        return baseResponse;
    }
}
