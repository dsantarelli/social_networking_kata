package com.xpeppers.snk.socialnetwork;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class User {

    private String username;
    private List<Message> messages;
    private Map<String, User> following;
    private Map<String, User> followers;

    public User(String username) {
        this.username = username;
        this.messages = new ArrayList<>();
        this.following = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        this.followers = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    }

    public String getUsername() {
        return username;
    }

    public List<Message> getMessages() {
        return Collections.unmodifiableList(messages);
    }

    public List<User> getFollowing() {
        return Collections.unmodifiableList(new ArrayList<>(following.values()));
    }

    public List<User> getFollowers() {
        return Collections.unmodifiableList(new ArrayList<>(followers.values()));
    }

    public void postMessage(String message) {
        messages.add(new Message(username, message, Instant.now()));
    }

    public void followUser(User user) {

        if (user.getUsername().equalsIgnoreCase(this.getUsername()))
            throw new IllegalArgumentException("user \"" + this.getUsername() + "\" can't follow itself");

        following.putIfAbsent(user.getUsername(), user);
        user.followers.putIfAbsent(this.getUsername(), this);
    }
}
