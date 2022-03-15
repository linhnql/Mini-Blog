package com.miniblog.poc.service.it.steps;

import com.miniblog.poc.client.api.BlogApi;
import com.miniblog.poc.client.model.BlogRequest;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.web.client.RestClientException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class BlogSteps extends CommonStepConfiguration {

//    protected BlogApi blogApi;
//
//    private RestClientException restClientException;
//
//    private BlogRequest blogRequest;
//
//    private final String API_KEY = "691c5597-e7d2-4c06-af49-f9369b367783";
//
//    @Before
//    public void setup() {
//        super.setup();
//        blogApi = new BlogApi(baseApiClient);
//    }
//
//    @Given("^user request to create blog with \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
//    public void userRequestToCreateBlogWith(String type, String title, String image, String intro, String detail) {
//        blogRequest = createBlogRequest(type, title, image, intro, detail);
//    }
//
//    @When("^the user do action create blog$")
//    public void theUserDoActionCreateBlog() {
//        try {
//            blogApi.addBlog(API_KEY, blogRequest);
//        } catch (RestClientException e) {
//            restClientException = e;
//        }
//    }
//
//    @Then("^user action is successful with \"([^\"]*)\"$")
//    public void userActionIsSuccessfulWith(String code) {
//        assertThat(blogApi.getApiClient().getStatusCode().value(), is(Integer.valueOf(code)));
//    }
//
//    @Then("^user action is failed with \"([^\"]*)\"$")
//    public void userActionIsFailedWith(String code) {
//        assertThat(getExceptionStatusCode(restClientException), is(code));
//    }
//
//    private BlogRequest createBlogRequest(String type, String title, String image, String intro, String detail) {
//        BlogRequest blogRequest = new BlogRequest();
//
//        blogRequest.setCategory(BlogRequest.CategoryEnum.fromValue(type));
//        blogRequest.setTitle(title);
//        blogRequest.setImage(image);
//        blogRequest.setIntro(intro);
//        blogRequest.setDetail(detail);
//
//        return blogRequest;
//    }
}
