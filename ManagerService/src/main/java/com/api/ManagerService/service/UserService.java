package com.api.ManagerService.service;

import com.api.ManagerService.mapper.UserMapper;
import com.api.ManagerService.model.TokenResponse;
import com.api.ManagerService.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    JwtService jwtService;

    public ResponseEntity<Object> userLogin(TokenResponse tokenResponse){
        User databaseUser = userMapper.findByUsername(tokenResponse.getUserName());
        if (databaseUser == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Sorry, User Does Not Exist");
        }
        if (bCryptPasswordEncoder.matches(tokenResponse.getPassword(), databaseUser.getPassword())){
            String token = jwtService.generateToken(tokenResponse.getUserName());
            tokenResponse.setToken(token);
            tokenResponse.setExpirationTime("60 Sec");
            return ResponseEntity.ok(tokenResponse);
        }else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Password Doesn't Match. Verify");
        }

    }
    public ResponseEntity<Object> userRegister(User user){
        String hashedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        try {
            userMapper.save(user);
            return ResponseEntity.ok("User saved");
        }
        catch (Exception exception){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("User Not Saved " + exception.getMessage());
        }
    }
    public User findUser(String userName){
        return userMapper.findByUsername(userName);
    }
}
