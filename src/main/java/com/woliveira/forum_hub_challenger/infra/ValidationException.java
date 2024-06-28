package com.woliveira.forum_hub_challenger.infra;

public class ValidationException extends RuntimeException {

    public ValidationException (String messenger) {
        super(messenger);
    }

}
