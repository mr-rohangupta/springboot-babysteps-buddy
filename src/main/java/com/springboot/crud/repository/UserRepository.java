package com.springboot.crud.repository;

import com.springboot.crud.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author rohangupta
 */
public interface UserRepository extends JpaRepository<User, Long> {

}
