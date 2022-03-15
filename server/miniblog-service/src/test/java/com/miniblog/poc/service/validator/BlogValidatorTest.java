package com.miniblog.poc.service.validator;

import com.miniblog.poc.api.model.BlogRequest;
import com.miniblog.poc.service.exception.BadRequestException;
import com.miniblog.poc.service.exception.EntityNotFoundException;
import com.miniblog.poc.service.mock.BlogData;
import com.miniblog.poc.service.repository.BlogRepository;
import com.miniblog.poc.service.validator.blog.BlogValidator;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BlogValidatorTest {
    @InjectMocks
    BlogValidator validator;

    @Mock
    BlogRepository repository;

    @Rule
    public ExpectedException expected = ExpectedException.none();

    @Test
    public void validateUpdateBlog() {
        when(repository.existsById(BlogData.ID)).thenReturn(true);

        validator.validateUpdateBlog(BlogData.ID, BlogData.mockBlogRequest());
    }

    @Test
    public void validateUpdateBlogShowExceptionWhenWrongId() {
        expected.expect(EntityNotFoundException.class);
        expected.expectMessage("Blog does not exist");

        when(repository.existsById(BlogData.ID)).thenReturn(false);

        validator.validateUpdateBlog(BlogData.ID, BlogData.mockBlogRequest());
    }

    @Test
    public void validateAddBlog() {
        validator.validateAddBlog(BlogData.mockBlogRequest());
    }

    @Test
    public void validateAddBlogShowExceptionWhenTitleFieldIsNull() {
        expected.expect(BadRequestException.class);
        expected.expectMessage("Title is requested");

        BlogRequest blogRequest = BlogData.mockBlogRequest();
        blogRequest.setTitle("");

        validator.validateAddBlog(blogRequest);
    }

    @Test
    public void validateAddBlogShowExceptionWhenDetailFieldIsNull() {
        expected.expect(BadRequestException.class);
        expected.expectMessage("Detail is required");

        BlogRequest blogRequest = BlogData.mockBlogRequest();
        blogRequest.setDetail("");

        validator.validateAddBlog(blogRequest);
    }

}
