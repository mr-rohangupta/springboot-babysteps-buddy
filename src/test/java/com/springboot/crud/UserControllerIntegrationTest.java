package com.springboot.crud;

import com.springboot.crud.dto.UserDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

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
        HttpStatus statusCode = this.restTemplate.getForEntity("http://localhost:" + port + "/users", UserDto[].class).getStatusCode();
        Assertions.assertThat(statusCode.toString()).isEqualTo("200 OK");
    }
}
