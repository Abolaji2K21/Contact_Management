package africa.semicolon.controller;


import africa.semicolon.contactException.BigContactException;
import africa.semicolon.dtos.requests.LoginUserRequest;
import africa.semicolon.dtos.requests.LogoutUserRequest;
import africa.semicolon.dtos.requests.RegisterUserRequest;
import africa.semicolon.dtos.response.ApiResponse;
import africa.semicolon.dtos.response.LoginUserResponse;
import africa.semicolon.dtos.response.LogoutUserResponse;
import africa.semicolon.dtos.response.RegisterUserResponse;
import africa.semicolon.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/Contact_Management")
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping("/sign up")
    public ResponseEntity<?> registerUser(@RequestBody RegisterUserRequest registerUserRequest) {
        try{
            RegisterUserResponse result = userService.register(registerUserRequest);
            return new ResponseEntity<>(new ApiResponse(true,result),CREATED);
        } catch (BigContactException message){
            return new ResponseEntity<>(new ApiResponse(false, message.getMessage()),BAD_REQUEST);
        }
    }
    @PatchMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginUserRequest loginUserRequest) {
        try {
            LoginUserResponse result = userService.login(loginUserRequest);
            return new ResponseEntity<>(new ApiResponse(true, result), CREATED);
        }
        catch (BigContactException message) {
            return new ResponseEntity<>(new ApiResponse(false, message.getMessage()), BAD_REQUEST);
        }
    }

    @PatchMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody LogoutUserRequest logoutUserRequest) {
        try {
            LogoutUserResponse result = userService.logout(logoutUserRequest);
            return new ResponseEntity<>(new ApiResponse(true, result), CREATED);
        }
        catch (BigContactException message) {
            return new ResponseEntity<>(new ApiResponse(false, message.getMessage()), BAD_REQUEST);
        }
    }



}
