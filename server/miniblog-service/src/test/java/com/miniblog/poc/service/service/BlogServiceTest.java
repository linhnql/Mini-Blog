package com.miniblog.poc.service.service;

import com.miniblog.poc.service.mapper.BlogMapper;
import com.miniblog.poc.service.mock.BlogData;
import com.miniblog.poc.service.repository.BlogRepository;
import com.miniblog.poc.service.search.BlogSearch;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BlogServiceTest {
    @InjectMocks
    BlogService service;

    @Mock
    BlogRepository repository;

    @Mock
    BlogMapper mapper;

    @Mock
    BlogSearch blogSearch;

    @Test
    public void addBlog() {
        service.addBlog(BlogData.mockBlogRequest());
    }

    @Test
    public void getBlogById(){
        service.getBlogById(BlogData.ID);
    }

    @Test
    public void getAllBlogs(){
        service.getAllBlogs();
    }

    @Test
    public void deleteBlogById(){
        service.deleteBlogById(BlogData.ID);
    }

    @Test
    public void updateBlogById() {
        service.updateBlogById(BlogData.ID, BlogData.mockBlogRequest());
    }

    @Test
    public void searchBlog() {
//        service.search("MOCK_TERM");
    }
}
