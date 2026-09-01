package com.koyacode.blobvault;

import java.util.Collection;
import java.util.Map;
import java.util.HashMap;
import java.util.UUID;


public class UserService {
    private User user;
    private Map<UUID, User> allUsers  = new HashMap<>();

    public User createNewUser(String name) {
        User user = new User(name);
        allUsers.put(user.getUserId(), user);
        return user;
    }

    public User getUserById(UUID id) {
        return allUsers.get(id);

    }

    public Collection<User> getAllUsers() {
        return allUsers.values();
    }
}
