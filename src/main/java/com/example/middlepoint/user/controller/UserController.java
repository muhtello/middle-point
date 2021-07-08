package com.example.middlepoint.user.controller;

import com.example.middlepoint.config.payload.MessageResponse;
import com.example.middlepoint.config.services.AuthenticatedUserService;
import com.example.middlepoint.deck.entity.Deck;
import com.example.middlepoint.user.entity.User;
import netscape.javascript.JSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import com.example.middlepoint.user.service.UserService;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path="/api/v1/")
@PreAuthorize("@authenticatedUserService.isAuthorized()")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

   @Autowired
   private AuthenticatedUserService authenticatedUserService;


   private final UserService userService;

   @Autowired
   public UserController(UserService userService) {
      this.userService = userService;
   }

   @PreAuthorize("@authenticatedUserService.isUserLogin(#username)")
   @GetMapping(path = "data/{username}")
   public ResponseEntity<?> getUserData(@PathVariable("username") String username){
      return ResponseEntity.ok(userService.getUserData(username));

   }

  /* @GetMapping(path = "{id}")
   public ResponseEntity<?>getUserData(@PathVariable("id") long id){
	  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	  UserDetails userDetails = (UserDetails) auth.getPrincipal();

	  System.out.println("User has authorities: " + userDetails.getAuthorities());
	  System.out.println("User has principal: " + userDetails.getClass());
	  System.out.println("UserName is : " + userDetails.getUsername());
	  Optional <User> findUser = userService.getUserById(id);
	  User currentUser = findUser.get();
	  String name = currentUser.getUsername();
	  String otherName= userDetails.getUsername();
	  if (currentUser.getId() == id && name.equals(otherName)){
	     // maybe should create class to contain all information
	     return ResponseEntity.ok(
	     new User(

	     	currentUser.getId(),
			 currentUser.getUsername(),
			 currentUser.getEmail(),
			 currentUser.getDecks()
		 )

		 );
	  }
	  else {
		 return ResponseEntity.badRequest().body( new MessageResponse("the auth is f"));
	  }*/
   //}
   /*public Optional<User> getUserById(@PathVariable("id") long id){
	  return userService.getUserById(id);
   }*/


}


   /*Authentication auth = SecurityContextHolder.getContext().getAuthentication();
   UserDetails userDetails = (UserDetails) auth.getPrincipal();

      System.out.println("User has authorities: " + userDetails.getUsername());*/


   /*The authentication instance now provides the following methods:

	Get the username of the logged in user: getPrincipal()
	Get the password of the authenticated user: getCredentials()
	Get the assigned roles of the authenticated user: getAuthorities()
	Get further details of the authenticated user: getDetails()*/