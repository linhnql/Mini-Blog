package com.miniblog.poc.service.it.steps;

import com.miniblog.poc.client.api.ContactApi;
import com.miniblog.poc.client.model.ContactRequest;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.web.client.RestClientException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ContactSteps extends CommonStepConfiguration {

    protected ContactApi contactApi;

    private RestClientException restClientException;

    private ContactRequest contactRequest;

    private final String API_KEY = "691c5597-e7d2-4c06-af49-f9369b367783";

    @Before
    public void setup() {
        super.setup();
        contactApi = new ContactApi(baseApiClient);
    }

    @Given("^user request to create Contact with \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
    public void userRequestToCreateContactWith(String name, String email, String messenger) {
        contactRequest = createContactRequest(name, email, messenger);
    }

    @When("^the user do action create Contact$")
    public void theUserDoActionCreateContact() {
        try {
            contactApi.addContact(API_KEY, contactRequest);
        } catch (RestClientException e) {
            restClientException = e;
        }
    }

    @Then("^user action is successful with \"([^\"]*)\"$")
    public void userActionIsSuccessfulWith(String code) {
        assertThat(contactApi.getApiClient().getStatusCode().value(), is(Integer.valueOf(code)));
    }

    @Then("^user action is failed with \"([^\"]*)\"$")
    public void userActionIsFailedWith(String code) {
        assertThat(getExceptionStatusCode(restClientException), is(code));
    }

    private ContactRequest createContactRequest(String name, String email, String messenger) {
        ContactRequest contactRequest = new ContactRequest();

        contactRequest.setName(name);
        contactRequest.setEmail(email);
        contactRequest.setMessenger(messenger);

        return contactRequest;
    }
}
