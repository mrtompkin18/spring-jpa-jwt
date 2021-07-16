package com.healme.app.common.error;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

@Data
@EqualsAndHashCode(callSuper = true)
public class ApiException extends Exception {
    @Serial
    private static final long serialVersionUID = -8839011734147859169L;
    private String errorCode;
    private String errorDesc;

    public ApiException() {
    }

    public ApiException(String errorCode, String errorDesc) {
        super(errorDesc);
        this.errorCode = errorCode;
        this.errorDesc = errorDesc;
    }

    public ApiException(String errorCode, String errorDesc, Throwable throwable) {
        super(errorDesc, throwable);
        this.errorCode = errorCode;
        this.errorCode = errorDesc;
    }
}
