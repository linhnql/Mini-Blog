package com.miniblog.poc.service.validator.blog;

import com.miniblog.poc.service.exception.BadRequestException;
import org.springframework.stereotype.Component;

@Component
public class BlogSearchValidator {

    private static final int MIN_LENGTH = 2;
    private static final int MAX_LENGTH = 128;

    private static final String TERM_INPUT_INVALID_MSG =
            "Term input is invalid. Please in put at least 2 normal characters";

    public void validateTermSearch(String term) {

        if (term.length() >= MIN_LENGTH && term.length() <= MAX_LENGTH) {
            return;
        }

        throw new BadRequestException(TERM_INPUT_INVALID_MSG);
    }

}
