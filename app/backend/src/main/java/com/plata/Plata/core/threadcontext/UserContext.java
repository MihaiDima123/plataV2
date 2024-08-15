package com.plata.Plata.core.threadcontext;

import com.plata.Plata.core.exception.ForbiddenException;
import com.plata.Plata.user.entity.User;

public class UserContext {
    private final static ThreadLocal<User> currentUser = new ThreadLocal<>();

    public static User get() throws ForbiddenException {
        if (currentUser.get() == null) {
            throw new ForbiddenException("User was not logged in");
        }

        return currentUser.get();
    }

    public static void set(User user) throws ForbiddenException {
        if (user == null) {
            throw new ForbiddenException("User could not be set");
        }

        currentUser.set(user);
    }
}
