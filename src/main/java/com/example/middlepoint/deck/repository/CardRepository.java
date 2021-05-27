package com.example.middlepoint.deck.repository;

import com.example.middlepoint.deck.entity.Card;
import com.example.middlepoint.deck.entity.Deck;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CardRepository extends JpaRepository<Card, Long> {



}
