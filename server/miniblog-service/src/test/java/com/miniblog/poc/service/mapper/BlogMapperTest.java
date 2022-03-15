package com.miniblog.poc.service.mapper;

import com.miniblog.poc.api.model.Blog;
import com.miniblog.poc.api.model.BlogRequest;
import com.miniblog.poc.api.model.Blogs;
import com.miniblog.poc.service.entity.BlogEntity;
import com.miniblog.poc.service.mock.BlogData;
import com.miniblog.poc.service.repository.BlogRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BlogMapperTest {

    @InjectMocks
    BlogMapper mapper;

    @Mock
    BlogRepository repository;

    @Test
    public void ensureMapBlogEntityFromBlogReqForAdd() {

        BlogRequest blogRequest = BlogData.mockBlogRequest();

        BlogEntity entity = mapper.mapBlogEntityFromBlogRequest(blogRequest);

        assertNotNull(entity.getId());
        assertEquals(entity.getCategory(), Blog.CategoryEnum.valueOf(blogRequest.getCategory().toString()));
        assertThat(entity.getTitle(), is(blogRequest.getTitle()));
        assertThat(entity.getImage(), is(blogRequest.getImage()));
        assertThat(entity.getIntro(), is(blogRequest.getIntro()));
        assertThat(entity.getDetail(), is(blogRequest.getDetail()));
    }

    @Test
    public void ensureMapBlogEntityFromBlogReqForUpdate() {
        BlogEntity blogEntity = BlogData.mockBlogEntity();

        when(repository.getOne(BlogData.ID)).thenReturn(blogEntity);

        BlogRequest blogRequest = BlogData.mockBlogRequest();

        BlogEntity entity = mapper.mapBlogEntityFromBlogRequest(BlogData.ID, blogRequest);

        assertThat(entity.getTitle(), is(blogRequest.getTitle()));
        assertEquals(entity.getCategory(), Blog.CategoryEnum.valueOf(blogRequest.getCategory().toString()));
        assertThat(entity.getImage(), is(blogRequest.getImage()));
        assertThat(entity.getId() , is(blogEntity.getId()));
        assertThat(entity.getIntro(), is(blogEntity.getIntro()));
        assertThat(entity.getDetail(), is(blogEntity.getDetail()));

    }

    @Test
    public void ensureMapBlogFromBlogEntity() {
        BlogEntity blogEntity = BlogData.mockBlogEntity();

        Blog blog = mapper.mapBlogFromBlogEntity(blogEntity);

        assertBlog(blog, blogEntity);
    }

    @Test
    public void ensureMapBlogsFromBlogEntities() {
        List<BlogEntity> blogEntities = BlogData.mockBlogEntities();

        Blogs blogs = mapper.mapBlogsFromBlogEntities(blogEntities);

        assertBlogs(blogs, blogEntities);
    }

    private void assertBlog(Blog actual, BlogEntity input) {
        assertThat(actual.getId(), is(input.getId()));
        assertThat(actual.getTitle(), is(input.getTitle()));
        assertThat(actual.getCategory(), is(input.getCategory()));
        assertThat(actual.getImage(), is(input.getImage()));
        assertThat(actual.getCreatedAt(), is(input.getCreatedAt()));
        assertThat(actual.getIntro(), is(input.getIntro()));
        assertThat(actual.getDetail(), is(input.getDetail()));
    }

    private void assertBlogs(Blogs actual, List<BlogEntity> input) {
        assertBlog(actual.get(0), input.get(0));
    }

}
