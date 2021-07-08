package com.example.middlepoint.config.services;


import com.example.middlepoint.user.entity.User;
import com.example.middlepoint.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
public class AuthenticatedUserService {
   @Autowired
   private UserRepository userRepository;



   private UserDetails getUserDetails(){
	  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
       UserDetails userDetails = (UserDetails) auth.getPrincipal();
	  return userDetails;
   }

   public boolean isAuthorized(){
      String currentUser = getUserDetails().getUsername();
	  User user = userRepository.findByUsername(currentUser)
		  .orElseThrow(() -> new UsernameNotFoundException(
		  	"User Not Found with username: " + currentUser)
		  )
	 ;

	  return  currentUser.equals(user.getUsername());
   }

   public boolean isUserLogin(String username){
      String currentUser = getUserDetails().getUsername();
      System.out.print("the user loging: "+ username);
      return currentUser.equals(username);
   }

}
