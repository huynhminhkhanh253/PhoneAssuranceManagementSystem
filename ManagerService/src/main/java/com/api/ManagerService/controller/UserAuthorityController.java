package com.api.ManagerService.controller;

import com.api.ManagerService.model.TokenResponse;
import com.api.ManagerService.model.User;
import com.api.ManagerService.service.JwtService;
import com.api.ManagerService.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("managerService")
@CrossOrigin
public class UserAuthorityController extends Exception {
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    UserService userService;
    @Autowired
    JwtService jwtService;
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        return ResponseEntity.badRequest().body("User name and password must not be null");
    }
    @PostMapping("user/register")
    public ResponseEntity<Object> registerUser(@Valid @RequestBody User user) throws Exception {
        return userService.userRegister(user);
    }
    @GetMapping("user/find/{userName}")
    public User find(@PathVariable String userName){
        return userService.findUser(userName);
    }
    @PostMapping("user/login")
    public ResponseEntity<Object> login(@RequestBody TokenResponse tokenResponse){
        return userService.userLogin(tokenResponse);
    }
}
