package com.example.APIRollTheDice.APIRollTheDice_Backend.Security.Service;

import com.example.APIRollTheDice.APIRollTheDice_Backend.Interface.User.UserRepository;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.User.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByusername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found  ");
        }

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRoleUser().name())
                .build();
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByusername(username);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public boolean existsById(Long id) {
        return userRepository.existsById(id);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User UpdateUser(User user) {
        return userRepository.save(user);
    }

    public int countUsers() {
        return (int) userRepository.count();
    }

    public String SaveUser(User user) {
        userRepository.save(user);
        return "User registered successfully!";
    }
}
