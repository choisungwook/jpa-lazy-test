package com.sungwook.lazytest.common.exceptions;

/***
 * 로직 처리 중 유효성검사 실패
 * 예: 데이터베이스에 이미 데이터가 있는 경우
 */
public class FailValidation extends RuntimeException{
    public FailValidation() {
        super();
    }

    public FailValidation(String message) {
        super(message);
    }

    public FailValidation(String message, Throwable cause) {
        super(message, cause);
    }

    public FailValidation(Throwable cause) {
        super(cause);
    }

    protected FailValidation(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
