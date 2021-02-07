package com.xpeppers.snk.socialnetwork;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InMemorySocialNetwork implements ISocialNetwork {

    private final Map<String, User> users;

    public InMemorySocialNetwork(List<User> users) {
        this.users = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        users.forEach(x -> this.users.put(x.getUsername(), x));
    }
    
    @Override
    public void postMessage(String username, String message) {       
        getUser(username).postMessage(message);
    }

    @Override
    public List<Message> getTimeline(String username) {
        return sortByTimestampDescending(getUser(username).getMessages().stream())
                .collect(Collectors.toList());
    }

    @Override
    public void follow(String followerUsername, String followedUsername) {
        var follower = getUser(followerUsername);
        var followed = getUser(followedUsername);        
        follower.followUser(followed);  
    }

    @Override
    public List<Message> getWall(String username) {
        var user = getUser(username);
        var userMessages = user.getMessages().stream();
        var otherUsersMessages = user
                    .getFollowing()
                    .stream()
                    .flatMap(x -> x.getMessages().stream());
        
        return sortByTimestampDescending(
                Stream.concat(userMessages, otherUsersMessages))
                      .collect(Collectors.toList());
    }
    
    private User getUser(String username) { 
        return Optional
                .ofNullable(users.get(username))
                .orElseThrow(() -> new UserNotFoundException(username));               
    }
    
    private static Stream<Message> sortByTimestampDescending(Stream<Message> stream) {
        return stream.sorted((a,b) -> a.getTimestamp().compareTo(b.getTimestamp()) * -1);
    }    
}
