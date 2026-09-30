package com.martin.poestashsearcher.poeapi;

public class ClientHttpException extends Exception {

    public ClientHttpException(String message) {
        super(message);
    }

    public ClientHttpException(Throwable cause) {
        super(cause);
    }

    public ClientHttpException(String message, Throwable cause) {
        super(message, cause);
    }

}
