package com.miniblog.poc.service.service;

import com.miniblog.poc.api.model.Blog;
import com.miniblog.poc.api.model.BlogRequest;
import com.miniblog.poc.api.model.Blogs;
import com.miniblog.poc.service.entity.BlogEntity;
import com.miniblog.poc.service.repository.BlogRepository;
import com.miniblog.poc.service.mapper.BlogMapper;
import com.miniblog.poc.service.search.BlogSearch;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BlogService {
    private final BlogRepository repository;

    private final BlogMapper mapper;

    private final BlogSearch blogSearch;

    public Blog addBlog(BlogRequest req) {
        BlogEntity blogEntity = mapper.mapBlogEntityFromBlogRequest(req);

        return mapper.mapBlogFromBlogEntity(repository.save(blogEntity));
    }

    public Blog getBlogById(String id){
        BlogEntity blogEntity = repository.getOne(id);

        return mapper.mapBlogFromBlogEntity(blogEntity);
    }

    public  Blogs getAllBlogs(){
        return mapper.mapBlogsFromBlogEntities(repository.findAll());
    }

    public void deleteBlogById(String id){
        repository.deleteById(id);
    }

    public Blog updateBlogById(String id, BlogRequest req) {
        BlogEntity blogEntity = mapper.mapBlogEntityFromBlogRequest(id, req);

        return mapper.mapBlogFromBlogEntity(repository.save(blogEntity));
    }

    public Blogs search(Integer page, Integer pageSize) {
        return mapper.mapBlogsFromBlogEntities(blogSearch.getBlogs(page, pageSize));
    }
}
