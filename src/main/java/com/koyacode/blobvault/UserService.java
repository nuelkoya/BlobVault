package com.koyacode.blobvault;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {
    private User user;
    private Map<UUID, User> allUsers  = new HashMap<>();
    private final UserMapper mapper;

    public UserService(UserMapper mapper) {
        this.mapper = mapper;
    }

    public User createNewUser(CreateUserRequestDTO request) {
        User user = mapper.toEntity(request);
        allUsers.put(user.getUserId(), user);
        return user;
    }

    public User getUserById(UUID id) {
        return allUsers.get(id);

    }

    public List<UserResponseDTO> getAllUsers() {
        return allUsers.values()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }
}
