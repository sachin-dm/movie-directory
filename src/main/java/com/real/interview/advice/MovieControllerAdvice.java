package com.real.interview.advice;

import com.real.interview.exception.InvalidMovieException;
import com.real.interview.exception.MovieDoesNotExistException;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

@ControllerAdvice
@CommonsLog
public class MovieControllerAdvice {

    @ExceptionHandler(IOException.class)
    public ResponseEntity handleIOE(IOException ioe) {
        log.error("IOException occurred.", ioe);
       return ResponseEntity
               .internalServerError()
               .body("Something went wrong. Please try again later..");
    }

    @ExceptionHandler(MovieDoesNotExistException.class)
    public ResponseEntity<String> handleMovieAlreadyExistException(MovieDoesNotExistException maee) {
        log.error(String.format("Cannot find the movie %s.", maee.getMovieName()), maee);
        return ResponseEntity
                .badRequest()
                .body(String.format("Movie %s does not exist.", maee.getMovieName()));
    }

    @ExceptionHandler(InvalidMovieException.class)
    public ResponseEntity<String> handleInvalidMovieException(InvalidMovieException ime) {
        log.error(String.format("Cannot find the movie with '%s' : '%s'.",ime.getKey(), ime.getValue()), ime);
        return ResponseEntity
                .badRequest()
                .body(String.format("Movie with '%s': '%s' does not exist.",ime.getKey(), ime.getValue()));
    }
}
