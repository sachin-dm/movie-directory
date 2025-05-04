package com.real.interview.exception;

import lombok.Getter;

@Getter
public class MovieDoesNotExistException extends RuntimeException {
    private final String movieName;

    public MovieDoesNotExistException(String name, String message, Throwable cause) {
        super(message, cause);
        this.movieName = name;
    }

    public MovieDoesNotExistException(String movieName) {
        this.movieName = movieName;
    }
}
