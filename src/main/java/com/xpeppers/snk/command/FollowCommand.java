package com.xpeppers.snk.command;

import com.xpeppers.snk.socialnetwork.ISocialNetwork;

public class FollowCommand implements ICommand {    
        
    private final String follower;
    private final String followed;
    private final ISocialNetwork socialNetwork;

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
