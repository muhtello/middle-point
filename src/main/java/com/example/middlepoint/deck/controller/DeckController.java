package com.example.middlepoint.deck.controller;

import com.example.middlepoint.config.services.AuthenticatedUserService;
import com.example.middlepoint.deck.entity.Deck;
import com.example.middlepoint.deck.service.DeckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;




@RestController
@RequestMapping(path="/api/v1/deck")
@PreAuthorize("@authenticatedUserService.isAuthorized()")

@CrossOrigin(origins = "http://localhost:3000")
public class DeckController {
   private final DeckService deckService;

   @Autowired
   private AuthenticatedUserService authenticatedUserService;

   @Autowired
   public DeckController(DeckService deckService) {
	  this.deckService = deckService;
   }

   @GetMapping
   public List<Deck>getAllDecks(){
      return deckService.getAllDeck();
   }

   //@PreAuthorize("@authenticatedUserService.hasId(#id)") @PathVariable("deck_id") in function
   @GetMapping(path = "{deck_id}")
   public Optional<Deck> getDeckById(@PathVariable("deck_id") long deckId){
      return deckService.getDeckById(deckId);
   }

   @PostMapping
   public void addDeck( @NonNull @RequestBody Deck newDeck){
      deckService.addDeck(newDeck);

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

