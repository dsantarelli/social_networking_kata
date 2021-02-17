package com.xpeppers.snk.command;

import com.xpeppers.snk.socialnetwork.ISocialNetwork;

public class PostCommand implements ICommand {

    private final String username;
    private final String message;
    private final ISocialNetwork socialNetwork;

    public PostCommand(
            String username, 
            String message, 
            ISocialNetwork socialNetwork) {

        this.username = username;
        this.message = message;
        this.socialNetwork = socialNetwork;
    }

    public String getUsername() {
        return username;
    }
    
    public String getMessage() {
        return message;
    }
    
    @Override
    public void execute() {
        socialNetwork.postMessage(getUsername(), getMessage());
    }
}
