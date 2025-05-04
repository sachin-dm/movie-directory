package com.real.interview.repository;

import com.real.interview.model.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends CrudRepository<Movie, String> {

    List<Movie> findByReleaseYear(Integer year);
}
