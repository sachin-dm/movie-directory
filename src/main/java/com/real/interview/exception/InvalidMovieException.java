package com.real.interview.exception;

import lombok.Getter;

@Getter
public class InvalidMovieException extends RuntimeException {
    private final String key;
    private final String value;

    public InvalidMovieException(String key, String value, String message, Throwable cause) {
        super(message, cause);
        this.key = key;
        this.value = value;
    }

    public InvalidMovieException(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
