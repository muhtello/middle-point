package com.example.middlepoint.deck.service;

import com.example.middlepoint.deck.entity.Card;
import com.example.middlepoint.deck.entity.Deck;
//import com.example.middlepoint.deck.repository.CardRepository;
import com.example.middlepoint.deck.repository.DeckRepository;
import javassist.expr.NewArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

@Service
public class DeckService {
   private final DeckRepository deckRepository;
   //private Logger logg = LoggerFactory.getLogger(DeckCardsService.class);

   @Autowired
   public DeckService(DeckRepository deckRepository) {
	  this.deckRepository = deckRepository;
   }


   public List<Deck> getAllDeck(){

      return deckRepository.findAll();
   }

   public Optional<Deck> getDeckById(long id){
      return deckRepository.findById(id);
   }

   /*public List<Deck> getDeckByUsername(long userId){


     return
   }*/




   public void addDeck (Deck newDeck){
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

