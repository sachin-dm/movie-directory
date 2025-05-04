package com.real.interview.dto;

import com.real.interview.model.Movie;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieDto {
    private String id;
    private String title;
    private Integer releaseYear;

    public static MovieDto toDto(Movie movie) {
        return MovieDto.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .releaseYear(movie.getReleaseYear())
                .build();
    }


    public static Movie toEntity(MovieDto dto) {
        return Movie.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .releaseYear(dto.getReleaseYear())
                .created(System.currentTimeMillis())
                .updated(System.currentTimeMillis())
                .build();
    }

}
