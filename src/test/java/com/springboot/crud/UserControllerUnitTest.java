package com.springboot.crud;

import com.fasterxml.jackson.databind.ObjectMapper;
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

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void testGetAllUsers() throws Exception {
        List<User> getAllUsers = new ArrayList<>();
        User user1 = User.builder().id(1L).firstName("Rohan")
                .lastName("Gupta").email("rohan@gmail.com").jobTitle("Java Developer")
                .build();
        User user2 = User.builder().id(2L).firstName("Soniya")
                .lastName("Gupta").email("soniya@gmail.com").jobTitle("QA")
                .build();
        User user3 = User.builder().id(2L).firstName("Monika")
                .lastName("Gupta").email("monika@gmail.com").jobTitle("QA")
                .build();
        getAllUsers.add(user1);
        getAllUsers.add(user2);
        getAllUsers.add(user3);
        Mockito.when(userService.getAllUsers()).thenReturn(getAllUsers);
        mockMvc.perform(MockMvcRequestBuilders.get("/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void createNewUser() throws Exception {
        User persistedUser = new User(1l, "Solution Architect", "Rohan", "Gupta", "rohangupta@gmail.com");
        Mockito.when(userService.saveUser(Mockito.any())).thenReturn(persistedUser);
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .content(asJsonString(new User
                                (1l, "Solution Architect", "Rohan", "Gupta", "rohangupta@gmail.com")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }
}
