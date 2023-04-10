package com.PractionBE.controller;

import com.PractionBE.dtos.reponse.UserResponse;
import com.PractionBE.dtos.request.UserRequest;
import com.PractionBE.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest){
        UserResponse user = userService.saveUser(userRequest);
        return ResponseEntity.created(URI.create("/users/" + user.getNickName())).body(user);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserResponse> showUser(@PathVariable Long id){
        return ResponseEntity.ok().body(userService.findUserById(id));
    }

    @GetMapping(value = "/users")
    public ResponseEntity<List<UserResponse>> showUsers(){
        return ResponseEntity.ok().body(userService.findAllUsers());
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<Void> updateUsers(@PathVariable Long id, @RequestBody UserRequest userRequest){
        userService.updateUserById(id, userRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUsers(@PathVariable Long id){
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

}
