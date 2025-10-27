package com.example.shoppinglist.service;

import com.example.shoppinglist.domain.Owner;
import com.example.shoppinglist.domain.OwnerRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final OwnerRepository userRepository;

    public UserDetailsServiceImpl(OwnerRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Owner> user = userRepository.findByUsername(username);

        UserBuilder builder = null;
        if (user.isPresent()) { // 이하의 실행문이 실행되면 user에 AppUser가 있다는 의미
            Owner currentUser = user.get();
            builder = User.withUsername(username);
            builder.password(currentUser.getPassword());
            builder.roles(currentUser.getRole());
        } else {
            throw new UsernameNotFoundException("User not found.");
        }

        return builder.build();
    }
}
