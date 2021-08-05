package com.example.middlepoint.deck.service;

import com.example.middlepoint.deck.entity.Card;
import com.example.middlepoint.deck.entity.Deck;
import com.example.middlepoint.deck.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardService {

   private final CardRepository cardRepository;
   private final DeckService deckService;

   @Autowired
   public CardService(CardRepository cardRepository, DeckService deckService) {
	  this.cardRepository = cardRepository;
      this.deckService = deckService;
   }

   private Optional<Deck> isDeckValid(long deck_id){
      return deckService.getDeckById(deck_id);
   }
   private Deck getCurrentDeck (Long deck_id){
      Optional<Deck> currentDeck = deckService.getDeckById(deck_id);
      return  currentDeck.get();
   }

   public List<Card> getAllCards(Long idDeck){

      return getCurrentDeck(idDeck).getCards();
   }



   public void addCard(Card newCard, long idDeck){

      Deck deck = getCurrentDeck(idDeck);
      Card card = newCard;
      card.setDeck(deck);
      cardRepository.save(card);
   }

   public void deleteCard (long cardId, Long deckId){

      if (isDeckValid(deckId).isPresent()){
         cardRepository.deleteById(cardId);
      }
   }
}
