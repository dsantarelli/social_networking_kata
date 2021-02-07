package com.xpeppers.snk.socialnetwork;

import java.util.List;

public interface ISocialNetwork {

    void postMessage(String username, String message);
    
    List<Message> getTimeline(String username);
    
    void follow(String followerUsername, String followedUsername);
    
    List<Message> getWall(String username);
}
