package com.real.interview.rest;

import com.real.interview.dto.MovieDto;
import com.real.interview.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/real/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public ResponseEntity<List<MovieDto>> list() throws IOException {
        return ResponseEntity.ok(movieService.getMovies());
    }


    @GetMapping("/year/{year}")
    public ResponseEntity<List<MovieDto>> getByYear(@PathVariable("year") Integer year) {
        return ResponseEntity.ok(movieService.getMoviesByYear(year));
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody  MovieDto movie) {
        movieService.save(movie);
        return ResponseEntity
                .created(URI.create(movie.getTitle().replaceAll(" ","_")))
                .build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MovieDto> patch(@PathVariable("id") String id, @RequestBody MovieDto dto) {
        //validation
        // JsonPatch would be very useful.

        return ResponseEntity.ok(movieService.patch(id, dto));
    }
}
