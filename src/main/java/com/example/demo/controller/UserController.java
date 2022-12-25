package com.example.demo.controller;

import com.example.demo.models.JwtRequest;
import com.example.demo.models.JwtResponse;
import com.example.demo.models.UserModel;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.utils.JwtUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private JwtUtility jwtUtility;

    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserService userService;

   @GetMapping("/")
   public  String home(){
       return "hello";
   }

   @PostMapping("/userlogin")
   public Object authentication(@RequestBody JwtRequest jwtRequest) throws Exception{
       try {
           authenticationManager.authenticate(
                   new UsernamePasswordAuthenticationToken(
                           jwtRequest.getUsername(),
                           jwtRequest.getPassword()
                   )
           );
       }catch (BadCredentialsException e){
           return "username or password is invalid";
       }
       final UserDetails userDetails = userService.loadUserByUsername(jwtRequest.getUsername());
       final String token = jwtUtility.generateToken(userDetails);
       return new JwtResponse(token);
   }


    //creating new user
    @PostMapping("/user")
    public UserModel create(@RequestBody UserModel body){
        String username = body.getUsername();
        String email = body.getEmail();
        String password = bCryptPasswordEncoder.encode(body.getPassword());
        return userRepository.save(new UserModel(username,email,password));
    }


    //list all the user
    @GetMapping("/userlist")
    public List<UserModel> listAllUser(){
        return userRepository.findAll();
    }


    //get a user information using id
    @GetMapping("/user/{id}")
    public Object list_a_User(@PathVariable int id){
        Optional<UserModel> validUser =  userRepository.findById(id);
        if (validUser.isPresent()) {
            return userRepository.findById(id);
        }else {
            return "User Not Found";
        }
    }


    //deleting a user
    @DeleteMapping("/user/{id}")
    public String delete(@PathVariable int id){
        Optional<UserModel> validUser =  userRepository.findById(id);
        if (validUser.isPresent()) {
            userRepository.deleteById(id);
            return "User deleted Successfully";
        }else {
            return "User not found";
        }
    }

}
