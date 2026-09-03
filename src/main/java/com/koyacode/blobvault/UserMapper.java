package com.koyacode.blobvault;

import org.springframework.stereotype.Component;


@Component
public class UserMapper {

    public User toEntity(CreateUserRequestDTO request) {
        return new User(request.name());
    }

    public UserResponseDTO toResponse(User user){
        return new UserResponseDTO(
                user.getName());
    }
}
