package com.miniblog.poc.service.components;

import com.miniblog.poc.client.api.BlogApi;
import com.miniblog.poc.client.handler.ApiClient;
import com.miniblog.poc.client.model.Blog;
import com.miniblog.poc.client.model.BlogRequest;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import java.time.OffsetDateTime;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@TestPropertySource("/application-test.properties")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BlogComponentTest {
    private final String API_KEY = "691c5597-e7d2-4c06-af49-f9369b367783";

    private static final String HTTP_LOCALHOST = "http://localhost:";
    private static final String BASE_PATH = "/miniblog/backend/v1";

    public static final String ID = "c73095df-a31d-45a0-8d57-8c43108830c7";
    public static final String TITLE = "ReactJS Become a professional in web app development";
    public static final String IMAGE = "https://images-na.ssl-images-amazon.com/images/I/41ith8XYqwS._SX331_BO1,204,203,200_.jpg";
    public static final String INTRO = "The copy warned the little blind text, that where it came from it would have been rewritten a thiusand times and everything that was left from its origin would be the word";
    public static final String DETAIL = "industrys standard dummy texter since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic types";
//    public static final OffsetDateTime CREATED_AT = OffsetDateTime.parse("2022-07-01 08:57:11");

    private BlogApi api;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @LocalServerPort
    private int port;

    @Before
    public void setup() {
        String url = HTTP_LOCALHOST + port + BASE_PATH;
        ApiClient client = new ApiClient();
        client.setBasePath(url);
        api = new BlogApi(client);
    }

    @Test
    public void ensureBlogCreated() {
        thrown.expect(HttpClientErrorException.class);
        Blog blog = api.addBlog(API_KEY, mockBlogRequest());

        assertBlog(blog);
    }

    @Test
    public void ensureBlogCreateThrowExceptionWhenRequiredFieldNotPass() {
        thrown.expect(HttpClientErrorException.class);

        BlogRequest blogRequest = mockBlogRequest();
        blogRequest.setTitle("");

        api.addBlog(API_KEY, blogRequest);
    }

    @Test
    public void ensureBlogUpdated() {
        thrown.expect(HttpClientErrorException.class);
        Blog blog = api.updateBlogById(API_KEY, ID, mockBlogRequest());

        assertBlog(blog);
    }

    @Test
    public void ensureBlogUpdateThrowExceptionWhenWrongPassed() {
        thrown.expect(HttpClientErrorException.class);

        BlogRequest blogRequest = mockBlogRequest();
        blogRequest.setTitle("");

        api.updateBlogById(API_KEY, ID + "FAKE", blogRequest);
    }

    private void assertBlog(Blog actual) {
        assertThat(actual.getId() != null, is(true));
        assertThat(actual.getId(), is(ID));
        assertThat(actual.getCategory(), is(Blog.CategoryEnum.ART));
        assertThat(actual.getImage(), is(IMAGE));
        assertThat(actual.getIntro(), is(INTRO));
        assertThat(actual.getDetail(), is(DETAIL));
    }

    private BlogRequest mockBlogRequest() {
        BlogRequest blogRequest = new BlogRequest();

        blogRequest.setCategory(BlogRequest.CategoryEnum.ART);
        blogRequest.setTitle(TITLE);
        blogRequest.setImage(IMAGE);
        blogRequest.setIntro(INTRO);
        blogRequest.setDetail(DETAIL);

        return blogRequest;
    }
}
