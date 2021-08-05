package com.example.middlepoint.deck.repository;

import com.example.middlepoint.deck.entity.Deck;
import org.springframework.data.jpa.repository.JpaRepository;



public interface DeckRepository extends JpaRepository<Deck, Long> {

}
