package com.miniblog.poc.service.api.blog;

import com.miniblog.poc.api.BlogApi;
import com.miniblog.poc.api.model.Blog;
import com.miniblog.poc.api.model.BlogRequest;
import com.miniblog.poc.api.model.Blogs;
import com.miniblog.poc.service.service.BlogService;
import com.miniblog.poc.service.validator.blog.BlogSearchValidator;
import com.miniblog.poc.service.validator.blog.BlogValidator;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/miniblog/backend/v1")
@CrossOrigin
@AllArgsConstructor
public class BlogController implements BlogApi {

    private final BlogService service;

    private final BlogValidator validator;

    private final BlogSearchValidator searchValidator;

    @Override
    public ResponseEntity<Blog> addBlog(@RequestHeader(value="apikey") String apikey,
                                        @RequestBody BlogRequest request) {
        validator.validateAddBlog(request);

        Blog blog = service.addBlog(request);

        return new ResponseEntity<>(blog, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Blog> getBlogById(@RequestHeader(value="apikey") String apikey,
                                            @PathVariable("id") String id) {
        validator.validateBlogExist(id);
        Blog blog = service.getBlogById(id);

        return new ResponseEntity<>(blog, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Blog> updateBlogById(@RequestHeader(value="apikey") String apikey,
                                               @PathVariable("id") String id,
                                               @RequestBody BlogRequest request) {
        validator.validateUpdateBlog(id, request);
        Blog blog = service.updateBlogById(id, request);

        return new ResponseEntity<>(blog, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Blogs> getAllBlogs(@RequestHeader(value="apikey") String apikey,
                                             Integer page, Integer pageSize) {
        Blogs blogs = service.search(page, pageSize);

        return new ResponseEntity<>(blogs, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Blog> deleteBlogById(@RequestHeader(value="apikey") String apikey,
                                               @PathVariable("id") String id) {
        validator.validateBlogExist(id);
        service.deleteBlogById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
