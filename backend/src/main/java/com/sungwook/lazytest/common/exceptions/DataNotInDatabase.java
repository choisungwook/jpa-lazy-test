package com.sungwook.lazytest.common.exceptions;

/***
 * Database에 찾는 데이터가 없는 상황을 다루는 에러
 */
public class DataNotInDatabase extends RuntimeException{
    public DataNotInDatabase() {
        super();
    }

    public DataNotInDatabase(String message) {
        super(message);
    }

    public DataNotInDatabase(String message, Throwable cause) {
        super(message, cause);
    }

    public DataNotInDatabase(Throwable cause) {
        super(cause);
    }

    protected DataNotInDatabase(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
