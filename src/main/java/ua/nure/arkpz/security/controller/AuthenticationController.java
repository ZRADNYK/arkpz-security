package ua.nure.arkpz.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ua.nure.arkpz.security.model.User;
import ua.nure.arkpz.security.service.UserService;
import ua.nure.arkpz.security.util.JwtTokenUtil;

@RestController
@CrossOrigin
public class AuthenticationController {
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;

    @Autowired
    public AuthenticationController(PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager,
                                    JwtTokenUtil jwtTokenUtil, UserService userService) {
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService;
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody User user) {
        User userFromDb = userService.loadUserByUsername(user.getUsername());
        if (authenticate(user, userFromDb)) {
            final String token = jwtTokenUtil.generateToken(user);
            userFromDb.setToken(token);
            userFromDb.setTokenExpirationDate(jwtTokenUtil.getExpirationDateFromToken(token));
            return ResponseEntity.ok(userFromDb);
        } else return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    private boolean authenticate(User user, User userFromDb) {
        if (passwordEncoder.matches(user.getPassword(), userFromDb.getPassword())) {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            return true;
        }
        return false;
    }
}
