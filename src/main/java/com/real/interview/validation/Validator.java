package com.real.interview.validation;

import com.real.interview.dto.MovieDto;

public interface Validator {
    boolean validate(MovieDto dto);
}
