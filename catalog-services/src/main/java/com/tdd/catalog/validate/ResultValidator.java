package com.tdd.catalog.validate;

import com.tdd.catalog.constant.Constant;
import com.tdd.catalog.utils.ResponseCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultValidator {
    private ResponseCode errCode;
    private String errMessage;
    private boolean isOk;

    public static ResultValidator success() {
        return new ResultValidator(ResponseCode.GW200, "Ok", true);
    }
}
