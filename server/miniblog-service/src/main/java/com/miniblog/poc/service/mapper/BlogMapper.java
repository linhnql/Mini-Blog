package com.miniblog.poc.service.mapper;

import com.miniblog.poc.api.model.Blog;
import com.miniblog.poc.api.model.BlogRequest;
import com.miniblog.poc.api.model.Blogs;
import com.miniblog.poc.service.entity.BlogEntity;
import com.miniblog.poc.service.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.threeten.bp.Instant;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class BlogMapper {
    private final BlogRepository repository;

    @Autowired
    public BlogMapper(BlogRepository repository) {
        this.repository = repository;
    }

    public BlogEntity setupBlogEntityFromBlogRequest(BlogEntity to, BlogRequest from){
        to.setCategory(from.getCategory().toString());
        to.setTitle(from.getTitle());
        to.setImage(from.getImage());
        to.setIntro(from.getIntro());
        to.setDetail(from.getDetail());
        to.setCreatedAt(OffsetDateTime.now());

        return to;
    }

    public BlogEntity mapBlogEntityFromBlogRequest(BlogRequest from) {
        BlogEntity to = new BlogEntity();

        to.setId(UUID.randomUUID().toString());

        return setupBlogEntityFromBlogRequest(to, from);
    }

    public BlogEntity mapBlogEntityFromBlogRequest(String id, BlogRequest from) {
        BlogEntity to = repository.getOne(id);

        return setupBlogEntityFromBlogRequest(to, from);
    }

    public Blog mapBlogFromBlogEntity(BlogEntity from) {
        Blog to = new Blog();

        to.setId(from.getId());
        to.setCategory(Blog.CategoryEnum.valueOf(from.getCategory()));
        to.setTitle(from.getTitle());
        to.setImage(from.getImage());
        to.setIntro(from.getIntro());
        to.setDetail(from.getDetail());
        to.setCreatedAt(from.getCreatedAt());

        return to;
    }

    public Blogs mapBlogsFromBlogEntities(List<BlogEntity> from) {
        return from.stream()
                .map(this::mapBlogFromBlogEntity)
                .collect(Collectors.toCollection(Blogs::new));
    }
}
