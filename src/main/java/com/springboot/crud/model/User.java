package com.springboot.crud.model;

import com.springboot.crud.dto.UserDto;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author rohangupta
 */
@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String jobTitle;
    private String firstName;
    private String lastName;
    private String email;

    public UserDto toDto(){
        return UserDto.builder()
                .id(this.id)
                .firstName(this.firstName)
                .lastName(this.lastName)
                .email(this.email)
                .jobTitle(this.jobTitle)
                .build();
    }

    public User value(UserDto fromDto){
        this.firstName = fromDto.getFirstName();
        this.lastName = fromDto.getLastName();
        this.jobTitle = fromDto.getJobTitle();
        this.email = fromDto.getEmail();
        return this;
    }
}