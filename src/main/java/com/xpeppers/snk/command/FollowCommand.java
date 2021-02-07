package com.xpeppers.snk.command;

import com.xpeppers.snk.socialnetwork.ISocialNetwork;

public class FollowCommand implements ICommand {    
        
    private final ISocialNetwork socialNetwork;
    private final String follower;
    private final String followed;

    public FollowCommand(
            String follower,
            String followed,
            ISocialNetwork socialNetwork) {
        
        this.follower = follower;
        this.followed = followed;
        this.socialNetwork = socialNetwork;
    }

    @Override
    public void execute() {       
        socialNetwork.follow(follower, followed);
    }    
}
