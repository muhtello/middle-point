package com.example.middlepoint.user.controller;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.example.middlepoint.config.jwt.model.JwtResponse;
import com.example.middlepoint.config.jwt.utility.JwtUtility;
import com.example.middlepoint.config.payload.LoginRequest;
import com.example.middlepoint.config.payload.MessageResponse;
import com.example.middlepoint.config.payload.SignupRequest;
import com.example.middlepoint.config.services.UserDetailsImpl;
import com.example.middlepoint.user.entity.ERole;
import com.example.middlepoint.user.entity.Role;
import com.example.middlepoint.user.entity.User;
import com.example.middlepoint.user.repository.RoleRepository;
import com.example.middlepoint.user.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {

   @Autowired
   AuthenticationManager authenticationManager;

   @Autowired
   UserRepository userRepository;

   @Autowired
   RoleRepository roleRepository;

   @Autowired ()
   PasswordEncoder encoder;

   @Autowired
   JwtUtility jwtUtility;



   //resend JwtResponse for client if it requested
   @GetMapping("/user")
   public ResponseEntity<?> getAuthenticatedUser(@RequestParam String token){
      String currentToken = token;

	  if (currentToken != null)
	  {
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		 boolean isAuthenticated = auth.isAuthenticated();
		 //System.out.println("user is " + isAuthenticated);
		 if (isAuthenticated)
		 {
			// TODO get role for user name
			String username= jwtUtility.getUserNameFromJwtToken(currentToken);
			System.out.println("the user " + username + " is authenticated");
			return ResponseEntity.ok(username);
		 }else {
			//return ResponseEntity.status(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);
			return new ResponseEntity<>("the token is expired",
				HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);
		 }
	  }
	  else{
		 return new ResponseEntity<>("logging is required",
			 HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);
	  }

   }

   @PostMapping("/signin")
   public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest){
	  Authentication authentication = authenticationManager.authenticate(
		  new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

	  SecurityContextHolder.getContext().setAuthentication(authentication);
	  String jwt = jwtUtility.generateJwtToken(authentication);
	 UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
	  List<String> roles = userDetails.getAuthorities().stream()
		  .map(item -> item.getAuthority())
		  .collect(Collectors.toList());

	  return ResponseEntity.ok(new JwtResponse(jwt,
		  userDetails.getId(),
		  userDetails.getUsername(),
		  userDetails.getEmail(),
		  roles)
	  );

   }

   @PostMapping("/signup")
   public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
	  if (userRepository.existsByUsername(signUpRequest.getUsername())) {
		 return ResponseEntity
			 .badRequest()
			 .body(new MessageResponse("Error: Username is already taken!"));
	  }

	  if (userRepository.existsByEmail(signUpRequest.getEmail())) {
		 return ResponseEntity
			 .badRequest()
			 .body(new MessageResponse("Error: Email is already in use!"));
	  }

	  // Create new user's account
	  User user = new User(signUpRequest.getUsername(),
		  signUpRequest.getEmail(),
		  encoder.encode(signUpRequest.getPassword())
	  );

	  Set<String> strRoles = signUpRequest.getRole();
	  Set<Role> roles = new HashSet<>();

	  if (strRoles == null) {
		 Role userRole = roleRepository.findByName(ERole.ROLE_USER)
			 .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
		 roles.add(userRole);
	  } else {
		 strRoles.forEach(role -> {
			switch (role) {
			   case "admin":
				  Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
					  .orElseThrow(() -> new RuntimeException("Error: Role is not found as admin."));
				  roles.add(adminRole);

				  break;
			   case "mod":
				  Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
					  .orElseThrow(() -> new RuntimeException("Error: Role is not found as mod."));
				  roles.add(modRole);

				  break;
			   default:
				  Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					  .orElseThrow(() -> new RuntimeException("Error: Role is not found as user."));
				  roles.add(userRole);
			}
		 });
	  }

	  user.setRoles(roles);
	  userRepository.save(user);

	  return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
   }




}
