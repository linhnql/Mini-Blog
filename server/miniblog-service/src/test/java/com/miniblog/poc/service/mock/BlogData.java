package com.miniblog.poc.service.mock;

import com.miniblog.poc.api.model.Blog;
import com.miniblog.poc.api.model.BlogRequest;
import com.miniblog.poc.api.model.Blogs;
import com.miniblog.poc.service.entity.BlogEntity;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;

public class BlogData {
    public static final String ID = "c73095df-a31d-45a0-8d57-8c43108830c7";
    public static final String TITLE = "ReactJS Become a professional in web app development";
    public static final String IMAGE = "https://images-na.ssl-images-amazon.com/images/I/41ith8XYqwS._SX331_BO1,204,203,200_.jpg";
    public static final String INTRO = "The copy warned the little blind text, that where it came from it would have been rewritten a thiusand times and everything that was left from its origin would be the word";
    public static final String DETAIL = "industrys standard dummy texter since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic types";
//    public static final OffsetDateTime CREATED_AT = OffsetDateTime.parse("2022-07-01 08:57:11");

    public static Blog mockBlog() {
        Blog blog = new Blog();
        
        blog.setId(ID);
        blog.setCategory(Blog.CategoryEnum.ART);
        blog.setTitle(TITLE);
        blog.setImage(IMAGE);
        blog.setIntro(INTRO);
        blog.setDetail(DETAIL);
        blog.setCreatedAt(OffsetDateTime.now());
        
        return blog;
    }

    public static Blogs mockBlogs() {
        Blogs blogs = new Blogs();

        blogs.add(mockBlog());

        return blogs;
    }

    public static BlogRequest mockBlogRequest() {
        BlogRequest blogRequest = new BlogRequest();

        blogRequest.setCategory(BlogRequest.CategoryEnum.ART);
        blogRequest.setTitle(TITLE);
        blogRequest.setImage(IMAGE);
        blogRequest.setIntro(INTRO);
        blogRequest.setDetail(DETAIL);

        return blogRequest;
    }

    public static BlogEntity mockBlogEntity() {
        BlogEntity blog = new BlogEntity();

        blog.setId(ID);
        blog.setCategory("ART");
        blog.setTitle(TITLE);
        blog.setImage(IMAGE);
        blog.setIntro(INTRO);
        blog.setDetail(DETAIL);


        return blog;
    }

    public static List<BlogEntity> mockBlogEntities() {
        return Arrays.asList(mockBlogEntity());
    }
}
