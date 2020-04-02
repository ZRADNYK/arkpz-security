package ua.nure.arkpz.security.dao;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.nure.arkpz.security.model.User;

@Repository
public interface UserDao extends JpaRepository<User, String> {
    User findByUsername(String username);
}
