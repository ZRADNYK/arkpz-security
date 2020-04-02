package ua.nure.arkpz.security.service;


import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.nure.arkpz.security.dao.UserDao;
import ua.nure.arkpz.security.model.User;

@Service
public class UserService implements UserDetailsService {
    private UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDao.findByUsername(username);
    }

    public void save(User user) {
        userDao.save(user);
    }
}
