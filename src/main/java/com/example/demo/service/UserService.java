package com.example.demo.service;

import com.example.demo.models.UserModel;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         //logic to get the user from the database
        UserModel user = userRepository.findByUsername(username);
//        if ("vivek".equals(username)) {
            return new User(user.getUsername(), user.getPassword(), new ArrayList<>());
//        } else {
//            throw new UsernameNotFoundException("User not found with username: " + username);
//        }
//        return new User("admin","password",new ArrayList<>());
    }
}
