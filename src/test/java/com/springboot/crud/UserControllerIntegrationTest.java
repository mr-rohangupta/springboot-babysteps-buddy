package com.springboot.crud;

import com.springboot.crud.model.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author rohangupta
 */
@SpringBootTest(classes = SpringBootCrudApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetAllUsers() {
        HttpStatus statusCode = this.restTemplate
                .getForEntity("http://localhost:" + port + "/users", User[].class)
                .getStatusCode();
        Assertions.assertThat(statusCode.toString()).isEqualTo("200 OK");
    }

    @Test
    public void testCreateUser() throws URISyntaxException {
        final String baseUrl = "http://localhost:" + port + "/users";
        URI uri = new URI(baseUrl);
        User user = new User(1l, "Solution Architect", "Rohan", "Gupta", "rohangupta@gmail.com");
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<User> request = new HttpEntity<>(user, httpHeaders);
        ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);
        Assertions.assertThat(result.getStatusCode().toString()).isEqualTo("201 CREATED");
    }
}
