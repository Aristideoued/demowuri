package com.wuri.demowuri.controller;

import com.wuri.demowuri.dto.UserDto;
import com.wuri.demowuri.model.User;

import com.wuri.demowuri.services.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("creer")
    public ResponseEntity<User> creerUser(@RequestBody User User){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.creerUser(User));
    }

    @PutMapping("update/{id}")
    public ResponseEntity<User> updateUser(@RequestBody UserDto UserDto, @PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(userService.modifierUser(UserDto, id));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("liste")
    public ResponseEntity<List<User>> listeUser(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.listeUser());
    }

    @GetMapping("show/{id}")
    public ResponseEntity<User> showCategorie(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(userService.showUser(id));
    }
}
