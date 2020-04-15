package ua.nure.arkpz.security.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.nure.arkpz.security.dao.UserDao;
import ua.nure.arkpz.security.model.User;

@Service
public class UserService implements UserDetailsService {
    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        return userDao.findByEmail(email).orElse(new User());
    }

    public void save(User user) {
        userDao.save(user);
    }


    public User findByEmail(String email) {
        return userDao.findByEmail(email).orElse(new User());
    }

   /* public User findByUsername(String username) {
        return userDao.findByUsername(username).orElse(new User());
    }*/
}
