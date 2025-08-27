package com.example.Spring.Security.JWT.Controller;

import com.example.Spring.Security.JWT.AuthenticationService.User;
import com.example.Spring.Security.JWT.Service.UserService;
import com.example.Spring.Security.JWT.Util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@RequestBody User user) {
        if (user.getUsername() == null || user.getPassword() == null) {
            return new ResponseEntity<>("Username and password cannot be null", HttpStatus.BAD_REQUEST);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );

        if (authentication.isAuthenticated()) {
          //  System.out.println("JWT Secret Key: " + secretKey);

            return jwtUtil.generateToken(user.getUsername());
        } else {
            throw new RuntimeException("Invalid login credentials");
        }
    }

    @DeleteMapping("{username}")
    public ResponseEntity<?> deleteUser(@PathVariable String username){
         User user = userService.getUserByName(username);
         if ( user == null){
             return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
         }
         userService.deleteUser(username);
            return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
    }

}
