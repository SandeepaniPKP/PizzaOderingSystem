package controllers;

import models.User;

import java.util.HashMap;
import java.util.Map;

public class UserController {
    private Map<String, User> users = new HashMap<>();

    public User getOrCreateUser(String name) {
        if (!users.containsKey(name)) {
            User newUser = new User(name);
            users.put(name, newUser);
        }
        return users.get(name);
    }

    public User getUser(String name) {
        return users.get(name); // Returns null if the user is not found
    }
}
