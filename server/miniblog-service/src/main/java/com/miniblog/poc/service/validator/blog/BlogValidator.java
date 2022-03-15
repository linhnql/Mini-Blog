package com.miniblog.poc.service.validator.blog;

import com.miniblog.poc.api.model.BlogRequest;
import com.miniblog.poc.service.exception.BadRequestException;
import com.miniblog.poc.service.exception.EntityNotFoundException;
import com.miniblog.poc.service.repository.BlogRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BlogValidator {
    private static final String BLOG_DOES_NOT_EXIST = "Blog does not exist";

    private static final String TITLE_REQUEST = "Title is requested";

    private static final String DETAIL_REQUEST = "Detail is required";

    private final BlogRepository repository;

    public void validateBlogExist(String id) {
        if (repository.existsById(id)) {return;}

        throw new EntityNotFoundException(BLOG_DOES_NOT_EXIST);
    }

    public void validateAddBlog(BlogRequest request) {
        checkRequiredField(request.getTitle(), TITLE_REQUEST);
        checkRequiredField(request.getDetail(), DETAIL_REQUEST);
    }

    public void validateUpdateBlog(String id, BlogRequest request) {
        validateBlogExist(id);
        checkRequiredField(request.getTitle(), TITLE_REQUEST);
        checkRequiredField(request.getDetail(), DETAIL_REQUEST);
    }

    private void checkRequiredField(String value, String errorMsg) {
        if (null == value || value.trim().isEmpty()) {
            throw new BadRequestException(errorMsg);
        }
    }
}
