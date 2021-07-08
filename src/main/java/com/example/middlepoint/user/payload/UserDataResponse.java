package com.example.middlepoint.user.payload;


import com.example.middlepoint.deck.entity.Card;
import com.example.middlepoint.deck.entity.Deck;

import java.util.List;
import java.util.UUID;

public class UserDataResponse {
   private String username;

   private List <Deck> deck;

   public UserDataResponse(String username, List<Deck> deck) {
	  this.username = username;
	  this.deck = deck;
   }

   public String getUsername() {
	  return username;
   }

   public void setUsername(String username) {
	  this.username = username;
   }

   public List<Deck> getDeck() {
	  return deck;
   }

   public void setDeck(List<Deck> deck) {
	  this.deck = deck;
   }
}
