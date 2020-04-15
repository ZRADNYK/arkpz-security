package ua.nure.arkpz.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.nure.arkpz.security.dao.UserDao;
import ua.nure.arkpz.security.model.User;
import ua.nure.arkpz.security.util.validator.OvalValidator;

import java.util.Optional;

@Service
public class RegistrationService {
    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;
    private final OvalValidator validator;

    @Autowired
    public RegistrationService(UserDao userDao, PasswordEncoder passwordEncoder, OvalValidator validator) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.validator = validator;
    }


    public User registerUser(User userDTO) {
        User user = User.builder()
                .userId(null)
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                //.firstName(Optional.ofNullable(userDTO.getFirstName()).orElse(""))
                //.lastName(Optional.ofNullable(userDTO.getLastName()).orElse(""))
                //.username(userDTO.getUsername())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .birthDate(userDTO.getBirthDate())
                .email(userDTO.getEmail())
                .token(null)
                .tokenExpirationDate(null)
                .isAccountNonExpired(true)
                .isAccountNonLocked(true)
                .isCredentialsNonExpired(true)
                .isEnabled(true)
                .build();
        validator.validate(user);
        userDao.save(user);
        return user;
    }

}
