package ua.nure.arkpz.security.dao;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.nure.arkpz.security.model.User;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User, String> {
   // Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);
}
