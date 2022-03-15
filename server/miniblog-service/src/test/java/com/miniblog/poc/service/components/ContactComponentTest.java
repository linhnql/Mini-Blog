package com.miniblog.poc.service.components;

import com.miniblog.poc.client.api.ContactApi;
import com.miniblog.poc.client.handler.ApiClient;
import com.miniblog.poc.client.model.Contact;
import com.miniblog.poc.client.model.ContactRequest;
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
public class ContactComponentTest {
    private final String API_KEY = "691c5597-e7d2-4c06-af49-f9369b367783";

    private static final String HTTP_LOCALHOST = "http://localhost:";
    private static final String BASE_PATH = "/miniblog/backend/v1";

    public static final String ID = "c73095df-a31d-45a0-8d57-8c43108830c7";
    public static final String NAME = "Nguyen Van A";
    public static final String EMAIL = "a.nguyenvan@gmail.com";
    public static final String MESSENGER = "Happy new year 2022";
//    public static final OffsetDateTime SENT_AT = OffsetDateTime.parse("2022-07-01 08:57:11");

    private ContactApi api;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @LocalServerPort
    private int port;

    @Before
    public void setup() {
        String url = HTTP_LOCALHOST + port + BASE_PATH;
        ApiClient client = new ApiClient();
        client.setBasePath(url);
        api = new ContactApi(client);
    }

    @Test
    public void ensureContactCreated() {
        thrown.expect(HttpClientErrorException.class);
        Contact contact = api.addContact(API_KEY, mockContactRequest());

        assertContact(contact);
    }

    @Test
    public void ensureContactCreateThrowExceptionWhenRequiredFieldNotPass() {
        thrown.expect(HttpClientErrorException.class);

        ContactRequest contactRequest = mockContactRequest();
        contactRequest.setName("");
        contactRequest.setEmail("");
        contactRequest.setMessenger("");

        api.addContact(API_KEY, contactRequest);
    }

    private void assertContact(Contact actual) {
        assertThat(actual.getId(), is(ID));
        assertThat(actual.getName(), is(NAME));
        assertThat(actual.getEmail(), is(EMAIL));
        assertThat(actual.getMessenger(), is(MESSENGER));
        assertThat(actual.getSentAt(), is(OffsetDateTime.now()));
    }

    private ContactRequest mockContactRequest() {
        ContactRequest contactRequest = new ContactRequest();

        contactRequest.setName(NAME);
        contactRequest.setEmail(EMAIL);
        contactRequest.setMessenger(MESSENGER);

        return contactRequest;
    }
}
