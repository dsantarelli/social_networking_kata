package com.xpeppers.snk.socialnetwork;

import java.text.MessageFormat;

public class UserNotFoundException extends RuntimeException {
        
    private static final long serialVersionUID = -5679784659536983704L;

    public UserNotFoundException(String username) {
        super(MessageFormat.format("user \"{0}\" not found", username));
    }
}