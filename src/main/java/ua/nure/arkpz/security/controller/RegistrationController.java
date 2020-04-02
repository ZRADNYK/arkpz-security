package ua.nure.arkpz.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.arkpz.security.model.User;
import ua.nure.arkpz.security.service.UserService;

@RestController
@CrossOrigin
public class RegistrationController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        final UserDetails userDetails = userService.loadUserByUsername(user.getUsername());

        if (userDetails != null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        User userFromDb = User.builder()
                .userId(null)
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .password(passwordEncoder.encode(user.getPassword()))
                .token(null)
                .tokenExpirationDate(null)
                .isAccountNonExpired(true)
                .isAccountNonLocked(true)
                .isCredentialsNonExpired(true)
                .isEnabled(true)
                .build();
        userService.save(userFromDb);
        return ResponseEntity.ok(userFromDb);
    }
}
