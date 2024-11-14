package br.mackenzie.webapp.security.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.mackenzie.webapp.security.model.User;

import java.util.Optional;


@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    /**
     * findByName method is used to retrieve a user by their username.
     * It returns an Optional of UserInfo, which will be empty if no user is found.
     */
    Optional<User> findByUsername(String username);
}
