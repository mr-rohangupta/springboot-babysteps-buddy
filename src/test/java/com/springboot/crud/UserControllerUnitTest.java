package com.springboot.crud;

import com.springboot.crud.controller.UserController;
import com.springboot.crud.model.User;
import com.springboot.crud.repository.UserRepository;
import com.springboot.crud.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rohangupta
 */
@WebMvcTest(UserController.class)
public class UserControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private UserService userService;

    @Test
    public void testGetAllUsers() throws Exception {
        List<User> getAllUsers = new ArrayList<>();
        User user1 = User.builder().id(1L).firstName("Rohan").lastName("Gupta").email("rohan@gmail.com").jobTitle("Java Developer")
                .build();
        User user2 = User.builder().id(2L).firstName("Disha").lastName("Gupta").email("disha@gmail.com").jobTitle("QA")
                .build();
        User user3 = User.builder().id(2L).firstName("Amaira").lastName("Gupta").email("amaira@gmail.com").jobTitle("Baby")
                .build();
        getAllUsers.add(user1);
        getAllUsers.add(user2);
        getAllUsers.add(user3);
        Mockito.when(userRepository.findAll()).thenReturn(getAllUsers);
        mockMvc.perform(MockMvcRequestBuilders.get("/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
