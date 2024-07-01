package com.woliveira.forum_hub_api.exceptions;

public class ValidationException extends RuntimeException {

    public ValidationException (String messenger) {
        super(messenger);
    }

}
