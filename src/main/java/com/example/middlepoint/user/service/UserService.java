package com.example.middlepoint.user.service;

import com.example.middlepoint.deck.entity.Deck;
import com.example.middlepoint.deck.repository.CardRepository;
import com.example.middlepoint.deck.repository.DeckRepository;
import com.example.middlepoint.deck.service.DeckService;
import com.example.middlepoint.user.entity.User;
import com.example.middlepoint.user.payload.UserDataResponse;
import com.example.middlepoint.user.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

   private final UserRepository userRepository;

   @Autowired
   private  DeckService deckService;

   @Autowired
   private CardRepository cardRepository;

   @Autowired
   public UserService(UserRepository userRepository) {
	  this.userRepository = userRepository;
   }

   public Optional<User> getUserById(Long id){

      return userRepository.findById(id);
   }

   public UserDataResponse getUserData(String userName){
      Optional <User> isUserExisted = userRepository.findByUsername(userName);
      User currentUser = isUserExisted.isPresent() ? isUserExisted.get() :
          isUserExisted.orElse(null);

      if (currentUser == null) return null;
      else{

         List<Deck> decks = userRepository.findByUsername(userName).get().getDecks();
         return new UserDataResponse(
            currentUser.getUsername(),
            decks
         );
      }
   }

}
