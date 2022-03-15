package com.miniblog.poc.service.api;

import com.miniblog.poc.api.model.Blog;
import com.miniblog.poc.api.model.BlogRequest;
import com.miniblog.poc.api.model.Blogs;
import com.miniblog.poc.service.api.blog.BlogController;
import com.miniblog.poc.service.mock.BlogData;
import com.miniblog.poc.service.service.BlogService;
import com.miniblog.poc.service.validator.blog.BlogSearchValidator;
import com.miniblog.poc.service.validator.blog.BlogValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class BlogControllerTest {
    private final String API_KEY = "MOCK_API_KEY";

    @InjectMocks
    BlogController controller;

    @Mock
    BlogService service;

    @Mock
    BlogValidator validator;

    @Mock
    BlogSearchValidator searchValidator;

    @Test
    public void testEndpointAddBlog() {
        when(service.addBlog(any(BlogRequest.class)))
                .thenReturn(BlogData.mockBlog());

        ResponseEntity<Blog> responseEntity =
                controller.addBlog(API_KEY, BlogData.mockBlogRequest());

        assertStatus200(responseEntity.getStatusCode());
        assertBlog(Objects.requireNonNull(responseEntity.getBody()));
    }

    @Test
    public void testEndpointGetBlogById(){
        when(service.getBlogById(anyString()))
                .thenReturn(BlogData.mockBlog());

        ResponseEntity<Blog> responseEntity =
                controller.getBlogById(API_KEY, BlogData.ID);

        assertStatus200(responseEntity.getStatusCode());
        assertBlog(Objects.requireNonNull(responseEntity.getBody()));
    }

//    @Test
//    public void testEndpointGetAllBlogs(){
//        when(service.getAllBlogs())
//                .thenReturn(BlogData.mockBlogs());
//
//        ResponseEntity<Blogs> responseEntity =
//                controller.getAllBlogs(API_KEY);
//
//        assertStatus200(responseEntity.getStatusCode());
//        assertBlogs(Objects.requireNonNull(responseEntity.getBody()));
//    }

    @Test
    public void testEndpointUpdateBlogById() {
        when(service.updateBlogById(anyString(), any(BlogRequest.class)))
                .thenReturn(BlogData.mockBlog());

        ResponseEntity<Blog> responseEntity =
                controller.updateBlogById(API_KEY, BlogData.ID, BlogData.mockBlogRequest());

        assertStatus200(responseEntity.getStatusCode());
        assertBlog(Objects.requireNonNull(responseEntity.getBody()));
    }

    @Test
    public void testDeleteBlogById(){
        ResponseEntity<Blog> responseEntity =
                controller.deleteBlogById(API_KEY, BlogData.ID);

        assertStatus200(responseEntity.getStatusCode());
    }

    private void assertBlog(Blog actual) {
        assertThat(actual.getId(), is(BlogData.ID));
        assertThat(actual.getCategory(), is(Blog.CategoryEnum.ART));
        assertThat(actual.getImage(), is(BlogData.IMAGE));
        assertThat(actual.getIntro(), is(BlogData.INTRO));
        assertThat(actual.getDetail(), is(BlogData.DETAIL));
    }

    private void assertBlogs(Blogs actual) {
        assertBlog(actual.get(0));
    }

    private void assertStatus200(HttpStatus actual) {
        assertThat(actual, is(HttpStatus.OK));
    }
}
