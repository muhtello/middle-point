package com.example.middlepoint.deck.controller;


import com.example.middlepoint.deck.entity.Deck;
import com.example.middlepoint.deck.service.DeckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;

import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(path="data/{userId}/api/v1/deck")
@CrossOrigin(origins = "http://localhost:3000")
public class DeckController {
   private final DeckService deckService;

   @Autowired
   public DeckController(DeckService deckService) {
	  this.deckService = deckService;
   }



   @GetMapping
   public List<Deck>getAllDecks(){
      return deckService.getAllDeck();
   }

   @GetMapping(path = "{deck_id}")
   public Optional<Deck> getDeckById( @PathVariable("deck_id") long deckId){

      return deckService.getDeckById(deckId);
   }

   @PostMapping
   public void addDeck( @NonNull @RequestBody Deck newDeck, @PathVariable("userId") Long userId){
      deckService.addDeck(newDeck, userId);

   }

   @PutMapping(path = "{id}")
   public void updateDeck(@PathVariable("id") Long id, @NonNull @RequestBody Deck deck)
   {
      deckService.editDeck(id, deck);
   }

   @DeleteMapping(path = "{id}")
   public void deleteDeck(@PathVariable("id") Long id){
      deckService.deleteDeck(id);
   }

}

