package com.example.middlepoint.deck.controller;

import com.example.middlepoint.deck.entity.Card;
import com.example.middlepoint.deck.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "api/v1/deck/{id}/cards")
public class CardController {

   private final CardService cardService;

   @Autowired
   public CardController( CardService cardService) {
      this.cardService = cardService;

   }


   @GetMapping()
   public List<Card> getAllCardsFromDeck(@PathVariable("id") long idDeck){

		 return cardService.getAllCards(idDeck);

   }

   @PostMapping
   public void addCard(@PathVariable("id") long idDeck, @NonNull @RequestBody Card card)
   {
	  cardService.addCard(card, idDeck);

   }

   @DeleteMapping(path = "{cardId}")
   public void deleteCard(@PathVariable("id") long deckId, @PathVariable("cardId") long cardId){
      cardService.deleteCard(cardId, deckId);
   }

}
