package br.mackenzie.webapp.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.mackenzie.webapp.security.model.User;
import br.mackenzie.webapp.security.model.UserPrincipal;
import br.mackenzie.webapp.security.repo.UserRepo;

import java.util.Optional;

/**
 * UserInfoService provides user-related services including loading user details
 * and managing user data in the repository.
 */
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;


    // Loads a user's details given their username.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepo.findByUsername(username);
        return user.map(UserPrincipal::new)
                .orElseThrow(() -> new UsernameNotFoundException("UserName not found: " + username));
    }


    // Adds a new user to the repository and encrypting password before saving it.
    public User addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }


}