package ua.nure.arkpz.security.util.validator.constraints;

import net.sf.oval.constraint.CheckWithCheck;
import org.springframework.beans.factory.annotation.Autowired;
import ua.nure.arkpz.security.model.User;
import ua.nure.arkpz.security.service.UserService;


public class CheckUniqueUsername implements CheckWithCheck.SimpleCheck {
    @Autowired
    private UserService userService;

    @Override
    public boolean isSatisfied(Object validatedUser, Object name) {
        User user = (User) validatedUser;
        User userFromDB = userService.findByUsername(user.getUsername());

        return (userFromDB.getUserId().equals(user.getUserId())
                || !(userFromDB.getUsername().equals(user.getUsername())));
    }
}

