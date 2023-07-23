package com.zerobase.trade.exception;

import lombok.Getter;
import com.zerobase.trade.exception.*;

@Getter
public class CustomException extends RuntimeException{
    private final ErrorCode errorCode;

    public CustomException(ErrorCode errorCode) {
        super(errorCode.getDetail());
        this.errorCode = errorCode;
    }
}
