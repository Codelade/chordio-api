package com.codelade.chordioapi.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Long id) {
        super("User with ID " + id + " not found");
    }

    public UserNotFoundException(String email) {
        super("User with email " + email + " not found");
    }

    public UserNotFoundException(String email, String userName, boolean both) {
        super("User with email " + email + " or username " + userName + " not found");
    }

}
