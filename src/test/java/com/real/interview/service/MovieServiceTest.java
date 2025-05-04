package com.real.interview.service;

import com.real.interview.dto.MovieDto;
import com.real.interview.repository.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MovieServiceTest {

    @Autowired
    private MovieRepository movieRepository;

    private MovieService movieService;

    @BeforeEach
    public void setup() {
        movieService = new MovieService(movieRepository);
    }

    @Test
    void getMovies() {
    }

    @Test
    void save() {
        MovieDto movieDto = buildMovie("ddlj");
        movieService.save(movieDto);
    }

    private MovieDto buildMovie(String name) {
        return MovieDto.builder()
                .id(""+new Random(100).nextInt())
                .title(name)
                .releaseYear(1998)
                .build();
    }
}