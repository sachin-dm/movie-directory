package com.real.interview.service;

import com.real.interview.dto.MovieDto;
import com.real.interview.exception.InvalidMovieException;
import com.real.interview.exception.MovieDoesNotExistException;
import com.real.interview.model.Movie;
import com.real.interview.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@CommonsLog
public class MovieService {

    private final MovieRepository movieRepository;

    public List<MovieDto> getMovies() throws IOException {
        log.info("Fetching details for all movies..");
        Iterator<Movie> movieIterator = movieRepository.findAll().iterator();
        List<MovieDto> movieDtos = new ArrayList<>();
        while (movieIterator.hasNext()) {
            movieDtos.add(MovieDto.toDto(movieIterator.next()));
        }
        return movieDtos;
    }

    /**
     * Save Movie
     * @param movieDto
     */
    public void save(MovieDto movieDto) {
        log.info(String.format("Saving movie with name %s in DB.", movieDto.getTitle()));
        movieRepository.save(MovieDto.toEntity(movieDto));
    }

    public MovieDto patch(String id, MovieDto dto) {
        Optional<Movie> movieOptional = movieRepository.findById(id);
        if (movieOptional.isPresent()) {
            Movie movie = movieOptional.get();
            if (!ObjectUtils.isEmpty(movie.getTitle()))
                movie.setTitle(dto.getTitle());
            if(!ObjectUtils.isEmpty(dto.getReleaseYear()))
                movie.setReleaseYear(dto.getReleaseYear());
            return MovieDto.toDto(movieRepository.save(MovieDto.toEntity(dto)));
        }
        else {
            throw new InvalidMovieException("ID", id);
        }
    }

    public List<MovieDto> getMoviesByYear(int year) {
        log.info("Getting all movies from year: "+ year);
        List<Movie> movies = movieRepository.findByReleaseYear(year);
        return movies
                .stream()
                .map(MovieDto::toDto)
                .toList();

    }


    private List<MovieDto> readAllMovies() throws IOException {
        List<String> data = Files.readAllLines(Path.of("/Users/sachin/Workspaces/java-interview-setup/src/main/resources/movies.csv"));
        List<MovieDto> movieDtos = new ArrayList<>();
        for(String movie: data) {
            String[] movieData = movie.split(",");
            movieDtos.add(MovieDto.builder()
                    .title(movieData[1])
                    .releaseYear(Integer.valueOf(movieData[2]))
                    .build());
        }
        return movieDtos;
    }
}
