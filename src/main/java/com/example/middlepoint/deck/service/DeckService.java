package com.example.middlepoint.deck.service;


import com.example.middlepoint.deck.entity.Deck;
import com.example.middlepoint.deck.repository.DeckRepository;
import com.example.middlepoint.user.entity.User;

import com.example.middlepoint.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;



import java.util.List;
import java.util.Optional;

@Service
public class DeckService {
   private final DeckRepository deckRepository;
   private final UserRepository userRepository;

   /*@Autowired
   public DeckService(DeckRepository deckRepository) {
	  this.deckRepository = deckRepository;
   }*/

   @Autowired
   public DeckService(DeckRepository deckRepository, UserRepository userRepository) {
      this.deckRepository = deckRepository;
      this.userRepository = userRepository;
   }

   private  User getUserById (Long userId)
   {
      Optional<User> user = userRepository.findById(userId);
      return user.get();
   }
   public List<Deck> getAllDeck(){

      return deckRepository.findAll();
   }

   public Optional<Deck> getDeckById(long deckId){

      return deckRepository.findById(deckId);
   }


   public void addDeck (Deck newDeck, Long userId){
      User currentUser = getUserById(userId);
      newDeck.setUser(currentUser);
     deckRepository.save(newDeck);

   }


   public void editDeck (Long id, Deck deck)
   {
      if (deckRepository.existsById(id))
      {
          deckRepository.findById(id).map(d -> {
            d.setName(deck.getName());
            d.setCollection(deck.getCollection());
            d.setCards(d.getCards());
            return deckRepository.save(d);
         });

      }
      else{
         System.out.print("no select deck found");
      }

   }

   public void deleteDeck(long id){

      deckRepository.deleteById(id);

   }
}

