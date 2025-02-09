package com.elibrary.LibraLink.security;

import com.elibrary.LibraLink.entities.Role;
import com.elibrary.LibraLink.entities.User;
import com.elibrary.LibraLink.repositories.RoleRepository;
import com.elibrary.LibraLink.repositories.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    // CONSTANT VALUES
    private final UserRepository userRepository;

    // CONSTRUCTOR
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found " + username));
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getRole_id().getRole_name());
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.isStatus(),
                true,
                true,
                true,
                List.of(authority)
        );
    }
}
