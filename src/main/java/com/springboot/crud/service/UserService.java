package com.springboot.crud.service;

import com.springboot.crud.dto.UserDto;
import com.springboot.crud.model.User;
import com.springboot.crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author rohangupta
 */
@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream().map(User::toDto)
                .collect(Collectors.toList());
    }
}