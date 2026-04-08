package com.codelade.chordioapi.exception;

public class InvalidRequestException extends RuntimeException {

     public InvalidRequestException(String field, String value) {
        super("Invalid value for " + field + ": " + value);
    }

}
