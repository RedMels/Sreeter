package ru.staticvoid.sreeter.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.staticvoid.sreeter.domain.User;

public interface UserRepo extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
