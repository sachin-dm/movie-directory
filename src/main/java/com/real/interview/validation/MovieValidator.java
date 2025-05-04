package com.real.interview.validation;

import com.real.interview.dto.MovieDto;
import org.springframework.stereotype.Component;

@Component
public class MovieValidator implements Validator {

    @Override
    public boolean validate(MovieDto dto) {
        return true;
    }
}
