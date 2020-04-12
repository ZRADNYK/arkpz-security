package ua.nure.arkpz.security.util.validator.constraints;

import net.sf.oval.constraint.CheckWithCheck;
import org.springframework.beans.factory.annotation.Autowired;
import ua.nure.arkpz.security.model.User;
import ua.nure.arkpz.security.service.UserService;

public class CheckUniqueMail implements CheckWithCheck.SimpleCheck {
    @Autowired
    private UserService userService;

    @Override
    public boolean isSatisfied(Object validatedUser, Object mail) {
        User user = (User) validatedUser;
        User userFromDB = userService.findByEmail(String.valueOf(user.getEmail()));
        return (userFromDB.getUserId().equals(user.getUserId())
                || !(userFromDB.getEmail().equals(user.getEmail())));
    }
}
