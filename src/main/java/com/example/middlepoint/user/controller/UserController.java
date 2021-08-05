package com.example.middlepoint.user.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import com.example.middlepoint.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;




@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping(path="/api/v1/")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {


   private final UserService userService;

   @Autowired
   public UserController(UserService userService) {
      this.userService = userService;
   }


   @GetMapping(path = "data/{username}")
   public ResponseEntity<?> getUserData(@PathVariable("username") String username){
      return ResponseEntity.ok(userService.getUserData(username));

   }



}


