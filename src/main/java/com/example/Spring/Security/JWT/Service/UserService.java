package com.example.Spring.Security.JWT.Service;

import com.example.Spring.Security.JWT.AuthenticationService.User;
import com.example.Spring.Security.JWT.Dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {


      @Autowired
      private UserDao userDao;

      public User saveUser(User user) {
         return userDao.save(user);
      }

      public User getUserByName(String username){
          return userDao.findByUsername(username);
      }

      public List<User> getAllUsers() {
          return userDao.findAll();
      }

      public void deleteUser(String username){
          userDao.deleteById(username);
      }

//    // Example method to get a user by username and password
//    public User getUserByNameandPassword(String username, String password) throws UserNotFoundException {
//        // Logic to retrieve user from the database
//        return null; // Replace with actual user retrieval logic
//    }
}
